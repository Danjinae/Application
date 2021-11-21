package com.danjinae.view.community

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.danjinae.BoardAdapter
import com.danjinae.databinding.FragmentFreeBoardBinding
import com.danjinae.network.RetrofitClient
import com.danjinae.vo.CommentModel
import com.danjinae.vo.PostModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FreeBoardFragment : Fragment() {
    lateinit var resultLauncher: ActivityResultLauncher<Intent>
    lateinit var addFAB: FloatingActionButton
    val datas = mutableListOf<PostModel>()
    var postModel = PostModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFreeBoardBinding.inflate(inflater, container, false)
        addFAB = binding.fabWrite

        val layoutManager = LinearLayoutManager(context)
        val boardAdapter = BoardAdapter(datas)
        binding.boardRecyclerView.layoutManager = layoutManager
        binding.boardRecyclerView.adapter = boardAdapter

//        val call: Call<PostList> = RetrofitClient.networkService.getPostList()
//        call.enqueue(object : Callback<PostList> {
//            override fun onResponse(
//                call: Call<PostList>,
//                response: Response<PostList>
//            ) {
//                //val reusltData: Result = response.body()
//                Log.d("데이터","데이터")
//                if (response.isSuccessful) {
//                    Log.d("조회", "성공 : ${response.raw()}")
//                }
//                Log.d("조회", "실패 : ${response.errorBody()?.string()!!}")
//            }
//            override fun onFailure(call: Call<PostList>, t: Throwable) {
//                Log.d("조회", "실패 : $t")
//            }
//        })

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == Activity.RESULT_OK){
               postModel = it.data?.getParcelableExtra("post")!!
                datas.add(postModel)
                boardAdapter.notifyDataSetChanged()
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        addFAB.setOnClickListener {
            onResume()
            val intent = Intent(this.context, FreeBoardActivity::class.java)
            resultLauncher.launch(intent)
        }

        //var a: CommentModel
        var a = CommentModel()
        a.comment = "a"
        a.userId = 0
        val call: Call<Boolean> = RetrofitClient.networkService.postComment(a,1)
        call.enqueue(object : Callback<Boolean>{
            override fun onResponse(
                call: Call<Boolean>,
                response: Response<Boolean>
            ){
                if(response.isSuccessful){
                    Log.d("1","1")
                } else{
                    Log.d("2","2")
                }
            }
            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Log.d("조회", "실패 : $t")
            }
        })
    }
}


