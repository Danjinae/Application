package com.danjinae

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.danjinae.databinding.ActivityFreeBoardBinding
import com.danjinae.vo.PostModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FreeBoardActivity : AppCompatActivity() {
    lateinit var binding: ActivityFreeBoardBinding
    lateinit var btnPost: Button
    lateinit var postTitle: EditText
    lateinit var postContent: EditText
    val data = arrayListOf<Post>()
    lateinit var adapter: BoardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFreeBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var postModel = PostModel()
        postTitle = binding.editPostTitle
        postContent = binding.editPostContents

        Log.d("등록", postTitle.text.toString())

        binding.btnPost.setOnClickListener {
            Log.d("등록", "진입")
            postModel.aptId = 1
            postModel.content = postContent.text.toString()
            postModel.title =  postTitle.text.toString()
            postModel.userId = 4

            val call: Call<PostModel> = RetrofitClient.networkService.addPost(postModel)
            call.enqueue(object : Callback<PostModel> {
                override fun onResponse(
                    call: Call<PostModel>,
                    response: Response<PostModel>
                ) {
                    if (response.isSuccessful) {
                        Log.d("조회", "성공 : ${response.raw()}")
                    }
                    Log.d("조회2", "실패 : ${response.errorBody()?.string()!!}")
                }

                override fun onFailure(call: Call<PostModel>, t: Throwable) {
                    Log.d("조회3", "실패 : $t")
                }
            })
        }

    }
}