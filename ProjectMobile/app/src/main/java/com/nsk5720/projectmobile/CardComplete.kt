package com.nsk5720.projectmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nsk5720.projectmobile.databinding.ActivityCardCompleteBinding

class CardComplete : AppCompatActivity() {
    val binding by lazy { ActivityCardCompleteBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val intent = Intent(this, DiagnosisList ::class.java)
        binding.reservationBtn.setOnClickListener {
            startActivity(intent)
        }

        val intent2 = Intent(this, Map::class.java)
        binding.cardComUntactRes.setOnClickListener {
            startActivity(intent2)
        }
        val intent3 = Intent(this, DiagnosisList::class.java)
        binding.cardComContactRes.setOnClickListener {
            startActivity(intent3)
        }
        val intent4 = Intent(this, ChatListActivity::class.java)
        binding.cardComDiagnosis.setOnClickListener {
            startActivity(intent4)
        }

        val intent5 = Intent(this, MyPage::class.java)
        binding.cardComMyPage.setOnClickListener {
            startActivity(intent5)
        }
        val intent6 = Intent(this, Reservation::class.java)
        binding.cardComBackBtn.setOnClickListener {
            startActivity(intent6)
        }
        val intent7 = Intent(this, CardInfo::class.java)
        binding.cardImage.setOnClickListener {
            startActivity(intent7)
        }


    }
}