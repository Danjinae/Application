package com.danjinae.view.community

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.danjinae.adapter.CommentListViewAdpater
import com.danjinae.databinding.ActivityBoardInfoBinding
import com.danjinae.network.RetrofitClient
import com.danjinae.vo.Comment
import com.danjinae.vo.CommentList
import com.danjinae.vo.CommentResponse
import com.danjinae.vo.PostResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BoardInfoActivity : AppCompatActivity() {
    var TAG = "BoardInfo"
    lateinit var binding: ActivityBoardInfoBinding
    lateinit var postTitle: TextView
    lateinit var postContent: TextView
    lateinit var editComment: EditText
    lateinit var btnComment: Button
    lateinit var listComment: ListView
    var commentModel = CommentResponse
    var comment = mutableListOf<CommentResponse>()
    var dataList= mutableListOf<CommentResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBoardInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        postTitle= binding.textPostTitle
        postContent = binding.textPostContents
        editComment = binding.inputComment
        btnComment = binding.btnComment
        listComment = binding.listComment

        val postId = intent.getIntExtra("postId",-1)
        Log.d("postid",postId.toString())

        val callPost: Call<PostResponse> = RetrofitClient.networkService.selectPost(postId)
        callPost.enqueue(object : Callback<PostResponse> {
            override fun onResponse(
                call: Call<PostResponse>,
                response: Response<PostResponse>
            ){
                if(response.isSuccessful){
                    postTitle.text = response.body()?.title
                    postContent.text = response.body()?.content
                } else{
                    Log.d(TAG, "실패1 : ${response.errorBody()?.string()!!}")
                }
            }
            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                Log.d(TAG, "실패2 : $t")
            }
        })

        val adapter = CommentListViewAdpater(this,comment)
        listComment.adapter = adapter

        GetComment(postId, adapter)

        var postComment = Comment()
        binding.btnComment.setOnClickListener{
            Log.d("버튼클릭","진입")
            postComment.comment = editComment.text.toString()
            val call: Call<Boolean> = RetrofitClient.networkService.postComment(postComment,postId)
            call.enqueue(object : Callback<Boolean> {
                override fun onResponse(
                    call: Call<Boolean>,
                    response: Response<Boolean>
                ){
                    if(response.isSuccessful){
                        GetComment(postId, adapter)
                    } else{
                        Log.d(TAG, "실패1 : ${response.errorBody()?.string()!!}")
                    }
                }
                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    Log.d(TAG, "실패2 : $t")
                }
            })
        }
    }

    private fun GetComment(postId: Int, adapter: CommentListViewAdpater) {
        val callComment: Call<CommentList> = RetrofitClient.networkService.getComment(postId)
        callComment.enqueue(object : Callback<CommentList> {
            override fun onResponse(
                call: Call<CommentList>,
                response: Response<CommentList>
            ) {
                if (response.isSuccessful) {
                    adapter.List.clear()
                    var dataList = response.body()?.content
                    if (dataList != null && dataList.size > 0) {
                        for (i in 0 until dataList.size) {
                            comment.add(dataList[i])
                            adapter.notifyDataSetChanged()
                        }
                        Log.d(TAG, comment.toString())
                    }
                } else {
                    Log.d(TAG, "실패1 : ${response.errorBody()?.string()!!}")
                }
            }

            override fun onFailure(call: Call<CommentList>, t: Throwable) {
                Log.d(TAG, "실패2 : $t")
            }
        })
    }
}