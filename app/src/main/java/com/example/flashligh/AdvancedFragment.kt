package com.example.flashligh

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.View
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreferenceCompat
import com.example.flashligh.receiver.Call_Receiver
import com.example.flashligh.receiver.Screen_Receiver

class AdvancedFragment : PreferenceFragmentCompat() {
    var screen_status : Boolean = false
    lateinit var screen_Switch: SwitchPreferenceCompat
    var screenReceiver : Screen_Receiver = Screen_Receiver()
    lateinit var intentFilter_screen: IntentFilter

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.advanced_preferences, rootKey)
    }

    override fun onStart() {
        super.onStart()
        intentFilter_screen = IntentFilter()
        intentFilter_screen.addAction(Intent.ACTION_SCREEN_ON)
        intentFilter_screen.addAction(Intent.ACTION_SCREEN_OFF)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundResource(R.drawable.custom_fragment)
        listView.overScrollMode = View.OVER_SCROLL_NEVER
        initUI()
        loadSettings()
        loadStatus()

        screen_Switch.setOnPreferenceClickListener(object :
            Preference.OnPreferenceClickListener {
            override fun onPreferenceClick(preference: Preference?): Boolean {
                loadSettings()
                if (screen_status) {
                    requireContext().registerReceiver(screenReceiver, intentFilter_screen)
                } else {
                    try {
                        requireContext().unregisterReceiver(screenReceiver)
                    }catch (e : Exception){}
                }
                return true
            }
        })

    }

    private fun loadStatus() {
        if (screen_status == true) {
            screen_Switch.isChecked = true
        } else {
            screen_Switch.isChecked = false
        }
    }

    private fun initUI() {
        screen_Switch = findPreference<SwitchPreferenceCompat>("screen_open")!!
    }
    private fun loadSettings() {
        var sp = PreferenceManager.getDefaultSharedPreferences(context)
        screen_status = sp.getBoolean("screen_open",false)
    }
}