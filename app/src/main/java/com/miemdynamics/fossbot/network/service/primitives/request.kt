package com.miemdynamics.fossbot.network.service.primitives

import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

@Serializable
sealed class Request {
    @Serializable
    @SerialName("list")
    class ListCommands: Request()

    @Serializable
    @SerialName("run")
    class RunCommand(val name: String): Request()

    @Serializable
    @SerialName("create")
    class CreateCommand(val name: String, val body: String): Request()
}

fun Request.serialize(): String {
    val json = Json{}
    return json.encodeToString(Request.serializer(), this)
}
