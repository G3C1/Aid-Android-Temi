package com.g3c1.oasis_android_temi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.g3c1.oasis_android_temi.databinding.OrderListItemBinding
import com.g3c1.oasis_android_temi.ui.adapter.dto.OrderInfo

class OrderAdapter : ListAdapter<OrderInfo, OrderAdapter.OrderViewHolder>(diffCallBack) {
    class OrderViewHolder(private val binding: OrderListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val detailOrderAdapter = DetailOrderAdapter()

        fun bind(data: OrderInfo) {
            binding.tableNum.text = data.tableNum.toString()
            binding.foodRecycler.adapter = detailOrderAdapter
            with(binding.foodRecycler) {
                layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                setHasFixedSize(true)
            }
            detailOrderAdapter.submitList(data.foodList)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(
            OrderListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<OrderInfo>() {
            override fun areItemsTheSame(
                oldItem: OrderInfo,
                newItem: OrderInfo
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: OrderInfo,
                newItem: OrderInfo
            ): Boolean {
                return oldItem.tableNum == newItem.tableNum && oldItem.foodList == newItem.foodList
            }
        }
    }
}