package com.miemdynamics.fossbot.network.connection

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.lang.IllegalStateException
import java.util.*

const val DEFAULT_UUID = "DDDDDDDD-DDDD-DDDD-DDDD-DDDDDDDDDDDD"

class BluetoothTarget(val device: BluetoothDevice):
    ConnectionTarget

class BluetoothConnection: Connection {
    private var socket: BluetoothSocket? = null

    override suspend fun connect(target: ConnectionTarget) {
        withContext(Dispatchers.IO) {
            require(target is BluetoothTarget) { "target must be of type BluetoothTarget" }
            socket = createSocket(target.device)
            socket!!.connect()
        }
    }

    override suspend fun close() {
        withContext(Dispatchers.IO) {
            socket?.close() ?: throw IllegalStateException("Already closed")
        }
    }

    override val inputStream: InputStream
        get() = checkNotNull(socket?.inputStream) { "Socket closed" }

    override val outputStream: OutputStream
        get() = checkNotNull(socket?.outputStream) { "Socket closed" }

    private fun createSocket(device: BluetoothDevice): BluetoothSocket {
        val uuid = UUID.fromString(DEFAULT_UUID)
        Log.d("BTC", device.uuids.map { it.uuid.toString() }.joinToString("\n"))
        return device.createRfcommSocketToServiceRecord(uuid)
    }
}