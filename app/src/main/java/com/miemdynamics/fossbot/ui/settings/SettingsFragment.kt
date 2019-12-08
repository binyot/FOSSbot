package com.miemdynamics.fossbot.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.miemdynamics.fossbot.R

/**
 * A special root Fragment for user preferences.
 */
class SettingsFragment : PreferenceFragmentCompat() {
    /**
     * Must use the res/xml/preferences.xml
     */
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}