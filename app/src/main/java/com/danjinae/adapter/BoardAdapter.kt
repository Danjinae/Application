package com.danjinae

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.danjinae.databinding.BoardAddBinding
import com.danjinae.network.RetrofitClient
import com.danjinae.view.community.BoardInfoActivity
import com.danjinae.vo.Post
import com.danjinae.vo.PostDelete
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

        binding.delete.setOnClickListener {
            val call: Call<PostDelete> = RetrofitClient.networkService.postDelete(data[position].postId)
            call.enqueue(object : Callback<PostDelete> {
                override fun onResponse(
                    call: Call<PostDelete>,
                    response: Response<PostDelete>
                ) {
                    if (response.isSuccessful) {
                        Log.d("조회", "성공 : ${response.body()?.content}")
                    } else {
                        Toast.makeText(context, "검색결과가 없습니다", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<PostDelete>, t: Throwable) {
                    Log.d("연결", "실패 : $t")
                }
            })
        }
    }

    override fun getItemCount(): Int{
        return data?.size ?: 0
    }
}