package com.nsk5720.projectmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nsk5720.projectmobile.databinding.ActivityHospitalListBinding

class HospitalList : AppCompatActivity() {
    val binding by lazy { ActivityHospitalListBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val intent = Intent(this, Reservation::class.java)
        binding.reservationBtn1.setOnClickListener {
            startActivity(intent)
        }
        val intent2 = Intent(this, Reservation::class.java)
        binding.reservationBtn2.setOnClickListener {
            startActivity(intent2)
        }
        val intent3 = Intent(this, Reservation::class.java)
        binding.reservationBtn3.setOnClickListener {
            startActivity(intent3)
        }

        val intent4 = Intent(this, Map::class.java)
        binding.backBtn.setOnClickListener {
            startActivity(intent4)
        }

        val intent5 = Intent(this, HospitalInfo::class.java)
        binding.hospital1.setOnClickListener {
            startActivity(intent5)
        }
        val intent6 = Intent(this, HospitalInfo::class.java)
        binding.hospital2.setOnClickListener {
            startActivity(intent6)
        }
        val intent7 = Intent(this, HospitalInfo::class.java)
        binding.hospital3.setOnClickListener {
            startActivity(intent7)
        }

        val intent8 = Intent(this, Map::class.java)
        binding.hlUntactRes.setOnClickListener {
            startActivity(intent8)
        }
        val intent9 = Intent(this, DiagnosisList::class.java)
        binding.hlContactRes.setOnClickListener {
            startActivity(intent9)

        }
        val intent10 = Intent(this, ChatListActivity::class.java)
        binding.hlDiagnosis.setOnClickListener {
            startActivity(intent10)
        }

        val intent11 = Intent(this, MyPage::class.java)
        binding.hlMyPage.setOnClickListener {
            startActivity(intent11)
        }


        val intent12 = Intent(this, Map::class.java)
        binding.hlBackBtn.setOnClickListener {
            startActivity(intent12)
        }


    }

}