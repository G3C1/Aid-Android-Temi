package com.g3c1.oasis_android_temi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.g3c1.oasis_android_temi.databinding.OrderListItemItemBinding
import com.g3c1.oasis_android_temi.dto.purchase.FoodInfo

class DetailOrderAdapter :
    ListAdapter<FoodInfo, DetailOrderAdapter.DetailOrderViewHolder>(diffCallBack) {
    class DetailOrderViewHolder(private val binding: OrderListItemItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: FoodInfo) {
            binding.foodName.text = data.food
            binding.foodCount.text = data.foodCnt.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailOrderViewHolder {
        return DetailOrderViewHolder(
            OrderListItemItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailOrderViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<FoodInfo>() {
            override fun areItemsTheSame(
                oldItem: FoodInfo,
                newItem: FoodInfo
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: FoodInfo,
                newItem: FoodInfo
            ): Boolean {
                return oldItem.food == newItem.food && oldItem.foodCnt == newItem.foodCnt
            }
        }
    }
}