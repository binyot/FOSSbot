package com.miemdynamics.fossbot.data.provider

import android.bluetooth.BluetoothDevice
import android.content.SharedPreferences

/**
 * User preference provider.
 */
interface PreferenceProvider {
    fun getPreferences(): SharedPreferences

    fun getBluetoothDevice(): BluetoothDevice?
}