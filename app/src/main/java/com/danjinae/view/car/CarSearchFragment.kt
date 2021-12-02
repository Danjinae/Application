package com.danjinae.view.car

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.danjinae.R
import com.danjinae.databinding.FragmentCarSearchBinding
import com.danjinae.network.RetrofitClient
import com.danjinae.vo.VehicleInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CarSearchFragment : DialogFragment() {
    val TAG = "carSearchtDialog"
    lateinit var carClassfication: TextView
    lateinit var carAdress: TextView
    lateinit var carPhoneNum: TextView
    var carNum: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            carNum = it.getString("carNum")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCarSearchBinding.inflate(inflater, container, false)

        val call: Call<VehicleInfo> = RetrofitClient.networkService.getVehicleInfo(0)
        call.enqueue(object : Callback<VehicleInfo> {
            override fun onResponse(
                call: Call<VehicleInfo>,
                response: Response<VehicleInfo>
            ) {
                if(response.isSuccessful){
                    Log.d(TAG, "성공 : ${response.raw()}")
                } else{
                    Log.d(TAG, "실패 : ${response.errorBody()?.string()!!}")
                }
            }
            override fun onFailure(call: Call<VehicleInfo>, t: Throwable) {
                Log.d(TAG, "서버 연결 실패 : $t")
            }
        })
        return inflater.inflate(R.layout.fragment_car_search, container, false)
    }
}