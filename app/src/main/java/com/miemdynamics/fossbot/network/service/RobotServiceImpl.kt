package com.miemdynamics.fossbot.network.service

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.miemdynamics.fossbot.data.entity.Program
import com.miemdynamics.fossbot.data.repo.ProgramRepository
import com.miemdynamics.fossbot.network.connection.Connection
import com.miemdynamics.fossbot.network.connection.ConnectionTarget
import kotlinx.coroutines.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonDecodingException
import java.io.*
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.util.concurrent.ConcurrentLinkedQueue

@Serializable
sealed class Request(val tag: String) {
    @Serializable
    class ListCommands: Request("command_list")

    @Serializable
    class RunCommand(val name: String): Request("run_command")

    @Serializable
    class CreateCommand(val name: String, val body: String): Request("create_command")
}

@Serializable
sealed class Response {
    @Serializable
    class ListCommands(val commands: List<String>): Response()
}

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
        val message = Json.stringify(Request.RunCommand.serializer(), request)
        Log.d("RJSON", "json: $message")
        write(message)
    }

    override suspend fun downloadPrograms() {
        check(state is RobotService.State.Connected) {"Cannot download while not connected"}
        val request = Request.ListCommands()
        val message = Json.stringify(Request.ListCommands.serializer(), request)
        write(message)
    }

    override suspend fun uploadPrograms() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun handleInputStream(reader: InputStreamReader) {
        reader.forEachLine {
            try {
                val obj = Json.parseJson(it)
                val tag = obj.jsonObject.get("tag")?.toString()
                requireNotNull(tag)
                val responseClass = when(tag) {
                    "command_list" -> Response.ListCommands
                    else -> throw IllegalStateException()
                }
                val response = Json.parse(responseClass.serializer(), it)
                handleResponse(response)
            } catch (e: JsonDecodingException) {
                Log.e("RJSON", "Received malformed JSON")
            } catch (e: IllegalArgumentException) {
                Log.e("RJSON", "Received JSON without a tag")
            } catch (e: IllegalStateException) {
                Log.e("RJSON", "Received JSON with an unknown tag")
            } catch (e: Exception) {
                Log.e("RJSON", "Unknown response parsing error")
            }
        }
    }

    private fun handleResponse(response: Response) {
        when(response) {
            is Response.ListCommands -> {
                GlobalScope.launch {
                    response.commands.forEach { name ->
                        repository.insert(Program(name = name, body = ""))
                    }
                }
            }
            else -> Log.e("RJSON", "Received generic response")
        }
    }
}