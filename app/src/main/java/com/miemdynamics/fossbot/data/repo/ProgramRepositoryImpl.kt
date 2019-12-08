package com.miemdynamics.fossbot.data.repo

import androidx.lifecycle.LiveData
import com.miemdynamics.fossbot.data.db.ProgramDao
import com.miemdynamics.fossbot.data.entity.Program

class ProgramRepositoryImpl(
    private val programDao: ProgramDao
) : ProgramRepository {
    override fun getAll(): LiveData<List<Program>> {
        return programDao.getAll()
    }

    override fun insert(program: Program) {
        programDao.insert(program)
    }
}