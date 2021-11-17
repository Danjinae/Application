package com.danjinae

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.danjinae.databinding.FragmentCarBinding


class CarFragment : Fragment() {
    lateinit var btnCarSearch: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_car, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCarBinding.bind(view)
        btnCarSearch = binding.btGuestcar

        btnCarSearch.setOnClickListener{
            var dialog = CarRegistrationFragment()
            dialog.show(childFragmentManager,"carDialog")
        }
    }

}