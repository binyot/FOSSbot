package com.miemdynamics.fossbot.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.miemdynamics.fossbot.data.provider.PreferenceProvider
import com.miemdynamics.fossbot.network.service.RobotService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.*

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
        viewModelScope.launch(Dispatchers.IO) {
            robotService.connect(target)
        }
    }

    fun disconnect() {
        viewModelScope.launch {
            robotService.disconnect()
        }
    }

    private val _receivedText = MutableLiveData<String>()
    val receivedText: LiveData<String> = _receivedText

    fun write(message: String) {
        viewModelScope.launch {
            if (robotService.liveState.value is RobotService.State.Connected) {
                val writer = OutputStreamWriter(robotService.outputStream)
                writer.write(message + "\n")
                writer.flush()
            }
        }
    }
}