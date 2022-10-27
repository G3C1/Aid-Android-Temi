package com.g3c1.oasis_android_temi.ui.order_info

import androidx.recyclerview.widget.GridLayoutManager
import com.g3c1.oasis_android_temi.R
import com.g3c1.oasis_android_temi.ui.base.BaseActivity
import com.g3c1.oasis_android_temi.databinding.ActivityMainBinding
import com.g3c1.oasis_android_temi.ui.util.ItemDecorator
import com.g3c1.oasis_android_temi.ui.adapter.OrderAdapter
import com.g3c1.oasis_android_temi.dto.FoodInfo
import com.g3c1.oasis_android_temi.dto.OrderInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val orderAdapter = OrderAdapter()
    private val orderList = mutableListOf(
        OrderInfo(
            mutableListOf(
                FoodInfo("후라이드 치킨", 2),
                FoodInfo("후라이드 치킨", 2),
                FoodInfo("후라이드 치킨", 2)
            ), 1
        ),
        OrderInfo(
            mutableListOf(
                FoodInfo("후라이드 치킨", 4),
                FoodInfo("후라이드 치킨", 2),
                FoodInfo("후라이드 치킨", 2)
            ), 2
        ),
        OrderInfo(mutableListOf(FoodInfo("후라이드 치킨", 7)), 3),
        OrderInfo(mutableListOf(FoodInfo("후라이드 치킨", 2)), 4),
        OrderInfo(mutableListOf(FoodInfo("후라이드 치킨", 3)), 5),
        OrderInfo(mutableListOf(FoodInfo("후라이드 치킨", 1)), 6),
    )

    override fun init() {
        settingRecycler()
    }

    fun settingRecycler() {
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
            orderAdapter.submitList(orderList)
        }
    }
}