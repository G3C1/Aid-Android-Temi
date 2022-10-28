package com.g3c1.oasis_android_temi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.g3c1.oasis_android_temi.R
import com.g3c1.oasis_android_temi.databinding.OrderListItemBinding
import com.g3c1.oasis_android_temi.dto.purchase.OrderInfo

class OrderAdapter : ListAdapter<OrderInfo, OrderAdapter.OrderViewHolder>(diffCallBack) {
    class OrderViewHolder(
        private val binding: OrderListItemBinding,
        listener: onItemClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        private val detailOrderAdapter = DetailOrderAdapter()
        private var isClick = false

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
                if (!isClick) {
                    isClick = true
                    binding.orderItem.setBackgroundResource(R.drawable.onclick_recycler_bg)
                } else {
                    isClick = false
                    binding.orderItem.setBackgroundResource(R.drawable.recycler_bg)
                }
            }
        }

        fun bind(data: OrderInfo) {
            binding.tableNum.text = data.seatNumber.toString()
            binding.foodRecycler.adapter = detailOrderAdapter
            with(binding.foodRecycler) {
                layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                setHasFixedSize(true)
            }
            detailOrderAdapter.submitList(data.foodInfoList)
            binding.executePendingBindings()
        }
    }

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(
            OrderListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), mListener
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
                return oldItem.seatNumber == newItem.seatNumber && oldItem.foodInfoList == newItem.foodInfoList
            }
        }
    }
}