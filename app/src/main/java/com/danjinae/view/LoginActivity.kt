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
            login.phone = binding.editId.text.toString()
            login.password = binding.editPassword.text.toString()
            val call: Call<Boolean> = RetrofitClient.networkService.postLogin(login)

            call.enqueue(object : Callback<Boolean> {
                override fun onResponse(
                    call: Call<Boolean>,
                    response: Response<Boolean>
                ){
                    if(response.isSuccessful){
                        val token = response.headers().get("ACCESS_TOKEN")

                        Log.d("로그인",token.toString())
                        CallMain(token)
                    }else{
                        Log.d("로그인", "실패 : ${response.errorBody()?.string()!!}")
                    }
                }
                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    Log.d("연결", "실패 : $t")
                }
            })
        }
    }

    fun CallMain(token: String?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("token",token)
        startActivity(intent)
    }
}