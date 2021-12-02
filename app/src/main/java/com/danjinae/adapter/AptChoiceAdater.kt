package com.danjinae.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danjinae.databinding.AptListBinding
import com.danjinae.vo.AptListResponse

class AptChoiceViewHolder(val binding: AptListBinding): RecyclerView.ViewHolder(binding.root){
}
class AptChoiceAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var aptData = mutableListOf<AptListResponse>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return AptChoiceViewHolder(AptListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as AptChoiceViewHolder).binding
        binding.aptName.text = aptData[position].name
        binding.aptAddress.text = aptData[position].address
    }

    override fun getItemCount(): Int{
        return aptData?.size ?: 0
    }
}