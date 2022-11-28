package com.g3c1.oasis_android_temi.presentation.ui.orderinfo

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.g3c1.oasis_android_temi.R
import com.g3c1.oasis_android_temi.data.remote.util.ApiState
import com.g3c1.oasis_android_temi.databinding.ActivityMainBinding
import com.g3c1.oasis_android_temi.dto.purchase.OrderInfoDTO
import com.g3c1.oasis_android_temi.presentation.adapter.OrderAdapter
import com.g3c1.oasis_android_temi.presentation.base.BaseActivity
import com.g3c1.oasis_android_temi.presentation.ui.moving.MovingActivity
import com.g3c1.oasis_android_temi.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val TAG = "MainActivity"
    private val orderAdapter = OrderAdapter()
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var result: List<OrderInfoDTO>
    private var isClick = false
    private var seatId: Long = 0
    private var seatNum: Int = 0

    override fun init() {
        getOrderList()
        //inputTestData()
        initRecycler()
        initFun()
    }

    private fun initFun() {
        initRecycler()
        initRobot()
        initOnClick()
    }

    private fun initOnClick() {
        val intent = Intent(this, MovingActivity::class.java)
        binding.moveBtn.setOnClickListener {
            intent.putExtra("seatNum", seatNum.toString())
            moveTemi(seatId = seatId)
            startActivity(intent)
        }
    }

    private fun initRecycler() {
        with(binding.orderRecycler) {
            layoutManager = GridLayoutManager(
                context, 2, GridLayoutManager.VERTICAL, false
            )
            setHasFixedSize(true)
            binding.orderRecycler.adapter = orderAdapter
        }
    }

//    private fun inputTestData() {
//        result = mutableListOf(
//            OrderInfoDTO(mutableListOf(FoodInfoDTO("치킨", 3)), 1, 1),
//            OrderInfoDTO(mutableListOf(FoodInfoDTO("떡볶이", 3)), 2, 2),
//            OrderInfoDTO(mutableListOf(FoodInfoDTO("피자", 3)), 3, 3)
//        )
//        orderAdapter.submitList(result)
//        itemOnclick()
//    }

    private fun initRobot() {
        mainViewModel.robot.setKioskModeOn(true)
        mainViewModel.robot.hideTopBar()
    }

    private fun itemOnclick() {
        orderAdapter.setOnItemClickListener(object : OrderAdapter.OnItemClickListener {
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
                        orderAdapter.submitList(result)
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