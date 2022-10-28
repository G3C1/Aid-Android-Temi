package com.g3c1.oasis_android_temi.ui.order_info

import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.g3c1.oasis_android_temi.R
import com.g3c1.oasis_android_temi.data.remote.util.ApiState
import com.g3c1.oasis_android_temi.databinding.ActivityMainBinding
import com.g3c1.oasis_android_temi.dto.purchase.OrderInfo
import com.g3c1.oasis_android_temi.ui.adapter.OrderAdapter
import com.g3c1.oasis_android_temi.ui.base.BaseActivity
import com.g3c1.oasis_android_temi.ui.util.ItemDecorator
import com.g3c1.oasis_android_temi.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val orderAdapter = OrderAdapter()
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var result: List<OrderInfo>

    override fun init() {
        getOrderList()
    }

    private fun itemOnclick() {
        orderAdapter.setOnItemClickListener(object : OrderAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                binding.moveBtn.visibility = View.VISIBLE
                binding.tableNum.text = result[position].seatNumber.toString()
            }
        })
    }

    private fun getOrderList() {
        lifecycleScope.launch {
            mainViewModel.getOrderList()
            mainViewModel.mOrderDataList.collect {
                when (it) {
                    is ApiState.Success -> {
                        Log.d("loading", it.data.toString())
                        result = it.data!!
                        mainViewModel.mOrderDataList.value = ApiState.Loading()
                        with(binding.orderRecycler) {
                            layoutManager = GridLayoutManager(
                                context,
                                2,
                                GridLayoutManager.VERTICAL,
                                false
                            )
                            addItemDecoration(ItemDecorator(90, "VERTICAL"))
                            setHasFixedSize(true)
                            binding.orderRecycler.adapter = orderAdapter
                            orderAdapter.submitList(it.data)
                        }
                        itemOnclick()
                    }
                    is ApiState.Error -> {
                        Log.e("loading", it.message.toString())
                        mainViewModel.mOrderDataList.value = ApiState.Loading()
                    }
                    is ApiState.Loading -> {
                        Log.d("loading", "loading")
                    }
                }
            }
        }
    }
}