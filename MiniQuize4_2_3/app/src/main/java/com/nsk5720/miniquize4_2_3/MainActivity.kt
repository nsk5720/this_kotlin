package com.nsk5720.miniquize4_2_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import com.nsk5720.miniquize4_2_3.databinding.ActivityMainBinding
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    lateinit var textview_msg : TextView
    lateinit var pB : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        textview_msg =findViewById(R.id.numText)
        var second = 0
        timer(period = 1000){
            second++
            runOnUiThread{
                textview_msg.text = second.toString()
            }
        }

    }
}