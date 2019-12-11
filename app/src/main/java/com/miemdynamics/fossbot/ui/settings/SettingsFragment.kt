package com.miemdynamics.fossbot.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.miemdynamics.fossbot.R
import com.miemdynamics.fossbot.internal.intPreference

/**
 * A special root [PreferenceFragmentCompat] for user preferences.
 */
class SettingsFragment : PreferenceFragmentCompat() {
    /**
     * Must use the res/xml/preferences.xml
     */
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}