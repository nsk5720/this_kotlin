package com.nsk5720.projectmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nsk5720.projectmobile.databinding.ActivityCardRegBinding

class CardReg : AppCompatActivity() {
    val binding by lazy { ActivityCardRegBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val intent = Intent(this, CardInfo::class.java)
        binding.cardRegBtn1.setOnClickListener {
            startActivity(intent)
        }

        val intent2 = Intent(this, CardInfo::class.java)
        binding.cardRegBtn2.setOnClickListener {
            startActivity(intent2)
        }
        val intent3 = Intent(this, Map::class.java)
        binding.cardRegUntactRes.setOnClickListener {
            startActivity(intent3)
        }
        val intent4 = Intent(this, DiagnosisList::class.java)
        binding.cardRegContactRes.setOnClickListener {
            startActivity(intent4)

        }
        val intent5 = Intent(this, ChatListActivity::class.java)
        binding.cardRegDiagnosis.setOnClickListener {
            startActivity(intent5)
        }

        val intent6 = Intent(this, MyPage::class.java)
        binding.cardRegMyPage.setOnClickListener {
            startActivity(intent6)
        }

        val intent7 = Intent(this, Reservation::class.java)
        binding.cardRegBackBtn.setOnClickListener {
            startActivity(intent7)
        }

    }
}