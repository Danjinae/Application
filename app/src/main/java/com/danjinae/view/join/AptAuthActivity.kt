package com.danjinae.view.join

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.danjinae.databinding.ActivityAptAuthBinding
import com.danjinae.view.LoginActivity

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

        binding.aptConfirm.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            Toast.makeText(this,"입주민 인증이 되었습니다.", Toast.LENGTH_SHORT).show()
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
    }
}