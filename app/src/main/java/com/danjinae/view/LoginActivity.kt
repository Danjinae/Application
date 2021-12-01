package com.danjinae.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.danjinae.databinding.ActivityLoginBinding
import com.danjinae.network.RetrofitClient
import com.danjinae.vo.LoginUserRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    var login = LoginUserRequest()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textSingUp.setOnClickListener{
            val intent = Intent(this, AuthenticationActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            login.password = binding.editId.text.toString()
            login.phone = binding.editPassword.text.toString()
            val call: Call<Boolean> = RetrofitClient.networkService.postLogin(login)

            call.enqueue(object : Callback<Boolean> {
                override fun onResponse(
                    call: Call<Boolean>,
                    response: Response<Boolean>
                ){
                    if(response.isSuccessful){
                        val headers: okhttp3.Headers = response.headers()
                        // get header value
                        headers
                        val cookie = headers.get("Connection")
                        Log.d("로그인1",headers.toString())
                        Log.d("로그인2",cookie.toString())
                        //CallMain()
                    }else{
                    }
                }
                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    Log.d("연결", "실패 : $t")
                }
            })
        }
    }

    fun CallMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
    }
}