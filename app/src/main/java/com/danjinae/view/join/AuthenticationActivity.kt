package com.danjinae.view.join

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.danjinae.databinding.ActivityAuthenticationBinding
import kotlin.random.Random

class AuthenticationActivity : AppCompatActivity() {
    val TAG = "본인인증"
    val SMS_SEND_PERMISSION = 1
    var num: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.d(TAG, "=== sms전송을 위한 퍼미션 확인 ===")

            val permission: Boolean = getWritePermission()
            if (permission) {
                Log.d(TAG, "=== 퍼미션 허용 ===")

                binding.sendSmsButton.setOnClickListener {
                    try {
                        Log.d(TAG, "=== 문자 전송 시작 ===")
                        val smsManager: SmsManager = SmsManager.getDefault()
                        num = numberGen(5, 1)
                        smsManager.sendTextMessage(
                            binding.inputPhoneNum.text.toString(),
                            "단지네",
                            "인증번호는 " + num + "입니다.",
                            null,
                            null
                        )

                        Log.d(TAG, "=== 문자 전송 완료 ===")

                    } catch (e: Exception) {
                        Log.d(TAG, "=== 문자 전송 실패 === 에러코드 e : $e")
                        e.printStackTrace()
                        var sendCan = false
                        Log.d(TAG, "=== sendCan === :$sendCan")
                    }
                }

            }
        } else {
            Log.d(TAG, "=== 퍼미션 필요 없는 버전임 ===")
        }

        binding.checkButton.setOnClickListener {
            if(binding.inputCheckNum.text.toString() == num){
                Log.d(TAG,"인증 완료")
                val intent = Intent(this, SingUpActivity::class.java)
                intent.apply {
                    this.putExtra("phoneNum",binding.inputPhoneNum.text.toString())
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
            }else{
                Log.d(TAG,"인증 실패")
            }
        }

        binding.inputCheckNum.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.inputCheckNum.length() > 0) {
                    binding.checkButton.isEnabled = true
                }
            }
        })

    }

    fun getWritePermission(): Boolean {
        val hasPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.SEND_SMS
        ) == PackageManager.PERMISSION_GRANTED
        if (!hasPermission) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), 10)
        }
        return hasPermission
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            10 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                }
            }
        }
    }

    fun numberGen(len: Int, dupCd: Int): String {
        var rand = Random
        var numStr: String = ""

        for (i: Int in 0..len) run {
            var ran = Integer.toString(rand.nextInt(10))

            if (dupCd == 1) {
                numStr += ran
            } else if (dupCd == 2) {
                if (!numStr.contains(ran)) {
                    numStr += ran
                } else {
                }
            }
        }
        return numStr
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
