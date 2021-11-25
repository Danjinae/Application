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
import com.danjinae.vo.Post
import com.danjinae.vo.PostList
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class FreeBoardFragment : Fragment() {
    lateinit var resultLauncher: ActivityResultLauncher<Intent>
    lateinit var addFAB: FloatingActionButton
    var datas = mutableListOf<Post>()
    var postModel = Post()

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

            val call: Call<PostList> = RetrofitClient.networkService.getPostList()
            call.enqueue(object : Callback<PostList> {
                override fun onResponse(
                    call: Call<PostList>,
                    response: Response<PostList>
                ){
                    if(response.isSuccessful){
                        var dataList: ArrayList<Post>? = response.body()?.content
                        if (dataList != null && dataList.size > 0) {
                            for(i in 0 until dataList.size){
                                datas.add(dataList[i])
                            }
                            Log.d("데이터",datas.toString())
                            boardAdapter.notifyDataSetChanged()
                        }
                    } else{
                        Log.d("2","2")
                    }
                }
                override fun onFailure(call: Call<PostList>, t: Throwable) {
                    Log.d("조회", "실패 : $t")
                }
            })


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
    }
}


