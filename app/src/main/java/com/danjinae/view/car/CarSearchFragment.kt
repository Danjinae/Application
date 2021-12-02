package com.danjinae.view.car

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.danjinae.databinding.FragmentCarSearchBinding
import com.danjinae.network.RetrofitClient
import com.danjinae.vo.VehicleList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CarSearchFragment : DialogFragment() {
    val TAG = "carSearchtDialog"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCarSearchBinding.inflate(inflater, container, false)
        val args = arguments
        val carNum: String? = args?.getString("carNum")
        if (carNum != null) {
            Log.d("차량 조회", carNum)
        }

        var carNumber = binding.carNum
        var carAddress = binding.carAddress
        var carPhone = binding.carPhone
        var carClass = binding.carClass

        val call: Call<VehicleList> = RetrofitClient.networkService.getVehicleSelectList(carNum.toString())
        call.enqueue(object : Callback<VehicleList> {
            override fun onResponse(
                call: Call<VehicleList>,
                response: Response<VehicleList>
            ) {
                if(response.isSuccessful){
                    carNumber.text = response.body()?.content?.get(0)?.number.toString()
                    //carAddress.text = response.body()?.content?.get(0)?.
                    carPhone.text = response.body()?.content?.get(0)?.phone.toString()
                    Log.d(TAG,carNumber.text.toString())
                    if(response.body()?.content?.get(0)?.guest == true){
                        carClass.text = "방문 차량입니다!"
                    }else{
                        carClass.text = "입주민 차량입니다!"
                    }
                } else{
                    Log.d(TAG, "실패 : ${response.errorBody()?.string()!!}")
                }
            }
            override fun onFailure(call: Call<VehicleList>, t: Throwable) {
                Log.d(TAG, "서버 연결 실패 : $t")
            }
        })
        return binding.root
    }
}