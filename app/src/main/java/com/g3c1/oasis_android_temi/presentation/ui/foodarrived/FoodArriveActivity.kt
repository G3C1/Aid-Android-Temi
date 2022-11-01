package com.g3c1.oasis_android_temi.presentation.ui.foodarrived

import androidx.activity.viewModels
import com.g3c1.oasis_android_temi.R
import com.g3c1.oasis_android_temi.databinding.ActivityFoodBinding
import com.g3c1.oasis_android_temi.presentation.base.BaseActivity
import com.g3c1.oasis_android_temi.presentation.viewmodel.MainViewModel

class FoodArriveActivity : BaseActivity<ActivityFoodBinding>(R.layout.activity_food) {

    private val viewModel by viewModels<MainViewModel>()

    override fun init() {
        binding.backBtn.setOnClickListener {
            viewModel.robot.goTo("홈베이스")
        }
    }
}