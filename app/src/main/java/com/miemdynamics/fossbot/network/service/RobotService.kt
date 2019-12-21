package com.miemdynamics.fossbot.network.service

import androidx.lifecycle.LiveData
import com.miemdynamics.fossbot.network.connection.ConnectionTarget
import java.io.InputStream
import java.io.OutputStream

interface RobotService {
    val liveState: LiveData<State>

    suspend fun connect(target: ConnectionTarget)
    suspend fun disconnect()

    val inputStream: InputStream
    val outputStream: OutputStream

    sealed class State {
        class Disconnected(val reason: DisconnectedBy): State()
        class Disconnecting: State()
        class Connecting: State()
        class Connected: State()
    }

    enum class DisconnectedBy {
        Server,
        Client,
        Error
    }
}