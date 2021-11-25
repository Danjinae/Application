package com.danjinae.view.community

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.danjinae.adapter.NoticeAdapter
import com.danjinae.databinding.FragmentNoticeBinding
import com.danjinae.network.RetrofitClient
import com.danjinae.vo.Notice
import com.danjinae.vo.NoticeListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoticeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNoticeBinding.inflate(inflater, container, false)
        val layoutManager = LinearLayoutManager(context)
        val noticeAdapter = NoticeAdapter()

        binding.recycleNotice.layoutManager = layoutManager
        binding.recycleNotice.adapter = noticeAdapter
        noticeAdapter.notifyDataSetChanged()

        Log.d("공지","진입")

            val call: Call<Notice> = RetrofitClient.networkService.getNoticeList(10)
            call.enqueue(object : Callback<Notice> {
                override fun onResponse(
                    call: Call<Notice>,
                    response: Response<Notice>
                ) {
                    if (response.isSuccessful) {
                        var dataList: MutableList<NoticeListResponse>? = response.body()?.content
                        var content = dataList?.get(0)?.content
                        Log.d("데이터1",content.toString())
                        if (dataList != null) {
                            noticeAdapter.noticeData = dataList
                            noticeAdapter.notifyDataSetChanged()
                        }

                    }else Log.d("조회", "실패 : ${response.errorBody()}")
                }override fun onFailure(call: Call<Notice>, t: Throwable) {
                    Log.d("조회", "실패 : $t")
                }
            })

        return binding.root
    }
}