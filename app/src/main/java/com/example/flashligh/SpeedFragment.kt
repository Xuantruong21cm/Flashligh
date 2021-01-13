package com.example.flashligh

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SeekBarPreference

class SpeedFragment : PreferenceFragmentCompat() {
    companion object{
        lateinit var turn_on_time: SeekBarPreference
        lateinit var turn_off_time: SeekBarPreference
        lateinit var turn_on_sms: SeekBarPreference
        var seekbar: Int = 0
    }
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.speed_preferences, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundResource(R.drawable.custom_fragment)
        listView.overScrollMode = View.OVER_SCROLL_NEVER
        loadSettings()
        turn_on_time = findPreference<SeekBarPreference>("turn_on_time")!!
        turn_off_time = findPreference<SeekBarPreference>("turn_off_time")!!
        turn_on_sms = findPreference<SeekBarPreference>("turn_on_sms")!!
        turn_on_time.setOnPreferenceChangeListener(object : Preference.OnPreferenceChangeListener {
            override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
                loadSettings()

                return true
            }

        })
        turn_on_time.max = 150
        turn_off_time.max = 150
        turn_on_sms.max = 10
    }

    private fun loadSettings() {
        var sp = PreferenceManager.getDefaultSharedPreferences(context)
        seekbar = sp.getInt("turn_on_time", 10)
    }
}