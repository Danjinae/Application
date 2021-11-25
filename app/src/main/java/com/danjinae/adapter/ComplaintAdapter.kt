package com.danjinae.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danjinae.databinding.ComplaintListBinding
import com.danjinae.vo.ComplaintListResponse

class ComplaintViewHolder(val binding: ComplaintListBinding): RecyclerView.ViewHolder(binding.root){
}
class ComplaintAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var complaintData = mutableListOf<ComplaintListResponse>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return ComplaintViewHolder(ComplaintListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as ComplaintViewHolder).binding
        binding.complaintItem.text = complaintData[position].content
    }

    override fun getItemCount(): Int{
        return complaintData?.size ?: 0
    }
}