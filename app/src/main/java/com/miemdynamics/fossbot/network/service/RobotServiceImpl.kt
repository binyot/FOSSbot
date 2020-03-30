package com.miemdynamics.fossbot.network.service

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.miemdynamics.fossbot.data.entity.Program
import com.miemdynamics.fossbot.network.connection.Connection
import com.miemdynamics.fossbot.network.connection.ConnectionTarget
import kotlinx.coroutines.*
import java.io.*

class RobotServiceImpl(
    private val connection: Connection
) : RobotService {
    // use this member to change state internally
    private var state: RobotService.State =
        RobotService.State.Disconnected(RobotService.DisconnectedBy.Client)
        set(newState) {
            field = newState
            _liveState.postValue(newState)
        }

    private val _liveState = MutableLiveData<RobotService.State>(state)
    override val liveState: LiveData<RobotService.State> = _liveState

    override var onReadLine: ((String) -> Unit)? = null

    override suspend fun connect(target: ConnectionTarget) {
        check(state is RobotService.State.Disconnected) { "Can only connect when disconnected" }
        state = RobotService.State.Connecting()
        state = try {
            connection.connect(target)
            GlobalScope.launch {
                Log.d("BTC", "Started reader worker")
                val reader = InputStreamReader(connection.inputStream)
                try {
                    reader.forEachLine { line ->
                        // Process socket's input here
                        Log.d("BTC", "Received \"$line\"")
                        onReadLine?.invoke(line)
                            ?: Log.d("BTC", "No readLine callback")
                    }
                } catch (e: IOException) {
                    Log.d("BTC", "Socket input stream ended")
                }
                Log.d("BTC", "Reader worker died")
            }
            RobotService.State.Connected()
        } catch (e: IOException) {
            RobotService.State.Disconnected(RobotService.DisconnectedBy.Error)
        }
    }

    override suspend fun disconnect() {
        check(state !is RobotService.State.Disconnected
                || state !is RobotService.State.Disconnecting) { "Already disconnected" }
        state = RobotService.State.Disconnecting()
        try {
            connection.close()
        } finally {
            state = RobotService.State.Disconnected(RobotService.DisconnectedBy.Client)
        }
    }

    override suspend fun write(string: String, appendNewLine: Boolean) {
        check(state is RobotService.State.Connected) {"Cannot write while not connected"}
        try {
            val writer = OutputStreamWriter(connection.outputStream)
            Log.d("BTC", "Writing $string")
            writer.write(string + if (appendNewLine) "\n" else "")
            writer.flush()
        } catch (e: IOException) {
            Log.d("BTC", "Broken pipe, setting state to Disconnected")
            disconnect()
        }
    }

    override suspend fun runProgram(program: Program) {
        check(state is RobotService.State.Connected) { "Cannot run programs while not connected" }
        val message = program.body
        write(message)
    }
}