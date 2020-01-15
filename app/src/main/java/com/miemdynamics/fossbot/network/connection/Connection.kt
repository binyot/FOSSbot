package com.miemdynamics.fossbot.network.connection

import java.io.InputStream
import java.io.OutputStream

/**
 * A wrapper for socket connections.
 */
interface Connection {
    /**
     * Attempts to connect to a specified [target].
     */
    suspend fun connect (target: ConnectionTarget)

    /**
     * Closes connection if currently connected.
     * Throws [IllegalStateException] unless currently connected
     */
    suspend fun close()

    /**
     * Returns implementation's input stream.
     * Throws [IllegalStateException] if currently not connected.
     */
    val inputStream: InputStream

    /**
     * Returns implementation's output stream.
     * Throws [IllegalStateException] if currently not connected.
     */
    val outputStream: OutputStream
}