package com.miemdynamics.fossbot.network.service

import androidx.lifecycle.LiveData
import com.miemdynamics.fossbot.data.entity.Program
import com.miemdynamics.fossbot.network.connection.ConnectionTarget
import java.io.InputStream
import java.io.OutputStream

/**
 * Used to interface with a robot's service
 */
interface RobotService {
    /**
     * Indicates current service connection state
     */
    val liveState: LiveData<State>

    /**
     * Connect to a service.
     * Throws an [IllegalStateException] unless the current state is [RobotService.State.Disconnected].
     */
    suspend fun connect(target: ConnectionTarget)

    /**
     * Disconnect from a service.
     * Throws an [IllegalStateException] unless the current state is [RobotService.State.Connected].
     */
    suspend fun disconnect()

    /**
     * Sends a [program] run request.
     */
    suspend fun runProgram(program: Program)

    /**
     * Sends given [string] as-is.
     * Should only be used for testing
     */
    suspend fun write(string: String, appendNewLine: Boolean = true)

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