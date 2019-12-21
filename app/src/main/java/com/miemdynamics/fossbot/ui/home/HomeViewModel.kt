package com.miemdynamics.fossbot.ui.home

import androidx.lifecycle.*
import com.miemdynamics.fossbot.data.provider.PreferenceProvider
import com.miemdynamics.fossbot.network.service.RobotService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A [ViewModel] for [HomeFragment].
 */
class HomeViewModel(
    val preferenceProvider: PreferenceProvider,
    private val robotService: RobotService
) : ViewModel() {
    val connectionState = robotService.liveState

    fun connect() {
        val target = preferenceProvider.connectionTarget
        checkNotNull(target) { "Connection target is null" }
        viewModelScope.launch {
            robotService.connect(target)
        }
    }

    fun disconnect() {
        viewModelScope.launch {
            robotService.disconnect()
        }
    }
}