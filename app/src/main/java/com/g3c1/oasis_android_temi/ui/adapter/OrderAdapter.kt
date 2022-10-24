package com.g3c1.oasis_android_temi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.g3c1.oasis_android_temi.databinding.OrderListItemBinding
import com.g3c1.oasis_android_temi.ui.Utils.ItemDecorator
import com.g3c1.oasis_android_temi.ui.adapter.dto.FoodInfo
import com.g3c1.oasis_android_temi.ui.adapter.dto.OrderInfo

class OrderAdapter : ListAdapter<OrderInfo, OrderAdapter.OrderViewHolder>(diffCallBack) {
    class OrderViewHolder(private val binding: OrderListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val detailOrderAdapter = DetailOrderAdapter()

        fun bind(data: OrderInfo) {
            binding.tableNum.text = data.tableNum.toString()
            binding.executePendingBindings()
        }

        fun settingRecycler(foodList: List<FoodInfo>) {
            with(binding.foodRecycler) {
                layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                setHasFixedSize(true)
            }
            binding.foodRecycler.adapter = detailOrderAdapter
            detailOrderAdapter.submitList(foodList)
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
        val foodList = mutableListOf(
            FoodInfo("스파게티", 3),
            FoodInfo("돈까스", 2),
            FoodInfo("치킨", 1),
            FoodInfo("짜장면", 4),
            FoodInfo("족발", 1),
        )

        holder.bind(getItem(position))
        holder.settingRecycler(foodList)
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