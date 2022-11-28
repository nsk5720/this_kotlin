package com.nsk5720.projectmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nsk5720.projectmobile.databinding.ActivityJoin2Binding

class Join2 : AppCompatActivity() {
    val binding by lazy { ActivityJoin2Binding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /*val intent = Intent(this, Login::class.java)
        binding.joinCompletionBtn.setOnClickListener {
            startActivity(intent)
        }

        val intent2 = Intent(this, Login::class.java)
        binding.startBtn3.setOnClickListener {
            startActivity(intent2)
        }*/

    }
}