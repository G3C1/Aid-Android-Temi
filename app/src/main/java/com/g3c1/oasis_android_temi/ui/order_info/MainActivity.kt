package com.g3c1.oasis_android_temi.ui.order_info

import androidx.recyclerview.widget.GridLayoutManager
import com.g3c1.oasis_android_temi.R
import com.g3c1.oasis_android_temi.base.BaseActivity
import com.g3c1.oasis_android_temi.databinding.ActivityMainBinding
import com.g3c1.oasis_android_temi.ui.Utils.ItemDecorator
import com.g3c1.oasis_android_temi.ui.adapter.OrderAdapter
import com.g3c1.oasis_android_temi.ui.adapter.dto.FoodInfo
import com.g3c1.oasis_android_temi.ui.adapter.dto.OrderInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val orderAdapter = OrderAdapter()
    private val orderList = mutableListOf(
        OrderInfo(FoodInfo("스파게티",1),1),
        OrderInfo(FoodInfo("스파게티",1),1),
        OrderInfo(FoodInfo("스파게티",1),1),
        OrderInfo(FoodInfo("스파게티",1),1),
        OrderInfo(FoodInfo("스파게티",1),1),
        OrderInfo(FoodInfo("스파게티",1),1),
        OrderInfo(FoodInfo("스파게티",1),1),
    )

    override fun init() {
        settingRecycler()
    }

    fun settingRecycler() {
        with(binding.orderRecycler) {
            layoutManager = GridLayoutManager(
                context,
                4,
                GridLayoutManager.VERTICAL,
                false
            )
            setHasFixedSize(true)
            addItemDecoration(ItemDecorator(52,"HORIZONTAL"))
            binding.orderRecycler.adapter = orderAdapter
            orderAdapter.submitList(orderList)
        }
    }
}