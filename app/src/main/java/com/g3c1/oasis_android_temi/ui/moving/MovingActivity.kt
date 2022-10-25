package com.g3c1.oasis_android_temi.ui.moving

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import androidx.activity.viewModels
import com.g3c1.oasis_android_temi.R
import com.g3c1.oasis_android_temi.base.BaseActivity
import com.g3c1.oasis_android_temi.databinding.ActivityMovingBinding
import com.g3c1.oasis_android_temi.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovingActivity : BaseActivity<ActivityMovingBinding>(R.layout.activity_moving) {

    private val vm by viewModels<MainViewModel>()

    override fun init() {
        animation()
    }

    private fun animation() {
        val goFront = ObjectAnimator.ofFloat(binding.temi, "translationX", 430f).apply {
            duration = 3000
            repeatCount = ValueAnimator.INFINITE
        }
        goFront.start()
    }
}