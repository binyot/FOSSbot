package com.miemdynamics.fossbot.ui.home

import android.content.SharedPreferences
import androidx.lifecycle.*
import com.miemdynamics.fossbot.data.provider.PreferenceProvider
import com.miemdynamics.fossbot.internal.BLUETOOTH_DEVICE
import com.miemdynamics.fossbot.network.BluetoothConnection
import kotlinx.coroutines.launch

/**
 * A [ViewModel] for [HomeFragment].
 */
class HomeViewModel(
    val preferenceProvider: PreferenceProvider,
    val btConnection: BluetoothConnection
) : ViewModel() {

}