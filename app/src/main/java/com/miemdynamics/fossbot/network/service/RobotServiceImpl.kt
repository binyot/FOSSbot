package com.miemdynamics.fossbot.network.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.miemdynamics.fossbot.network.connection.Connection
import com.miemdynamics.fossbot.network.connection.ConnectionTarget
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

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

    override val inputStream: InputStream
        get() = connection.inputStream

    override val outputStream: OutputStream
        get() = connection.outputStream

    override suspend fun connect(target: ConnectionTarget) {
        check(state is RobotService.State.Disconnected) { "Can only connect when disconnected" }
        state = RobotService.State.Connecting()
        state = try {
            connection.connect(target)
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
}