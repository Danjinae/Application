package com.danjinae

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danjinae.databinding.BoardAddBinding
import com.danjinae.vo.PostModel

class BoardViewHolder(val binding: BoardAddBinding): RecyclerView.ViewHolder(binding.root)

class BoardAdapter(val data: MutableList<PostModel>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return BoardViewHolder(BoardAddBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as BoardViewHolder).binding
        binding.boardTitle.text= data[position].title
        binding.boardContent.text= data[position].content
    }
    override fun getItemCount(): Int{
        return data?.size ?: 0
    }
}