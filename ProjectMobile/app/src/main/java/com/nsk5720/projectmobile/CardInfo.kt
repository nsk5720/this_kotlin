package com.nsk5720.projectmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nsk5720.projectmobile.databinding.ActivityCardInfoBinding

class CardInfo : AppCompatActivity() {
    val binding by lazy { ActivityCardInfoBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val intent = Intent(this, CardComplete::class.java)
        binding.cardInfoBtn.setOnClickListener {
            startActivity(intent)
        }

        val intent2 = Intent(this, Map::class.java)
        binding.cardInfoUntactRes.setOnClickListener {
            startActivity(intent2)
        }
        val intent3 = Intent(this, DiagnosisList::class.java)
        binding.cardInfoContactRes.setOnClickListener {
            startActivity(intent3)
        }
        val intent4 = Intent(this, ChatListActivity::class.java)
        binding.cardInfoDiagnosis.setOnClickListener {
            startActivity(intent4)
        }

        val intent5 = Intent(this, MyPage::class.java)
        binding.cardInfoMyPage.setOnClickListener {
            startActivity(intent5)
        }
        val intent6 = Intent(this, Reservation::class.java)
        binding.cardInfoBackBtn.setOnClickListener {
            startActivity(intent6)
        }




    }
}