package com.danjinae.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.danjinae.databinding.ActivityAptAuthBinding

class AptAuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAptAuthBinding.inflate(layoutInflater)

        binding.textChoiceApt.setOnClickListener{
            val intent = Intent(this, AptSearchActivity::class.java)
            startActivity(intent)
        }

        val aptName = intent.getStringExtra("aptName")
        binding.textChoiceApt.text = aptName

        setContentView(binding.root)
    }
}