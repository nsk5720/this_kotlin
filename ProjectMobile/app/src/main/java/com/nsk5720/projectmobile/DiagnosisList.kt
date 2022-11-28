package com.nsk5720.projectmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nsk5720.projectmobile.databinding.ActivityDiagnosisBinding
import com.nsk5720.projectmobile.databinding.ActivityDiagnosisListBinding

class DiagnosisList : AppCompatActivity() {
    val binding by lazy{ ActivityDiagnosisListBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val intent = Intent(this, ChatListActivity::class.java)
        binding.diagnosisResult1.setOnClickListener {
            startActivity(intent)
        }

        val intent2 = Intent(this, ChatListActivity::class.java)
        binding.diagnosisResult2.setOnClickListener {
            startActivity(intent2)
        }

        val intent3 = Intent(this, ChatListActivity::class.java)
        binding.diagnosisResult3.setOnClickListener {
            startActivity(intent3)
        }

        val intent4 = Intent(this, ChatListActivity::class.java)
        binding.diagnosisResult4.setOnClickListener {
            startActivity(intent4)
        }

        val intent5 = Intent(this, ChatListActivity::class.java)
        binding.diagnosisResult5.setOnClickListener {
            startActivity(intent5)
        }

        val intent6 = Intent(this, ChatListActivity::class.java)
        binding.diagnosisResult6.setOnClickListener {
            startActivity(intent6)
        }
        val intent7 = Intent(this, Map::class.java)
        binding.diagListUntactRes.setOnClickListener {
            startActivity(intent7)
        }
        val intent8 = Intent(this, ChatListActivity::class.java)
        binding.diagListDiagnosis.setOnClickListener {
            startActivity(intent8)
        }

        val intent9 = Intent(this, MyPage::class.java)
        binding.diagListMyPage.setOnClickListener {
            startActivity(intent9)
        }
        val intent10 = Intent(this, Map::class.java)
        binding.diagListBackBtn.setOnClickListener {
            startActivity(intent10)
        }
        val intent11 = Intent(this, MusicPlay::class.java)
        binding.musicBtn.setOnClickListener {
            startActivity(intent11)
        }


    }
}