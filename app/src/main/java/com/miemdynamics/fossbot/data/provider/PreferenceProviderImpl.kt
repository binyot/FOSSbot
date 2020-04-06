package com.miemdynamics.fossbot.data.provider

import android.bluetooth.BluetoothAdapter
import android.content.Context
import androidx.preference.PreferenceManager
import com.miemdynamics.fossbot.internal.BLUETOOTH_DEVICE
import com.miemdynamics.fossbot.internal.IP_ADDRESS
import com.miemdynamics.fossbot.internal.PORT
import com.miemdynamics.fossbot.internal.RUN_PROGRAM_CONFIRM
import com.miemdynamics.fossbot.network.connection.BluetoothTarget
import com.miemdynamics.fossbot.network.connection.ConnectionTarget
import com.miemdynamics.fossbot.network.connection.TcpTarget
import java.lang.Exception
import java.net.InetAddress

class PreferenceProviderImpl(private val context: Context) : PreferenceProvider {
    private val appContext = context.applicationContext
    override val preferences = PreferenceManager.getDefaultSharedPreferences(appContext)!!

    override val connectionTarget: ConnectionTarget?
        get() {
            val addressString = preferences.getString(IP_ADDRESS, null)
            val port = preferences.getString(PORT, "1489")!!.toInt()
            val address = try {
                addressString?.let { TcpTarget(InetAddress.getByName(it), port) }
            } catch (e: Exception) { null }
            return address
        }
//        get() {
//            val address = preferences.getString(BLUETOOTH_DEVICE, null)
//            return address?.let {
//                BluetoothTarget(BluetoothAdapter.getDefaultAdapter().getRemoteDevice(it))
//            }
//        }

    override val runProgramConfirmEnabled: Boolean
        get() = preferences.getBoolean(RUN_PROGRAM_CONFIRM, true)
}