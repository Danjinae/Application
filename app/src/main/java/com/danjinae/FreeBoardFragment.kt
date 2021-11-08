package com.danjinae

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.danjinae.databinding.FragmentFreeBoardBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout


class FreeBoardFragment : Fragment() {

    private lateinit var fabwrite: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view: View = inflater.inflate(R.layout.fragment_free_board, container, false)
        fabwrite = view.findViewById(R.id.fab_write)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabwrite.setOnClickListener{
            val intent = Intent (this.context, FreeBoardActivity::class.java)
            startActivity(intent)
        }
    }
}