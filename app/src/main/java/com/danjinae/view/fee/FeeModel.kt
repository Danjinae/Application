package com.danjinae.view.fee

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danjinae.view.LoginActivity
import com.iamport.sdk.data.sdk.IamPortRequest
import com.iamport.sdk.data.sdk.IamPortResponse
import com.iamport.sdk.data.sdk.PG
import com.iamport.sdk.data.sdk.PayMethod
import com.iamport.sdk.domain.core.Iamport
import com.iamport.sdk.domain.utils.Event
import java.util.*

class FeeViewModel : ViewModel() {

    lateinit var pg: PG
    lateinit var payMethod: PayMethod
    var userCode: String = ""
    var paymentName: String = ""
    var merchantUid: String = ""
    var amount: String = ""

    val resultCallback = MutableLiveData<Event<IamPortResponse>>()
    override fun onCleared() {
        Iamport.close()
        super.onCleared()
    }

    /**
     * SDK 에 결제 요청할 데이터 구성
     */
    fun createIamPortRequest(): IamPortRequest {
        return IamPortRequest(
            pg = PG.html5_inicis.makePgRawName(),
            pay_method = PayMethod.card.name,
            name = "아파트 관리비",
            merchant_uid = "sample_aos_${Date().time}",
            amount = LoginActivity.prefs.getString("amount",""),
            buyer_name = "유호진"
        )
    }

    private fun getRandomCustomerUid(): String {
        return "mcuid_aos_${Date().time}"
    }

    @JvmName("setAmount1")
    fun setAmount(am: String){
        amount = am
    }

    @JvmName("getAmount1")
    fun getAmount(): String{
        return amount
    }
}