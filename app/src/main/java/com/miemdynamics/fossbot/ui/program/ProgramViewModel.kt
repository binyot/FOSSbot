package com.miemdynamics.fossbot.ui.program

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miemdynamics.fossbot.data.entity.Program
import com.miemdynamics.fossbot.data.provider.PreferenceProvider
import com.miemdynamics.fossbot.data.repo.ProgramRepository
import com.miemdynamics.fossbot.network.service.RobotService
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

    val connectionState = robotService.liveState

    fun insert(program: Program) {
        viewModelScope.launch {
            programRepository.insert(program)
        }
    }

    fun addProgram() {
        viewModelScope.launch {
            programRepository.insert(
                Program("inserted program #${getPrograms().await().size}",
                    "is a program that has been inserted")
            )
        }
    }

    fun runProgram(program: Program) {
        viewModelScope.launch {
            robotService.runProgram(program)
        }
    }

    fun deletePrograms(programs: List<Program>) {
        viewModelScope.launch {
            programs.forEach {
                programRepository.delete(it)
            }
        }
    }

    val runProgramConfirmEnabled = preferenceProvider.runProgramConfirmEnabled
}