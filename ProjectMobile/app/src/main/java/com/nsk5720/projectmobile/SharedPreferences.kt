package com.nsk5720.projectmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.preference.PreferenceManager
import com.nsk5720.projectmobile.databinding.ActivitySharedPreferencesBinding
// 마이페이지 설정기능
class SharedPreferences : AppCompatActivity(){
    val binding by lazy {ActivitySharedPreferencesBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val shared = PreferenceManager.getDefaultSharedPreferences(this)

        val checkboxValue = shared.getBoolean("key_add_shortcut", false)
        val switchValue = shared.getBoolean("key_switch_on", false)
        val name = shared.getString("key_edit_name", "")
        val selected = shared.getString("key_set_item", "")


    }
}