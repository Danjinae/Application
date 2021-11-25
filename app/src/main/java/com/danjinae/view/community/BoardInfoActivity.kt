package com.danjinae.view.community

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.danjinae.R
import com.danjinae.adapter.ListViewItem
import com.danjinae.databinding.ActivityBoardInfoBinding
import com.danjinae.vo.Comment

class BoardInfoActivity : AppCompatActivity() {
    var TAG = "BoardInfo"
    lateinit var binding: ActivityBoardInfoBinding
    lateinit var postTitlea: TextView
    lateinit var postContenta: TextView
    lateinit var editComment: EditText
    lateinit var btnComment: Button
    lateinit var listComment: ListView
    var commentModel = Comment()
    var item = mutableListOf<ListViewItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_info)
        binding= ActivityBoardInfoBinding.inflate(layoutInflater)
        postTitlea= binding.textPostTitle
        postContenta = binding.textPostContents
        editComment = binding.inputComment
        btnComment = binding.btnComment
        listComment = binding.listComment

        commentModel.comment = "comment"
        commentModel.userId = 1

        binding.btnComment.setOnClickListener{
            Log.d("버튼클릭","진입")
        }
    }
}