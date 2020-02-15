package com.miemdynamics.fossbot.ui.control

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miemdynamics.fossbot.data.entity.Program
import com.miemdynamics.fossbot.data.repo.ProgramRepository
import com.miemdynamics.fossbot.network.service.RobotService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ControlViewModel(
    private val robotService: RobotService,
    private val programRepository: ProgramRepository
): ViewModel() {
    val presets = arrayListOf(Preset("Default"), Preset("A"), Preset("B"))
    var selectedPreset: Preset = presets.first()

    fun getProgramsLive() = programRepository.getAllLive()

    fun getPrograms() = viewModelScope.async {
        programRepository.getAll()
    }

    fun runProgram(program: Program) = viewModelScope.launch(Dispatchers.IO) {
        robotService.runProgram(program)
    }
}