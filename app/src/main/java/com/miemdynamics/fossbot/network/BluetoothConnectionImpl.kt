package com.miemdynamics.fossbot.network

import android.bluetooth.BluetoothDevice
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.lang.IllegalStateException

class BluetoothConnectionImpl: BluetoothConnection {

    override suspend fun connect(device: BluetoothDevice) {
        when(state) {
            is BluetoothConnection.State.Disconnected -> {
                state = BluetoothConnection.State.Connecting()
                spp.connect(device)
            }
            else -> throw IllegalStateException("Can connect only while disconnected")
        }
    }

    override suspend fun disconnect() {
        when(state) {
            is BluetoothConnection.State.Connected -> {
                state = BluetoothConnection.State.Disconnected(BluetoothConnection.DisconnectReason.Normal)
                spp.close()
            }
            else -> throw IllegalStateException("Can disconnect only while connected")
        }
    }

    override suspend fun write(buffer: ByteArray) {
        when(state) {
            is BluetoothConnection.State.Connected ->
                spp.write(buffer)
            else -> throw IllegalStateException("Can write only while connected")
        }
    }

    override suspend fun read(): ByteArray {
        return when(state) {
            is BluetoothConnection.State.Connected ->
                spp.read()
            else -> throw IllegalStateException("Can read only while connected")
        }
    }

    private val spp = BluetoothSPP()

    private var state: BluetoothConnection.State =
        BluetoothConnection.State.Disconnected(
            BluetoothConnection.DisconnectReason.Normal)
        set(newState) {
            field = newState
            _liveState.postValue(field)
        }

    private val _liveState = MutableLiveData<BluetoothConnection.State>(state)

    override val liveState: LiveData<BluetoothConnection.State> = _liveState

    init {
        spp.onConnectionFailed = {
            state = BluetoothConnection.State.Disconnected(
                BluetoothConnection.DisconnectReason.ConnectionFailed
            )
        }
        spp.onConnectionEstablished = {
            state = BluetoothConnection.State.Connected()
        }
    }
}