package com.g3c1.oasis_android_temi.ui.food_arrived

import androidx.activity.viewModels
import com.g3c1.oasis_android_temi.R
import com.g3c1.oasis_android_temi.databinding.ActivityFoodBinding
import com.g3c1.oasis_android_temi.ui.base.BaseActivity
import com.g3c1.oasis_android_temi.ui.viewmodel.MainViewModel

class FoodArriveActivity : BaseActivity<ActivityFoodBinding>(R.layout.activity_food) {

    private val viewModel by viewModels<MainViewModel>()

    override fun init() {
        binding.backBtn.setOnClickListener {
            viewModel.robot.goTo("홈베이스")
        }
    }
}