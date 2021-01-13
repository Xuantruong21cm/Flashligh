package com.example.flashligh

import android.os.Bundle
import android.view.View
import androidx.preference.PreferenceFragmentCompat

class ModeFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.mode_preference, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundResource(R.drawable.custom_fragment)
        listView.overScrollMode = View.OVER_SCROLL_NEVER
    }
}