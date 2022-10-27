package com.g3c1.oasis_android_temi.ui.order_info

import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.g3c1.oasis_android_temi.R
import com.g3c1.oasis_android_temi.data.remote.util.ApiState
import com.g3c1.oasis_android_temi.databinding.ActivityMainBinding
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

    override fun init() {
        getOrderList()
    }

    private fun getOrderList() {
        lifecycleScope.launch {
            mainViewModel.getOrderList()
            mainViewModel.mOrderDataList.collect {
                when (it) {
                    is ApiState.Success -> {
                        Log.d("TAG", it.data.toString())
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
                    }
                    is ApiState.Error -> {
                        Log.e("TAG", it.message.toString())
                        mainViewModel.mOrderDataList.value = ApiState.Loading()
                    }
                    is ApiState.Loading -> {}
                }
            }
        }
    }
}