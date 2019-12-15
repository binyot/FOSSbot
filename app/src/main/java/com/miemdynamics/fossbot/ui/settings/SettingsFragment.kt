package com.miemdynamics.fossbot.ui.settings

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.miemdynamics.fossbot.R
import com.miemdynamics.fossbot.internal.BLUETOOTH_DEVICE_ENABLE

/**
 * A special root [PreferenceFragmentCompat] for user preferences.
 */
class SettingsFragment : PreferenceFragmentCompat() {
    /**
     * Must use the res/xml/preferences.xml
     */
    private lateinit var bluetoothDeviceListPreference: ListPreference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bluetoothDeviceListPreference = findPreference("BLUETOOTH_DEVICE")!!
        fillBluetoothDeviceList()
    }

    private fun fillBluetoothDeviceList() {
        if (isBluetoothAvailable(activity!!)) {
            bluetoothDeviceListPreference.isEnabled = true
            val adapter = BluetoothAdapter.getDefaultAdapter()
            val bluetoothDevices = adapter.bondedDevices.toList()
            val names = bluetoothDevices
                .map { "${it.name} (${it.address})" as CharSequence }
                .toTypedArray()
            val addresses = bluetoothDevices
                .map { it.address as CharSequence }
                .toTypedArray()
            bluetoothDeviceListPreference.entries = names
            bluetoothDeviceListPreference.entryValues = addresses
        } else {
            bluetoothDeviceListPreference.isEnabled = false
        }
    }

    private fun isBluetoothAvailable(activity: Activity): Boolean {
        val adapter = BluetoothAdapter.getDefaultAdapter()
        if (adapter == null) {
            //TODO: handle lack of bt adapter
            return false
        } else {
            val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            activity.startActivityForResult(intent, BLUETOOTH_DEVICE_ENABLE)
            return true
        }
    }
}