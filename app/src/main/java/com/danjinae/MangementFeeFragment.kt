package com.danjinae

import android.content.Context
import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.google.android.material.snackbar.Snackbar
import com.iamport.sdk.data.sdk.*
import com.iamport.sdk.domain.core.Iamport
import kotlinx.coroutines.GlobalScope
import java.util.*


class MangementFeeFragment : Fragment() {

    private lateinit var btpay: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_management_fee, container, false)

        btpay = view.findViewById(R.id.bt_pay)

        Iamport.init(this)

        return view
    }


    override fun onStart() {
        super.onStart()

        val request = IamPortRequest(
            pg = PG.html5_inicis.makePgRawName(),
            pay_method = PayMethod.card.name,
            name = "아파트 관리비",
            merchant_uid = "sample_aos_${Date().time}",
            amount = "1000",
            buyer_name = "hojin"
        )

        btpay.setOnClickListener { view ->
            Snackbar.make(view, "결제시도", Snackbar.LENGTH_LONG)
                .setAction("결제") {

                    Log.d("", "결제시작")
                    Iamport.payment("imp60685235", iamPortRequest = request, paymentResultCallback = {
                        //Toast.makeText(this, "결제결과 => $it", Toast.LENGTH_LONG).show()
                    })
                }.show()
        }
    }

}
