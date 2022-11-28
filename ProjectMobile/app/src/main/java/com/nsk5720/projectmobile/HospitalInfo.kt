package com.nsk5720.projectmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nsk5720.projectmobile.databinding.ActivityHospitalInfoBinding
import kotlin.collections.Map

class HospitalInfo : AppCompatActivity() {
    val binding by lazy{ActivityHospitalInfoBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val intent = Intent(this, Map::class.java)
        binding.hiUntactRes.setOnClickListener {
            startActivity(intent)
        }
        val intent1 = Intent(this, DiagnosisList::class.java)
        binding.hiContactRes.setOnClickListener {
            startActivity(intent1)

        }
        val intent2 = Intent(this, ChatListActivity::class.java)
        binding.hiDiagnosis.setOnClickListener {
            startActivity(intent2)
        }

        val intent3 = Intent(this, MyPage::class.java)
        binding.hiMyPage.setOnClickListener {
            startActivity(intent3)
        }

    }
}