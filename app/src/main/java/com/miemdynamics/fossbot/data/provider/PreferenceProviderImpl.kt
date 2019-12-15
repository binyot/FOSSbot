package com.miemdynamics.fossbot.data.provider

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import androidx.preference.PreferenceManager

class PreferenceProviderImpl(context: Context) : PreferenceProvider {
    private val appContext = context.applicationContext
    private val preferences = PreferenceManager.getDefaultSharedPreferences(appContext)

    override fun getBluetoothDevice(): BluetoothDevice? {
        val address = preferences.getString("BLUETOOTH_DEVICE", null)
        if (address != null) {
            return BluetoothAdapter.getDefaultAdapter().getRemoteDevice(address)
        } else {
            return null
        }
    }
}