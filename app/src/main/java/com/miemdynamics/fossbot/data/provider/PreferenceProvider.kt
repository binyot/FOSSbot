package com.miemdynamics.fossbot.data.provider

import android.bluetooth.BluetoothDevice
import android.content.SharedPreferences
import com.miemdynamics.fossbot.network.connection.ConnectionTarget

/**
 * User preference provider.
 */
interface PreferenceProvider {
    val preferences: SharedPreferences
    val connectionTarget: ConnectionTarget?
}