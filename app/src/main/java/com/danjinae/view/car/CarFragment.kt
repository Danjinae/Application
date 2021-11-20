package com.danjinae.view.car

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.danjinae.databinding.FragmentCarBinding


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

        bundle.putString("carNum",carNum.toString())

        btnCarSearch.setOnClickListener {
            var dialogCarSearch = CarSearchFragment()
            dialogCarSearch.arguments = bundle
            dialogCarSearch.show(childFragmentManager, "carSearchDialog")
        }

        btnGuestCar.setOnClickListener {
            var dialogCarRegistration = CarRegistrationFragment()
            dialogCarRegistration.show(childFragmentManager, "carRegistrationDialog")
        }
        return binding.root
    }
}



//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val binding = FragmentCarBinding.bind(view)
//        btnCarSearch = binding.btnCarSerch
//        btnGuestCar = binding.btGuestCar
//        carNum = binding.edtCar
//
//        btnCarSearch.setOnClickListener{
//            val call: Call<PostModel> = RetrofitClient.networkService.addPost(PostModel())
//            call.enqueue(object : Callback<PostModel> {
//                override fun onResponse(
//                    call: Call<PostModel>,
//                    response: Response<PostModel>
//                ) {
//                    if(response.isSuccessful){
//                        Log.d("조회", "성공 : ${response.raw()}")
//                    }
//                    Log.d("조회2", "실패 : ${response.errorBody()?.string()!!}")
//                }
//                override fun onFailure(call: Call<PostModel>, t: Throwable) {
//                    Log.d("조회3", "실패 : $t")
//                }
//            })
//        }
//
//        btnGuestCar.setOnClickListener{
//            var dialog = CarRegistrationFragment()
//            dialog.show(childFragmentManager,"carDialog")
//        }
//    }
//
//}