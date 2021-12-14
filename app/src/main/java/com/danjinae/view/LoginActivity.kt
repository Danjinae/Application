package com.danjinae.view

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.danjinae.R
import com.danjinae.databinding.ActivityLoginBinding
import com.danjinae.network.RetrofitClient
import com.danjinae.view.join.WebViewAuthActivity
import com.danjinae.vo.FcmToken
import com.danjinae.vo.LoginUserRequest
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.dialog_login.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    var login = LoginUserRequest()
    val TAG = "로그인"
    var backKeypressedTime: Long = 0
    var fcmToken = FcmToken()
    companion object {
        lateinit var prefs: PrefsManager
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = PrefsManager(this)

        binding.textSingUp.setOnClickListener{
            val intent = Intent(this, WebViewAuthActivity::class.java)
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
                        val accessToken = response.headers().get("ACCESS_TOKEN")
                        val refreshToken = response.headers().get("REFRESH_TOKEN")

                        if(response.body()!!){
                            if (accessToken != null) {
                                prefs.setString("ACCESS_TOKEN",accessToken)
                            }
                            if (refreshToken != null) {
                                prefs.setString("REFRESH_TOKEN",refreshToken)
                            }
                            DeviceToken()
                            CallMain()
                        }else{
                            val loginDialogView = LayoutInflater.from(this@LoginActivity).inflate(R.layout.dialog_login, null)
                            val loginBuilder = AlertDialog.Builder(this@LoginActivity)
                                .setView(loginDialogView)
                            val dialog = loginBuilder.create()
                            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.show()

                            val button = loginDialogView.btnConfirm
                            button.setOnClickListener{
                                dialog.dismiss()
                            }
                        }
                    }else{
                        Log.d(TAG, "실패 : ${response.errorBody()?.string()!!}")
                    }
                }
                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    Log.d(TAG, "실패 : $t")
                }
            })
        }

    }

    private fun DeviceToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("토큰", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            fcmToken.token = token
            // Log and toast
            //val msg = getString(R.string.msg_token_fmt, token)
            if (token != null) {
                Log.d("토큰", token)
                val call: Call<Boolean> = RetrofitClient.networkService.newToken(fcmToken)
                call.enqueue(object : Callback<Boolean> {
                    override fun onResponse(
                        call: Call<Boolean>,
                        response: Response<Boolean>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("fcm토큰","저장성공")
                        } else {
                            Log.d(TAG, "실패 : ${response.errorBody()?.string()!!}")
                        }
                    }

                    override fun onFailure(call: Call<Boolean>, t: Throwable) {
                        Log.d("연결", "실패 : $t")
                    }
                })
            }
            //Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })
    }


    fun CallMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    class PrefsManager(context: Context) {
        private val prefs = context.getSharedPreferences("pref_name", Context.MODE_PRIVATE)

        fun getString(key: String, defValue: String) : String {
            return prefs.getString(key, defValue).toString()
        }

        fun setString(key: String, value: String) {
            prefs.edit().putString(key, value).apply()
        }
    }

    override fun onBackPressed() {
        if(System.currentTimeMillis() > backKeypressedTime + 2500) {
            backKeypressedTime = System.currentTimeMillis()
            Toast.makeText(this,"종료를 원하시면 뒤로가기를 한번 더 눌러주세요.",Toast.LENGTH_SHORT).show()
            return
        }

        if(System.currentTimeMillis() <= backKeypressedTime + 2500) {
            finishAffinity()
        }
    }
}