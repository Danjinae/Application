package com.danjinae.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danjinae.databinding.NoticeListBinding
import com.danjinae.vo.NoticeListResponse

class NoticeViewHolder(val binding: NoticeListBinding): RecyclerView.ViewHolder(binding.root){
}
class NoticeAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var noticeData = mutableListOf<NoticeListResponse>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return NoticeViewHolder(NoticeListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as NoticeViewHolder).binding
        binding.noticeItem.text = noticeData[position].content
    }
    override fun getItemCount(): Int{
        return noticeData?.size ?: 0
    }
}