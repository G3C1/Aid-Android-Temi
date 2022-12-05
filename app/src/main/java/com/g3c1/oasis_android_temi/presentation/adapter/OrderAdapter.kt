package com.g3c1.oasis_android_temi.presentation.adapter

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.g3c1.oasis_android_temi.R
import com.g3c1.oasis_android_temi.databinding.OrderListItemBinding
import com.g3c1.oasis_android_temi.dto.purchase.OrderInfoDTO
import com.g3c1.oasis_android_temi.presentation.util.ItemDecorator

class OrderAdapter :
    ListAdapter<OrderInfoDTO, OrderAdapter.OrderViewHolder>(diffCallBack) {

    inner class OrderViewHolder(
        private val binding: OrderListItemBinding,
        listener: OnItemClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        private val detailOrderAdapter = DetailOrderAdapter()

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
                binding.orderItem.setBackgroundResource(if (binding.orderItem.isSelected) R.drawable.onclick_recycler_bg else R.drawable.recycler_bg)
            }
        }

        fun bind(data: OrderInfoDTO) {
            binding.tableNum.text = data.seatNumber.toString()
            binding.foodRecycler.adapter = detailOrderAdapter
            with(binding.foodRecycler) {
                layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                setHasFixedSize(true)
                addItemDecoration(ItemDecorator(20,"VERTICAL"))
            }
            detailOrderAdapter.submitList(data.foodInfoList)
            binding.executePendingBindings()
        }
    }

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
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
        val diffCallBack = object : DiffUtil.ItemCallback<OrderInfoDTO>() {
            override fun areItemsTheSame(
                oldItem: OrderInfoDTO,
                newItem: OrderInfoDTO
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: OrderInfoDTO,
                newItem: OrderInfoDTO
            ): Boolean {
                return oldItem.seatNumber == newItem.seatNumber && oldItem.foodInfoList == newItem.foodInfoList
            }
        }
    }
}