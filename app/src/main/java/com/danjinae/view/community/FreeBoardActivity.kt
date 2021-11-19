package com.danjinae.view.community

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.danjinae.databinding.ActivityFreeBoardBinding
import com.danjinae.databinding.FragmentCommunityBinding
import com.danjinae.databinding.FragmentFreeBoardBinding
import com.danjinae.network.RetrofitClient
import com.danjinae.vo.PostModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FreeBoardActivity : AppCompatActivity() {
    lateinit var binding: ActivityFreeBoardBinding
    lateinit var reCycleBinding: FragmentFreeBoardBinding
    lateinit var frameBinding: FragmentCommunityBinding
    lateinit var postTitle: EditText
    lateinit var postContent: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFreeBoardBinding.inflate(layoutInflater)
        reCycleBinding = FragmentFreeBoardBinding.inflate(layoutInflater)
        frameBinding = FragmentCommunityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var postModel = PostModel()
        postTitle = binding.editPostTitle
        postContent = binding.editPostContents

        binding.btnPost.setOnClickListener {
            Log.d("등록", "진입")
            postModel.aptId = 1
            postModel.content = postContent.text.toString()
            postModel.title =  postTitle.text.toString()
            postModel.userId = 4

            var intent = Intent().putExtra("post",postModel)
            setResult(RESULT_OK,intent)

            val call: Call<PostModel> = RetrofitClient.networkService.addPost(postModel)
            call.enqueue(object : Callback<PostModel> {
                override fun onResponse(
                    call: Call<PostModel>,
                    response: Response<PostModel>
                ) {
                    if (response.isSuccessful) {
                        Log.d("조회", "성공 : ${response.raw()}")
                    }
                    Log.d("조회", "실패 : ${response.errorBody()?.string()!!}")
                }
                override fun onFailure(call: Call<PostModel>, t: Throwable) {
                    Log.d("조회", "실패 : $t")
                }
            })
        }
    }
}