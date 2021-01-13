package com.example.flashligh

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {
    lateinit var power_shared: SharedPreferences
    var PREFS_NAME: String = "status_power"
    var status_power: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        power_shared = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        status_power = power_shared.getBoolean("Power_status", true)

        if (power_shared != null) {
            if (status_power == true) {
                root.img_power.setImageResource(R.drawable.power_off)
            } else {
                root.img_power.setImageResource(R.drawable.power_on)
            }
        } else {
            status_power = false
            root.img_power.setImageResource(R.drawable.power_on)
        }

        root.img_power.setOnClickListener {
            if (status_power == true) {
                root.img_power.setImageResource(R.drawable.power_on)
                status_power = false
                statusPower(status_power)
            }else{
                root.img_power.setImageResource(R.drawable.power_off)
                status_power = true
                statusPower(status_power)
            }

        }

        return root
    }


    fun statusPower(boolean: Boolean) {
        var editor: SharedPreferences.Editor = power_shared.edit()
        editor.putBoolean("Power_status", boolean)
        editor.apply()
    }

}