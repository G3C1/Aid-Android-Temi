package com.g3c1.oasis_android_temi.presentation.ui.foodarrived

import android.content.Intent
import androidx.activity.viewModels
import com.g3c1.oasis_android_temi.R
import com.g3c1.oasis_android_temi.databinding.ActivityFoodBinding
import com.g3c1.oasis_android_temi.presentation.base.BaseActivity
import com.g3c1.oasis_android_temi.presentation.ui.moving.MovingActivity
import com.g3c1.oasis_android_temi.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodArriveActivity : BaseActivity<ActivityFoodBinding>(R.layout.activity_food) {

    private val viewModel by viewModels<MainViewModel>()

    override fun init() {
        val intent = Intent(this, MovingActivity::class.java)
        binding.backBtn.setOnClickListener {
            intent.putExtra("seatNum", "홈베이스")
            viewModel.robot.goTo("홈베이스")
            startActivity(intent)
        }
    }
}