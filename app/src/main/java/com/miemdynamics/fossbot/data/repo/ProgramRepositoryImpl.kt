package com.miemdynamics.fossbot.data.repo

import androidx.lifecycle.LiveData
import com.miemdynamics.fossbot.data.db.ProgramDao
import com.miemdynamics.fossbot.data.entity.Program
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProgramRepositoryImpl(
    private val programDao: ProgramDao
) : ProgramRepository {
    override fun getAll(): LiveData<List<Program>> {
        return programDao.getAll()
    }

    override suspend fun insert(program: Program) {
        withContext(Dispatchers.Main) {
            programDao.insert(program)
        }
    }
}