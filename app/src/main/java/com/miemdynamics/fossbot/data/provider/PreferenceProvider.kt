package com.miemdynamics.fossbot.data.provider

import android.bluetooth.BluetoothDevice

/**
 * User preference provider.
 */
interface PreferenceProvider {
    fun getBluetoothDevice(): BluetoothDevice?
}