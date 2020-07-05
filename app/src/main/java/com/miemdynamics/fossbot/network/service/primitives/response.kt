package com.miemdynamics.fossbot.network.service.primitives

import com.miemdynamics.fossbot.data.entity.Program
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

@Serializable
sealed class Response {
    @Serializable
    @SerialName("list")
    data class ListCommands(val commands: ArrayList<Program>): Response()
}

fun deserializeResponse(string: String): Response {
    val json = Json{}
    return json.decodeFromString(Response.serializer(), string)
}
