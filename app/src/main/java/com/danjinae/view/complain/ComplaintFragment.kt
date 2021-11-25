package com.danjinae.view.complain

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.danjinae.adapter.ComplaintAdapter
import com.danjinae.databinding.FragmentComplaintBinding
import com.danjinae.network.RetrofitClient
import com.danjinae.vo.Complaint
import com.danjinae.vo.ComplaintList
import com.danjinae.vo.ComplaintListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ComplaintFragment : Fragment() {
    var datas = mutableListOf<ComplaintListResponse>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentComplaintBinding.inflate(inflater, container, false)
        var complaint = Complaint()
        complaint.aptId = 1
        complaint.userId = 10

        val layoutManager = LinearLayoutManager(context)
        val complaintAdapter = ComplaintAdapter()
        binding.recycleComplaint.layoutManager = layoutManager
        binding.recycleComplaint.adapter = complaintAdapter

        complaintAdapter.notifyDataSetChanged()

        val call: Call<ComplaintList> = RetrofitClient.networkService.getManagerComplaintList(1)
        call.enqueue(object : Callback<ComplaintList> {
            override fun onResponse(
                call: Call<ComplaintList>,
                response: Response<ComplaintList>
            ) {
                if (response.isSuccessful) {
                    var dataList: MutableList<ComplaintListResponse>? = response.body()?.content
                    var content = dataList?.get(0)?.content
                    if (dataList != null) {
                        complaintAdapter.complaintData = dataList
                        complaintAdapter.notifyDataSetChanged()
                    }
                    Log.d("내용!!",content.toString())
                    if (dataList != null && dataList.size > 0) {
                        for (i in 0 until dataList.size) {
                            Log.d("내용",dataList[i].toString())
                        }
                        Log.d("데이터", datas.toString())
                        Log.d("조회", "성공 : ${response.raw()}")
                    } else Log.d("조회", "실패 : ${response.errorBody()}")
                }
            }override fun onFailure(call: Call<ComplaintList>, t: Throwable) {
                Log.d("조회", "실패 : $t")
            }
        })

//        val call: Call<ComplaintProcess> = RetrofitClient.networkService.getManagerComplaint(1)
//        call.enqueue(object : Callback<ComplaintProcess> {
//            override fun onResponse(
//                call: Call<ComplaintProcess>,
//                response: Response<ComplaintProcess>
//            ) {
//                if (response.isSuccessful) {
//                    var dataList: MutableList<Processes>? = response.body()?.processes
//                    var processdata = dataList?.get(0)?.content
//                    var content = dataList?.get(0)?.content
//                    if (processdata != null) {
//                        complaintAdapter.processData = processdata
//                        complaintAdapter.notifyDataSetChanged()
//                    }
//                    Log.d("진행",content.toString())
//                    if (processdata != null && processdata.size > 0) {
//                        for (i in 0 until processdata.size) {
//                            Log.d("진행",processdata[i].toString())
//                        }
//                        Log.d("진행", datas.toString())
//                        Log.d("진행", "성공 : ${response.raw()}")
//                    } else Log.d("진행", "실패 : ${response.errorBody()}")
//                }
//            }override fun onFailure(call: Call<ComplaintProcess>, t: Throwable) {
//                Log.d("진행", "실패 : $t")
//            }
//        })

        binding.fabComplaint.setOnClickListener {
            var dialogComplaintAdd = ComplaintAddFragment()
            dialogComplaintAdd.show(childFragmentManager, "complaintAddFragment")
        }
        return binding.root
    }

}