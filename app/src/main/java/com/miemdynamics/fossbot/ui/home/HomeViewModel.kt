package com.miemdynamics.fossbot.ui.home

import androidx.lifecycle.*
import com.miemdynamics.fossbot.data.provider.PreferenceProvider
import com.miemdynamics.fossbot.network.service.RobotService

/**
 * A [ViewModel] for [HomeFragment].
 */
class HomeViewModel(
    val preferenceProvider: PreferenceProvider,
    val robotService: RobotService
) : ViewModel() {

}