package com.g3c1.oasis_android_temi.ui.order_info

import android.content.Intent
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
import com.g3c1.oasis_android_temi.ui.moving.MovingActivity
import com.g3c1.oasis_android_temi.ui.util.ItemDecorator
import com.g3c1.oasis_android_temi.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val TAG = "MainActivity"
    private val orderAdapter = OrderAdapter()
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var result: List<OrderInfo>
    private var isClick = false
    private var seatId: Long = 0
    private var seatNum: Int = 0

    override fun init() {
        mainViewModel.robot.setKioskModeOn(true)
        getOrderList()
        onClick()
    }

    private fun onClick() {
        val intent = Intent(this, MovingActivity::class.java)
        binding.moveBtn.setOnClickListener {
            intent.putExtra("seatNum", seatNum.toString())
            moveTemi(seatId = seatId)
            startActivity(intent)
        }
    }

    private fun itemOnclick() {
        orderAdapter.setOnItemClickListener(object : OrderAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                if (!isClick) {
                    isClick = true
                    binding.moveBtn.visibility = View.VISIBLE
                } else {
                    isClick = false
                    binding.moveBtn.visibility = View.INVISIBLE
                }
                seatId = result[position].seatId
                seatNum = result[position].seatNumber
                binding.tableNum.text = result[position].seatNumber.toString()
            }
        })
    }

    private fun moveTemi(seatId: Long) {
        lifecycleScope.launch {
            mainViewModel.moveTemi(seatId = seatId)
            mainViewModel.mMoveTemi.collect {
                when (it) {
                    is ApiState.Success -> {
                        Log.d(TAG, "MoveTemi: Success")
                    }
                    is ApiState.Error -> {
                        Log.e(TAG, it.message.toString())
                        mainViewModel.mOrderDataList.value = ApiState.Loading()
                    }
                    is ApiState.Loading -> {
                        Log.d(TAG, "MoveTemi: loading")
                    }
                }
            }
        }
    }

    private fun getOrderList() {
        lifecycleScope.launch {
            mainViewModel.getOrderList()
            mainViewModel.mOrderDataList.collect {
                when (it) {
                    is ApiState.Success -> {
                        Log.d(TAG, it.data.toString())
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
                            orderAdapter.submitList(result)
                        }
                        itemOnclick()
                    }
                    is ApiState.Error -> {
                        Log.e(TAG, it.message.toString())
                        mainViewModel.mOrderDataList.value = ApiState.Loading()
                    }
                    is ApiState.Loading -> {
                        Log.d(TAG, "OrderList: loading")
                    }
                }
            }
        }
    }
}