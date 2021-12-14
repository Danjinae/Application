package com.danjinae.view.join

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.danjinae.databinding.ActivityAptAuthBinding
import com.danjinae.network.RetrofitClient
import com.danjinae.view.LoginActivity
import com.danjinae.vo.Auth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AptAuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAptAuthBinding.inflate(layoutInflater)
        var phone = binding.inputPhoneNum
        val toolbar = binding.toolbar
        var authUser = Auth()
        setContentView(binding.root)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.textChoiceApt.setOnClickListener{
            val intent = Intent(this, AptSearchActivity::class.java)
            startActivity(intent)
        }

        val aptName = intent.getStringExtra("aptName")
        val aptId = intent.getIntExtra("aptId",-1)
        binding.textChoiceApt.text = aptName

        binding.auth.setOnClickListener {
            authUser.aptId = aptId
            authUser.impId = LoginActivity.prefs.getString("impId","")
            authUser.phone = phone.text.toString()
            val call: Call<Boolean> = RetrofitClient.networkService.auth(authUser)
            call.enqueue(object : Callback<Boolean> {
                override fun onResponse(
                    call: Call<Boolean>,
                    response: Response<Boolean>
                ){
                    if(response.isSuccessful){
                        Log.d("조회", "성공 : ${response.raw()}")
                        binding.aptConfirm.isEnabled = true
                    }else{
                        Log.d("조회", "실패 : ${response.errorBody()}")
                    }
                }
                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    Log.d("연결", "실패 : $t")
                }
            })
            Toast.makeText(this,"입주민 인증이 되었습니다.", Toast.LENGTH_SHORT).show()
        }

        binding.aptConfirm.setOnClickListener {
            val intent = Intent(this, SingUpActivity::class.java)
            intent.apply {
                this.putExtra("phoneNum",phone.text.toString())
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
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