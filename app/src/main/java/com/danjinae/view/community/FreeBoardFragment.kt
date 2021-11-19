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
import com.danjinae.vo.PostModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


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
        Log.d("post!!",datas.toString())
        addFAB.setOnClickListener {
            onResume()
            val intent = Intent(this.context, FreeBoardActivity::class.java)
            resultLauncher.launch(intent)
        }
    }
}


