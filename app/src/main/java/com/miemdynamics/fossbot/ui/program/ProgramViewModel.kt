package com.miemdynamics.fossbot.ui.program

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miemdynamics.fossbot.data.entity.Program
import com.miemdynamics.fossbot.data.provider.PreferenceProvider
import com.miemdynamics.fossbot.data.repo.ProgramRepository
import com.miemdynamics.fossbot.network.service.RobotService
import kotlinx.coroutines.launch

/**
 * A [ViewModel] for [ProgramFragment].
 */
class ProgramViewModel(
    private val programRepository: ProgramRepository,
    private val robotService: RobotService,
    private val preferenceProvider: PreferenceProvider): ViewModel() {
    fun getPrograms() = programRepository.getAll()

    val connectionState = robotService.liveState
    var selectModeEnabled = false
    var previousSelectionState = false

    fun insert(program: Program) {
        viewModelScope.launch {
            programRepository.insert(program)
        }
    }

    fun runProgram(program: Program) {
        viewModelScope.launch {
            robotService.runProgram(program)
        }
    }

    val runProgramConfirmEnabled = preferenceProvider.runProgramConfirmEnabled
}