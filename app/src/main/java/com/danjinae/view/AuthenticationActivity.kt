package com.danjinae.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.danjinae.databinding.ActivityAuthenticationBinding
import kotlin.random.Random

class AuthenticationActivity : AppCompatActivity() {
    val TAG = "본인인증"
    val SMS_SEND_PERMISSION = 1
    lateinit var num: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.d(TAG, "=== sms전송을 위한 퍼미션 확인 ===")

            // For device above MarshMallow
            val permission: Boolean = getWritePermission()
            if (permission) {
                // If permission Already Granted
                // Send You SMS here
                Log.d(TAG, "=== 퍼미션 허용 ===")

                binding.sendSmsButton.setOnClickListener {
                    try {
                        Log.d(TAG, "=== 문자 전송 시작 ===")

                        //전송
                        val smsManager: SmsManager = SmsManager.getDefault()
                        smsManager.sendTextMessage(
                            binding.inputPhoneNum.text.toString(),
                            "단지네",
                            "인증번호는 " + numberGen(1,1) + "입니다.",
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
            // Send Your SMS. You don't need Run time permission
            Log.d(TAG, "=== 퍼미션 필요 없는 버전임 ===")
        }
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
                    // Permission is Granted
                    // Send Your SMS here
                }
            }
        }
    }

    fun numberGen (len: Int, dupCd: Int): String{
        var rand = Random
        var numStr: String = ""

        for(i: Int in 0..len ) run {
            var ran = Integer.toString(rand.nextInt(10))

            if(dupCd == 1){
                numStr += ran
            }else if(dupCd == 2){
                if(!numStr.contains(ran)){
                    numStr += ran
                }else{
                }
            }
        }
        return numStr
    }
}
