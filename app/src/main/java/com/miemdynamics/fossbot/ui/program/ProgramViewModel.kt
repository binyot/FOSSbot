package com.miemdynamics.fossbot.ui.program

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miemdynamics.fossbot.data.entity.Program
import com.miemdynamics.fossbot.data.provider.PreferenceProvider
import com.miemdynamics.fossbot.data.repo.ProgramRepository
import com.miemdynamics.fossbot.network.service.RobotService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * A [ViewModel] for [ProgramFragment].
 */
class ProgramViewModel(
    private val programRepository: ProgramRepository,
    private val robotService: RobotService,
    private val preferenceProvider: PreferenceProvider): ViewModel() {

    fun getProgramsLive() = programRepository.getAllLive()

    fun getPrograms() = viewModelScope.async {
        programRepository.getAll()
    }

    fun connectionStateLive() = robotService.liveState


    fun addProgram(program: Program) {
        GlobalScope.launch {
            val count = getPrograms().await().size
            programRepository.insert(program)
        }
    }

    fun runProgram(program: Program) {
        GlobalScope.launch {
            robotService.runProgram(program)
        }
    }

    fun createProgram(program: Program) {
        GlobalScope.launch {
            robotService.uploadProgram(program)
        }
    }

    fun uploadAllPrograms() {
        GlobalScope.launch {
            // TODO: Forward uploading to service?
            val programs = programRepository.getAll()
            programs.forEach {
                robotService.uploadProgram(it)
            }
        }
    }

    fun uploadProgram(program: Program) {
        GlobalScope.launch {
            robotService.uploadProgram(program)
        }
    }

    fun deletePrograms(programs: List<Program>) {
        GlobalScope.launch {
            programs.forEach {
                programRepository.delete(it)
            }
        }
    }

    fun downloadPrograms() {
        GlobalScope.launch {
            robotService.downloadPrograms()
        }
    }

    val runProgramConfirmEnabled = preferenceProvider.runProgramConfirmEnabled
}