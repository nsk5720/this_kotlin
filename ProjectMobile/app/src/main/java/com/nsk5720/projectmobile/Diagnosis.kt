/*
package com.nsk5720.projectmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.nsk5720.projectmobile.databinding.ActivityDiagnosisBinding
import kotlin.concurrent.thread

class Diagnosis : AppCompatActivity() {
    val binding by lazy{ActivityDiagnosisBinding.inflate(layoutInflater)}

    // 타이머
    var total = 0
    var started = false

    val handler = object: Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            val minute = String.format("%02d", total / 60)
            val second = String.format("%02d", total % 60)
            binding.textTimer.text = "$minute:$second"
        }
    }
    // 타이머



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 링크
        val intent = Intent(this, Map::class.java)
        binding.diagUntactRes.setOnClickListener {
            startActivity(intent)
        }

        val intent2 = Intent(this, DiagnosisList::class.java)
        binding.diagContactRes.setOnClickListener {
            startActivity(intent2)
        }
        val intent3 = Intent(this, MyPage::class.java)
        binding.diagMyPage.setOnClickListener {
            startActivity(intent3)
        }
        val intent4 = Intent(this, DiagnosisList::class.java)
        binding.diagBackBtn.setOnClickListener {
            startActivity(intent4)
        }
        // 링크

        // 별점
        binding.ratingBar.setOnRatingBarChangeListener {ratingBar, rating, fromUser ->
            binding.ratingResult.text = "$rating"
        }

        //타이머
        binding.buttonStart.setOnClickListener {
            started= true
            if(total == 0) {
                thread(start = true) {
                    while (started) {
                        Thread.sleep(1000)
                        if (started) {
                            total = total + 1
                            handler?.sendEmptyMessage(0)
                        }
                    }
                }
            }
        }
        binding.buttonStop.setOnClickListener{
            if(started){
                started = false
                total = 0
                binding.textTimer.text = "00:00"
            }
        }
        //타이머



    }
}*/
