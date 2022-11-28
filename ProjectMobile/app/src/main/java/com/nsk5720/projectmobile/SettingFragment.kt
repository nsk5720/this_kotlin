package com.nsk5720.projectmobile

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
// 마이페이지 설정기능
class SettingFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
    }
}