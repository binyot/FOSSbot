package com.miemdynamics.fossbot.data.repo

import androidx.lifecycle.LiveData
import com.miemdynamics.fossbot.data.entity.Program

/**
 * A repository for [Program]
 */
interface ProgramRepository {
    /**
     * @return a live list of all programs
     */
    fun getAllLive(): LiveData<List<Program>>

    /**
     * @return a list of all programs
     */
    suspend fun getAll(): List<Program>

    /**
     * Insert the [program] in the database
     */
    suspend fun insert(program: Program)

    /**
     * Delete the [program]
     */
    suspend fun delete(program: Program)
}