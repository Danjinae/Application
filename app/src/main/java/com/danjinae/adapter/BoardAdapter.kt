package com.danjinae

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danjinae.databinding.BoardAddBinding
import com.danjinae.view.community.BoardInfoActivity
import com.danjinae.vo.Post


class BoardViewHolder(val binding: BoardAddBinding): RecyclerView.ViewHolder(binding.root){
}

class BoardAdapter(val data: MutableList<Post>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var idx: Int = 0
    private var context: Context? = null

    fun BoardAdapter(context: Context){
        this.context = context
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return BoardViewHolder(BoardAddBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as BoardViewHolder).binding
        binding.boardTitle.text = data[position].title
        binding.boardContent.text = data[position].content
        val postId: Int = data[position].postId

        binding.cardPostItem.setOnClickListener {
            Intent(holder.itemView.context, BoardInfoActivity::class.java).apply {
                this.putExtra("postId",postId)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }.run { holder.itemView.context?.startActivity(this) }
        }

    }

    override fun getItemCount(): Int{
        return data?.size ?: 0
    }
}