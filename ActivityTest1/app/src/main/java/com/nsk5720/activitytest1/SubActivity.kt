package com.nsk5720.activitytest1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nsk5720.activitytest1.databinding.ActivityMainBinding
import com.nsk5720.activitytest1.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {
    val binding by lazy { ActivitySubBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.to1.text = intent.getStringExtra("from1")
        binding.to2.text = "${intent.getIntExtra("from2",0)}"

        binding.btnClose.setOnClickListener{
            val returnIntent = Intent()
            returnIntent.putExtra("returnValue", binding.editMessage.text.toString())
            setResult(RESULT_OK, returnIntent)
            finish()
        }
    }
}