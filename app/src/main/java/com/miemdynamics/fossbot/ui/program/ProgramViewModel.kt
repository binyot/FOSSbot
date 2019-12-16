package com.miemdynamics.fossbot.ui.program

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miemdynamics.fossbot.data.entity.Program
import com.miemdynamics.fossbot.data.repo.ProgramRepository
import kotlinx.coroutines.launch

/**
 * A [ViewModel] for [ProgramFragment].
 */
class ProgramViewModel(private val programRepository: ProgramRepository): ViewModel() {
    fun getPrograms() = programRepository.getAll()

    fun insert(program: Program) {
        viewModelScope.launch {
            programRepository.insert(program)
        }
    }


}