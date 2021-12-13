package com.danjinae.view.fee

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.danjinae.databinding.FragmentManagementFeeBinding
import com.danjinae.network.RetrofitClient
import com.danjinae.view.LoginActivity
import com.danjinae.vo.MgFee
import com.danjinae.vo.UserMgFeeResponse
import com.google.android.material.snackbar.Snackbar
import com.iamport.sdk.domain.core.Iamport
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getFee(feeData: String){
    var fee: String = feeData
}

class ManagementFeeFragment : Fragment() {
    private lateinit var btpay: Button
    private lateinit var textFee: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentManagementFeeBinding.inflate(inflater, container, false)
        textFee = binding.textFee
        btpay = binding.btPay
        var view = FeeViewModel()
        var fee = Fee()
        var a: String
        Iamport.init(this)

        val call: Call<MgFee> = RetrofitClient.networkService.getMgFee()
        call.enqueue(object : Callback<MgFee> {
            override fun onResponse(
                call: Call<MgFee>,
                response: Response<MgFee>
            ){
                if(response.isSuccessful){
                    Log.d("조회", "성공 : ${response.raw()}")
                    var content: MutableList<UserMgFeeResponse>? = response.body()?.content
                    textFee.text = response.body()?.content?.get(0)?.fee.toString()
                    LoginActivity.prefs.setString("amount",textFee.text.toString())
                }else{

                }
            }
            override fun onFailure(call: Call<MgFee>, t: Throwable) {
                Log.d("연결", "실패 : $t")
            }
        })

        var request = view.createIamPortRequest()

        btpay.setOnClickListener { view ->
            Snackbar.make(view, "결제시도", Snackbar.LENGTH_LONG)
                .setAction("결제") {
                    Log.d("금액",request.amount)
                    Log.d("", "결제시작")
                    Iamport.payment("imp60685235", iamPortRequest = request, paymentResultCallback = {
                        //Toast.makeText(this, "결제결과 => $it", Toast.LENGTH_LONG).show()
                    })
                }.show()
        }
        return binding.root
    }
}



