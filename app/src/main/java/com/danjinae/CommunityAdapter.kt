package com.danjinae

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CommunityAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {

   val fragments: List<Fragment>

   init {
       fragments= listOf(FreeBoardFragment(),NoticeFragment())
   }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}