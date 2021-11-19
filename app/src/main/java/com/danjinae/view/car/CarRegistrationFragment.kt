package com.danjinae.view.car

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.danjinae.databinding.FragmentCarRegistrationBinding
import com.danjinae.network.RetrofitClient
import com.danjinae.vo.GuestVehicleModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.Timestamp

class CarRegistrationFragment : DialogFragment() {
    val TAG = "carDialog"
    lateinit var btnCarInput: Button
    lateinit var btnCarCancel: Button
    lateinit var carNum: EditText
    lateinit var carPhone: EditText
    lateinit var timestamp: Timestamp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCarRegistrationBinding.inflate(inflater, container, false)
        val guestCar = GuestVehicleModel()
        btnCarInput = binding.btnCarInput
        btnCarCancel = binding.btnCarCancel
        carNum = binding.editCarNum
        carPhone = binding.editCarPhone

        btnCarInput.setOnClickListener {
            guestCar.userId = 0
            guestCar.number = carNum.text.toString()
            guestCar.phone = carPhone.text.toString()
            val call: Call<GuestVehicleModel> = RetrofitClient.networkService.postVehicleGuest(guestCar)
            call.enqueue(object : Callback<GuestVehicleModel> {
                override fun onResponse(
                    call: Call<GuestVehicleModel>,
                    response: Response<GuestVehicleModel>
                ) {
                    val data = response.body()
                    Log.d(TAG, "성공 : $data")
                    if(response.isSuccessful){
                    Log.d(TAG, "성공 : ${response.raw()}")
                    } else{
                        Log.d(TAG, "실패 : ${response.errorBody()?.string()!!}")
                    }
                }
                override fun onFailure(call: Call<GuestVehicleModel>, t: Throwable) {
                    Log.d(TAG, "실패1 : $t")
                }
            })
            dismiss()
        }

        btnCarCancel.setOnClickListener{
            dismiss()
        }
        return binding.root
    }
}