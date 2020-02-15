package com.miemdynamics.fossbot.data.repo

import androidx.lifecycle.LiveData
import com.miemdynamics.fossbot.data.db.ProgramDao
import com.miemdynamics.fossbot.data.entity.Program
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProgramRepositoryImpl(
    private val programDao: ProgramDao
) : ProgramRepository {
    override fun getAllLive(): LiveData<List<Program>> {
        return programDao.getAllLive()
    }

    override suspend fun getAll(): List<Program> {
        return withContext(Dispatchers.IO) {
            return@withContext programDao.getAll()
        }
    }

    override suspend fun insert(program: Program) {
        withContext(Dispatchers.IO) {
            programDao.insert(program)
        }
    }

    override suspend fun delete(program: Program) {
        withContext(Dispatchers.IO) {
            programDao.deleteByName(program.name)
        }
    }
}