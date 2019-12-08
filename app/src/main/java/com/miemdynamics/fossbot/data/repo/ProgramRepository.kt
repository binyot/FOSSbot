package com.miemdynamics.fossbot.data.repo

import androidx.lifecycle.LiveData
import com.miemdynamics.fossbot.data.entity.Program

/**
 * A repository for [Program]
 */
interface ProgramRepository {
    /**
     * @return a list of all programs
     */
    fun getAll(): LiveData<List<Program>>

    /**
     * Insert a [program] in the database
     */
    fun insert(program: Program)
}