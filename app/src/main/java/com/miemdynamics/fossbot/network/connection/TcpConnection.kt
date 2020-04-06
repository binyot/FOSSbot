package com.miemdynamics.fossbot.network.connection

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.OutputStream
import java.net.InetAddress
import java.net.Socket

class TcpTarget(val ipAddress: InetAddress, val port: Int):
    ConnectionTarget

class TcpConnection : Connection {
    private var socket: Socket? = null

    override suspend fun connect(target: ConnectionTarget) {
        withContext(Dispatchers.IO) {
            require(target is TcpTarget) { "target must be of type TcpTarget" }
            Log.d("TCP", "Connecting to ${target.ipAddress}:${target.port}")
            socket = Socket(target.ipAddress, target.port)
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

}