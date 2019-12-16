package com.miemdynamics.fossbot.network

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.Closeable
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException
import java.lang.IllegalStateException
import java.util.*

const val DEFAULT_UUID = "4344ca40-5e11-4cf8-bbc2-132c8a3a5897"

/**
 * A bluetooth serial port profile connection to the [device]
 */
class BluetoothSPP: Closeable {
    private var socket: BluetoothSocket? = null

    var onConnectionEstablished: (() -> Unit)? = null
    var onConnectionFailed: (() -> Unit)? = null

    suspend fun connect(device: BluetoothDevice) {
        withContext(Dispatchers.IO) {
            try {
                socket = createSocket(device)
                socket?.connect()
                socket?.let {
                    if (it.isConnected) {
                        onConnectionEstablished?.invoke()
                    }
                }
            } catch (e: IOException) {
                onConnectionFailed?.invoke()
            }
        }
    }

    fun isConnected() = socket?.isConnected ?: false

    suspend fun write(buffer: ByteArray) {
        if (isConnected()) {
            withContext(Dispatchers.IO) {
                DataOutputStream(socket!!.outputStream).write(buffer)
            }
        } else {
            throw IllegalStateException("Cannot write to disconnected socket")
        }
    }

    suspend fun read(): ByteArray {
        if (isConnected()) {
            return withContext(Dispatchers.IO) {
                DataInputStream(socket!!.inputStream).readBytes()
            }
        } else {
            throw IllegalStateException("Cannot read from disconnected socket")
        }
    }

    override fun close() {
        socket?.close()
    }

    private fun createSocket(device: BluetoothDevice): BluetoothSocket {
        Log.d("spp", "creating socket for ${device.name
        }")
        device.uuids?.forEach {
            Log.d("spp", "found UUID=\"$it\"")
        } ?: Log.d("spp", "no device selected")
        val uuid = device.uuids?.firstOrNull()?.uuid ?: UUID.fromString(DEFAULT_UUID)
        return device.createRfcommSocketToServiceRecord(uuid)
    }
}