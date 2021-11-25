package com.danjinae.view.complain

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.danjinae.databinding.FragmentComplaintAddBinding
import com.danjinae.network.RetrofitClient
import com.danjinae.vo.Complaint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComplaintAddFragment : DialogFragment() {
    lateinit var complaintAdd: Button
    lateinit var complaintEit: EditText
    var complaint = Complaint()
    val TAG ="complaintAddFragment"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentComplaintAddBinding.inflate(inflater, container, false)
        complaintAdd = binding.complaintAdd
        complaintEit = binding.editComplaint

        complaintAdd.setOnClickListener {
            Log.d("등록","진입")
            complaint.aptId = 1
            complaint.content = complaintEit.text.toString()
            complaint.userId = 1
            val call: Call<Boolean> = RetrofitClient.networkService.addComplaint(complaint)
            call.enqueue(object : Callback<Boolean> {
                override fun onResponse(
                    call: Call<Boolean>,
                    response: Response<Boolean>
                ) {
                    if (response.isSuccessful) {
                        Log.d("조회", "성공 : ${response.raw()}")
                    }else Log.d("조회", "실패 : ${response.errorBody()}")
                }
                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    Log.d("조회", "실패 : $t")
                }
            })
        }
        return binding.root
    }

}