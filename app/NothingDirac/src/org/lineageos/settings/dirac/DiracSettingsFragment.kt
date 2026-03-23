/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.settings.dirac

import android.content.Context
import android.os.Bundle

import androidx.preference.ListPreference
import androidx.preference.Preference

import com.android.settingslib.widget.MainSwitchPreference
import com.android.settingslib.widget.SettingsBasePreferenceFragment
import com.android.settingslib.widget.SliderPreference

import org.lineageos.settings.R

class DiracSettingsFragment : SettingsBasePreferenceFragment(),
    Preference.OnPreferenceChangeListener {

    companion object {
        const val PREF_ENABLE = "dirac_enable"
        const val PREF_SCENARIO = "dirac_scenario_pref"
        const val PREF_PRESET = "dirac_preset_pref"
        const val PREF_VOLUME = "dirac_volume_pref"
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.dirac_settings, rootKey)

        DiracUtils.initialize(requireContext())
        val enhancerEnabled = DiracUtils.isDiracEnabled(requireContext())

        findPreference<MainSwitchPreference>(PREF_ENABLE)?.let { switchBar ->
            switchBar.setOnPreferenceChangeListener(this)
            switchBar.isChecked = enhancerEnabled
        }

        findPreference<ListPreference>(PREF_SCENARIO)?.setOnPreferenceChangeListener(this)
        findPreference<ListPreference>(PREF_PRESET)?.setOnPreferenceChangeListener(this)

        findPreference<SliderPreference>(PREF_VOLUME)?.let { volumePreference ->
            volumePreference.setSliderIncrement(1)
            volumePreference.setHapticFeedbackMode(SliderPreference.HAPTIC_FEEDBACK_MODE_ON_TICKS)
            volumePreference.setTickVisible(true)
            volumePreference.setOnPreferenceChangeListener(this)
        }
    }

    override fun onPreferenceChange(preference: Preference, newValue: Any?): Boolean =
        when (preference.key) {
            PREF_ENABLE -> {
                DiracUtils.setEnabled(newValue as Boolean)
                true
            }
            PREF_SCENARIO -> {
                DiracUtils.setScenario(newValue.toString())
                true
            }
            PREF_PRESET -> {
                DiracUtils.setLevel(newValue.toString())
                true
            }
            PREF_VOLUME -> {
                DiracUtils.setVolume(newValue as Int)
                true
            }
            else -> false
        }
}
