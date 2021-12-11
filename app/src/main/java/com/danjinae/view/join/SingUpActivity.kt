package com.danjinae.view.join

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.danjinae.databinding.ActivitySingUpBinding
import com.danjinae.network.RetrofitClient
import com.danjinae.vo.LoginUserRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class SingUpActivity : AppCompatActivity() {
    val TAG = "비밀번호 설정"
    var singUp = LoginUserRequest()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySingUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val phoneNum = intent.getStringExtra("phoneNum")
        binding.textId.text = phoneNum


        binding.btnConfirm.setOnClickListener {
            val intent = Intent(this, AptAuthActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        binding.btnJoin.setOnClickListener {
            singUp.password = binding.editRePass.text.toString()
            singUp.phone = phoneNum
            val call: Call<Boolean> = RetrofitClient.networkService.putSingUp(singUp)
            call.enqueue(object : Callback<Boolean> {
                override fun onResponse(
                    call: Call<Boolean>,
                    response: Response<Boolean>
                ){
                    if(response.isSuccessful){
                        Log.d("조회", "성공 : ${response.raw()}")
                        binding.btnConfirm.isEnabled = true
                    }else{

                    }
                }
                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    Log.d("연결", "실패 : $t")
                }
            })
            Toast.makeText(this,"회원가입이 되었습니다.", Toast.LENGTH_SHORT).show()
        }

        binding.editPass.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                binding.password.isVisible =
                    !Pattern.matches("^(?=.*[A-Za-z])(?=.*[0-9]).{8,16}$",binding.editPass.text.toString())

                if (binding.editPass.text.toString()==binding.editRePass.text.toString()) {
                    binding.btnJoin.isEnabled = true
                    binding.passSame.isVisible = false
                }else{
                    binding.passSame.isVisible = true
                    binding.btnJoin.isEnabled = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.password.isVisible =
                    !Pattern.matches("^(?=.*[A-Za-z])(?=.*[0-9]).{8,16}\$",binding.editPass.text.toString())

                if (binding.editPass.text.toString().equals(binding.editRePass.text.toString())) {
                    binding.btnJoin.isEnabled = true
                    binding.passSame.isVisible = false
                }else{
                    binding.passSame.isVisible = true
                    binding.btnJoin.isEnabled = false
                }
            }
        })

        binding.editRePass.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (binding.editPass.text.toString()==binding.editRePass.text.toString()) {
                    binding.btnJoin.isEnabled = true
                    binding.passSame.isVisible = false
                }else{
                    binding.passSame.isVisible = true
                    binding.btnJoin.isEnabled = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.editPass.text.toString().equals(binding.editRePass.text.toString())) {
                    binding.btnJoin.isEnabled = true
                    binding.passSame.isVisible = false
                }else{
                    binding.passSame.isVisible = true
                    binding.btnJoin.isEnabled = false
                }
            }
        })
    }
}