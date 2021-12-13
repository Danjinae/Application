package com.danjinae.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.danjinae.R
import com.danjinae.vo.CommentResponse

class CommentListViewAdpater(val context : Context, val List : MutableList<CommentResponse>) : BaseAdapter(){

    // View를 꾸며주는 부분
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        // converView가 null이 아닐 경우 View를 재활용
        // 이 부분이 없다면, View를 리스트의 갯수만큼 호출해야 함
        var convertView = convertView
        val holder : ViewHolder


        if (convertView == null) {
            // list_view_item 을 가져온다
            convertView = LayoutInflater.from(parent?.context).inflate(R.layout.comment_list, parent, false)

            // 최초로 화면을 실행할 때, viewholder를사용
            holder = ViewHolder()
            holder.title = convertView?.findViewById(R.id.text_comment)
            holder.name = convertView?.findViewById(R.id.text_commentName)
            convertView.tag = holder
        } else {
            // 이미 만들어진 View가 있으므로, converview.tag를 불러온다
            holder = convertView.tag as ViewHolder
        }

        // 실제 데이터를 holder와 연결
        holder.title!!.text = List[position].comment
        holder.name!!.text = "익명"+List[position].usrId
        return convertView

    }

    // 각각의 리스트 하나씩 가져오는 부분
    override fun getItem(position: Int): Any {
        return List[position]
    }

    // 리스트의 ID를 가져오는 부분
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // 리스트의 전체 크기
    override fun getCount(): Int {
        return List.size
    }


    // Viewholder 정의
    private class ViewHolder {
        var title : TextView? = null
        var name : TextView? = null
    }

}

