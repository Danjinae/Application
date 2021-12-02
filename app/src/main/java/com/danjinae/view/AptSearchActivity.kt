package com.danjinae.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.danjinae.adapter.AptChoiceAdapter
import com.danjinae.databinding.ActivityAptSearchBinding
import com.danjinae.network.RetrofitClient
import com.danjinae.vo.AptList
import com.danjinae.vo.AptListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AptSearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAptSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        val aptChoiceAdapter = AptChoiceAdapter()
        binding.recycleApt.layoutManager = layoutManager
        binding.recycleApt.adapter = aptChoiceAdapter

        binding.aptSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(count > 1){
                    Log.d("아파트선택","진입")
                    val call: Call<AptList> = RetrofitClient.networkService.getapt(binding.aptSearch.text.toString())
                    call.enqueue(object : Callback<AptList> {
                        override fun onResponse(
                            call: Call<AptList>,
                            response: Response<AptList>
                        ){
                            if(response.isSuccessful){
                                Log.d("조회", "성공 : ${response.raw()}")
                                var aptList: MutableList<AptListResponse>? = response.body()?.content
                                if (aptList != null && aptList.size > 0) {
                                    for(i in 0 until aptList.size){
                                        aptChoiceAdapter.aptData.clear()
                                        aptChoiceAdapter.aptData.add(aptList[i])
                                        aptChoiceAdapter.notifyDataSetChanged()
                                    }
                                }
                            }else{
                            }
                        }
                        override fun onFailure(call: Call<AptList>, t: Throwable) {
                            Log.d("연결", "실패 : $t")
                        }
                    })
                }else{
                    aptChoiceAdapter.aptData.clear()
                    aptChoiceAdapter.notifyDataSetChanged()
                }
            }
        })
    }
}