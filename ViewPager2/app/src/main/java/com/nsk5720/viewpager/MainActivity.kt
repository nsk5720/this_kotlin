package com.nsk5720.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nsk5720.viewpager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy{ ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}