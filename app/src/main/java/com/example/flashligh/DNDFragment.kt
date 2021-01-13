package com.example.flashligh

import android.os.Bundle
import android.view.View
import androidx.preference.PreferenceFragmentCompat

class DNDFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundResource(R.drawable.custom_fragment)
        listView.overScrollMode = View.OVER_SCROLL_NEVER
    }
}