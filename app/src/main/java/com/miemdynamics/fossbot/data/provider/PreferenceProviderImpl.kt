package com.miemdynamics.fossbot.data.provider

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.miemdynamics.fossbot.internal.BLUETOOTH_DEVICE
import com.miemdynamics.fossbot.network.connection.BluetoothTarget
import com.miemdynamics.fossbot.network.connection.ConnectionTarget

class PreferenceProviderImpl(private val context: Context) : PreferenceProvider {
    private val appContext = context.applicationContext
    override val preferences = PreferenceManager.getDefaultSharedPreferences(appContext)!!

    override val connectionTarget: ConnectionTarget?
        get() {
            val address = preferences.getString(BLUETOOTH_DEVICE, null)
            return address?.let {
                BluetoothTarget(BluetoothAdapter.getDefaultAdapter().getRemoteDevice(it))
            }
        }
}