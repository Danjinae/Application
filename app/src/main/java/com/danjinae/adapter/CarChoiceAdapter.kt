package com.danjinae.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danjinae.databinding.CarListBinding
import com.danjinae.vo.VehicleResponse

class CarChoiceViewHolder(val binding: CarListBinding): RecyclerView.ViewHolder(binding.root){
}
class CarChoiceAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var carData = mutableListOf<VehicleResponse>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return CarChoiceViewHolder(CarListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as CarChoiceViewHolder).binding
        binding.carNum.text = carData[position].number

        if(carData[position].guest) {
            binding.carClass.text = "우리아파트 방문 차량입니다."
        }else{
            binding.carClass.text = "우리아파트 입주민 차량입니다."
        }
        binding.btnCall.setOnClickListener {
        }
    }

    override fun getItemCount(): Int{
        return carData?.size ?: 0
    }
}