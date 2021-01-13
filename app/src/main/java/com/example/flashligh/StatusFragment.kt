package com.example.flashligh

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.provider.Telephony
import android.provider.Telephony.Sms.Intents.SMS_RECEIVED_ACTION
import android.telephony.TelephonyManager
import android.view.View
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreferenceCompat
import com.example.flashligh.receiver.Call_Receiver
import com.example.flashligh.receiver.SMS_Receiver

class StatusFragment : PreferenceFragmentCompat() {
    var incoming_call: Boolean = false
    var incoming_message: Boolean = false
    var notificaton: Boolean = false
    lateinit var incoming_call_Switch: SwitchPreferenceCompat
    lateinit var incoming_sms_Switch: SwitchPreferenceCompat
    lateinit var incoming_notification_Switch: SwitchPreferenceCompat
    lateinit var intentFilter_Call: IntentFilter
    lateinit var intentFilter_SMS: IntentFilter
    var callReceiver: Call_Receiver = Call_Receiver()
    var smsReceiver : SMS_Receiver = SMS_Receiver()

    override fun onStart() {
        super.onStart()
        intentFilter_Call = IntentFilter()
        intentFilter_SMS = IntentFilter()

        intentFilter_Call.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED)
        intentFilter_SMS.addAction(SMS_RECEIVED_ACTION)


    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.status_preferences, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundResource(R.drawable.custom_fragment)
        listView.overScrollMode = View.OVER_SCROLL_NEVER
        inintUI()
        loadSettings()
        loadStatus()

        incoming_call_Switch.setOnPreferenceClickListener(object :
            Preference.OnPreferenceClickListener {
            override fun onPreferenceClick(preference: Preference?): Boolean {
                loadSettings()
                if (incoming_call) {
                    requireContext().registerReceiver(callReceiver, intentFilter_Call)
                } else {
                    try {
                        requireContext().unregisterReceiver(callReceiver)
                    }catch (e : Exception){}
                }
                return true
            }
        })

        incoming_sms_Switch.setOnPreferenceClickListener(object : Preference.OnPreferenceClickListener{
            override fun onPreferenceClick(preference: Preference?): Boolean {
                loadSettings()
                if (incoming_message) {
                    requireContext().registerReceiver(smsReceiver, intentFilter_SMS)
                } else {
                    try {
                        requireContext().unregisterReceiver(smsReceiver)
                    }catch (e : Exception){}
                }

                return true
            }

        })


    }

    private fun loadStatus() {
        if (incoming_call == true) {
            incoming_call_Switch.isChecked = true
        } else {
            incoming_call_Switch.isChecked = false
        }

        if (incoming_message == true) {
            incoming_sms_Switch.isChecked = true
        } else {
            incoming_sms_Switch.isChecked = false
        }

        if (notificaton == true) {
            incoming_notification_Switch.isChecked = true
        } else {
            incoming_notification_Switch.isChecked = false
        }


    }

    private fun inintUI() {
        incoming_call_Switch = findPreference<SwitchPreferenceCompat>("incoming_call")!!
        incoming_sms_Switch = findPreference<SwitchPreferenceCompat>("incoming_message")!!
        incoming_notification_Switch = findPreference<SwitchPreferenceCompat>("notificaton")!!
    }

    private fun loadSettings() {
        var sp = PreferenceManager.getDefaultSharedPreferences(context)
        incoming_call = sp.getBoolean("incoming_call", false)
        incoming_message = sp.getBoolean("incoming_message", false)
        notificaton = sp.getBoolean("notificaton",false)
    }

}