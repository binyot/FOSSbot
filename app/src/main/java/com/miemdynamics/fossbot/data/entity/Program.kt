package com.miemdynamics.fossbot.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

/**
 * Holds a program
 * @param name a unique program name
 * @param body a body of an executable program
 */
@Entity(tableName="program_table")
@Serializable
data class Program(
    @PrimaryKey
    val name: String,
    val body: String
)