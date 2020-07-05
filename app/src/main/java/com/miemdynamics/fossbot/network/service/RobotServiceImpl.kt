package com.miemdynamics.fossbot.network.service

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.miemdynamics.fossbot.data.entity.Program
import com.miemdynamics.fossbot.data.repo.ProgramRepository
import com.miemdynamics.fossbot.network.connection.Connection
import com.miemdynamics.fossbot.network.connection.ConnectionTarget
import com.miemdynamics.fossbot.network.service.primitives.Request
import com.miemdynamics.fossbot.network.service.primitives.Response
import com.miemdynamics.fossbot.network.service.primitives.deserializeResponse
import com.miemdynamics.fossbot.network.service.primitives.serialize
import kotlinx.coroutines.*
import kotlinx.serialization.json.JsonException
import java.io.*

class RobotServiceImpl(
    private val connection: Connection,
    private val repository: ProgramRepository
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

    override suspend fun connect(target: ConnectionTarget) {
        check(state is RobotService.State.Disconnected) { "Can only connect when disconnected" }
        state = RobotService.State.Connecting()
        state = try {
            connection.connect(target)
            GlobalScope.launch {
                Log.d("BTC", "Started reader worker")
                val reader = InputStreamReader(connection.inputStream)
                try {
                    handleInputStream(reader)
                } catch (e: IOException) {
                    Log.d("BTC", "Socket input stream ended")
                }
                Log.d("BTC", "Reader worker died")
            }
            RobotService.State.Connected()
        } catch (e: IOException) {
            Log.d("TCP", "Error connecting: ${e.toString()}")
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
        val request = Request.RunCommand(program.name)
        write(request.serialize())
    }

    override suspend fun uploadProgram(program: Program) {
        check(state is RobotService.State.Connected) { "Cannot upload programs while not connected" }
        val request = Request.CreateCommand(program.name, program.body)
        write(request.serialize())
    }

    override suspend fun downloadPrograms() {
        check(state is RobotService.State.Connected) {"Cannot download while not connected"}
        val request = Request.ListCommands()
        write(request.serialize())
    }

    private fun handleInputStream(reader: InputStreamReader) {
        reader.forEachLine {
            Log.e("RJSON", "Received request \"$it\"")
            try {
                val response = deserializeResponse(it)
                GlobalScope.launch {
                    handleResponse(response)
                }
            } catch (e: JsonException) {
                Log.e("RJSON", "Ill-formed response received")
            } catch (e: Exception) {
                Log.e("RJSON", "Unknown response parsing error")
            }
        }
    }

    private fun CoroutineScope.handleResponse(response: Response) = launch {
        Log.d("RJSON", "Handling $response.type response")
        when(response) {
            is Response.ListCommands -> {
                val programs = response.commands
                programs.forEach {
                    repository.insert(it)
                }
            }
        }
    }
}