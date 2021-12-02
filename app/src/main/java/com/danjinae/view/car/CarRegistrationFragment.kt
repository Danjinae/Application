package com.danjinae.view.car

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.danjinae.databinding.FragmentCarRegistrationBinding
import com.danjinae.network.RetrofitClient
import com.danjinae.vo.VehicleRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class CarRegistrationFragment : DialogFragment() {
    val TAG = "carRegisrationtDialog"
    val guestCar = VehicleRequest()
    lateinit var datePicker: DatePickerHelper
    lateinit var btnCarInput: Button
    lateinit var btnCarCancel: Button
    lateinit var carNum: EditText
    lateinit var carPhone: EditText
    lateinit var carDate: TextView
    val a: Activity? = activity

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
        btnCarInput = binding.btnCarInput
        btnCarCancel = binding.btnCarCancel
        carNum = binding.editCarNum
        carPhone = binding.editCarPhone
        carDate = binding.textCarDate
        val timeNow = System.currentTimeMillis()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val dateNow = dateFormat.format(timeNow)
        //val dateEnd = dateFormat.format(carDate.text)
        datePicker = DatePickerHelper(requireContext(),true)
        carDate.setOnClickListener {
            showDatePickerDialog()
        }

        btnCarInput.setOnClickListener {
            guestCar.userId = 6
            guestCar.startDate = dateNow
            guestCar.endDate = carDate.text.toString()
            guestCar.number = carNum.text.toString()
            guestCar.phone = carPhone.text.toString()

            val call: Call<List<VehicleRequest>> =
                RetrofitClient.networkService.postVehicleGuest(guestCar)
            call.enqueue(object : Callback<List<VehicleRequest>> {
                override fun onResponse(
                    call: Call<List<VehicleRequest>>,
                    response: Response<List<VehicleRequest>>
                ) {
                    if (response.isSuccessful) {
                        Log.d(TAG, "성공 : ${response.body()}")
                    } else {
                        Log.d(TAG, "실패 : ${response.errorBody()?.string()!!}")
                    }
                }
                override fun onFailure(call: Call<List<VehicleRequest>>, t: Throwable) {
                    Log.d(TAG, "실패1 : $t")
                }
            })
            dismiss()
        }

        btnCarCancel.setOnClickListener {
            dismiss()
        }
        return binding.root
    }

    private fun showDatePickerDialog() {
        val cal = Calendar.getInstance()
        val d = cal.get(Calendar.DAY_OF_MONTH)
        val m = cal.get(Calendar.MONTH)
        val y = cal.get(Calendar.YEAR)
        datePicker.showDialog(d, m, y, object : DatePickerHelper.Callback {
            override fun onDateSelected(dayofMonth: Int, month: Int, year: Int) {
                val dayStr = if (dayofMonth < 10) "0${dayofMonth}" else "${dayofMonth}"
                val mon = month + 1
                val monthStr = if (mon < 10) "0${mon}" else "${mon}"
                carDate.text = "${year}-${monthStr}-${dayStr}"
            }
        })
    }
}
