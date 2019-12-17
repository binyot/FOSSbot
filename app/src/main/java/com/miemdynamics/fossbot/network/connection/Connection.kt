package com.miemdynamics.fossbot.network.connection

import java.io.InputStream
import java.io.OutputStream

interface Connection {
    suspend fun connect (target: ConnectionTarget)
    suspend fun close()

    val inputStream: InputStream
    val outputStream: OutputStream
}