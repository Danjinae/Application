package com.danjinae.view.car

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.danjinae.adapter.CarChoiceAdapter
import com.danjinae.databinding.FragmentCarBinding
import com.danjinae.network.RetrofitClient
import com.danjinae.vo.VehicleList
import com.danjinae.vo.VehicleResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CarFragment : Fragment() {
    lateinit var btnCarSearch: Button
    lateinit var btnGuestCar: Button
    lateinit var carNum: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCarBinding.inflate(inflater, container, false)
        var bundle = Bundle()
        btnCarSearch = binding.btnCarSerch
        btnGuestCar = binding.btGuestCar
        carNum = binding.edtCar

        val layoutManager = LinearLayoutManager(context)
        val carChoiceAdapter = CarChoiceAdapter()
        binding.carList.layoutManager = layoutManager
        binding.carList.adapter = carChoiceAdapter

        btnCarSearch.setOnClickListener {
//            bundle.putString("carNum",carNum.text.toString())
//            var dialogCarSearch = CarSearchFragment()
//            dialogCarSearch.arguments = bundle
//            dialogCarSearch.show(childFragmentManager, "carSearchDialog")
            carChoiceAdapter.carData.clear()
            val call: Call<VehicleList> = RetrofitClient.networkService.getVehicleSelectList(carNum.text.toString())
            call.enqueue(object : Callback<VehicleList> {
                override fun onResponse(
                    call: Call<VehicleList>,
                    response: Response<VehicleList>
                ){
                    if(response.isSuccessful){
                        Log.d("조회", "성공 : ${response.body()?.content}")
                        var carList: MutableList<VehicleResponse>? = response.body()?.content
                        if (carList != null && carList.size > 0) {
                            for(i in 0 until carList.size) {
                                carChoiceAdapter.carData.add(carList[i])
                                carChoiceAdapter.notifyDataSetChanged()
                            }
                        }
                    }else{
                    }
                }
                override fun onFailure(call: Call<VehicleList>, t: Throwable) {
                    Log.d("연결", "실패 : $t")
                }
            })

        }

        btnGuestCar.setOnClickListener {
            var dialogCarRegistration = CarRegistrationFragment()
            dialogCarRegistration.show(childFragmentManager, "carRegistrationDialog")
        }
        return binding.root
    }
}