package com.miemdynamics.fossbot.network.service

import androidx.lifecycle.LiveData
import com.miemdynamics.fossbot.data.entity.Program
import com.miemdynamics.fossbot.network.connection.ConnectionTarget

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
     * Sends given [string] as-is.
     * Should only be used for testing
     */
    suspend fun write(string: String, appendNewLine: Boolean = true)

    /**
     * Sends a [program] run request.
     */
    suspend fun runProgram(program: Program)

    /**
     * Sends a [program] create request
     */
    suspend fun uploadProgram(program: Program)

    /**
     * Sends a [program] list request, then adds programs from the list to the database
     */
    suspend fun downloadPrograms()

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