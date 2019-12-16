package com.miemdynamics.fossbot.network

import android.bluetooth.BluetoothDevice
import androidx.lifecycle.LiveData

interface BluetoothConnection {
    suspend fun connect(device: BluetoothDevice)
    suspend fun disconnect()
    suspend fun write(buffer: ByteArray)
    suspend fun read(): ByteArray

    val liveState: LiveData<State>

    sealed class State {
        class Disconnected(val reason: DisconnectReason): State()
        class Connected: State()
        class Connecting: State()
    }

    enum class DisconnectReason {
        ConnectionFailed,
        Normal
    }
}