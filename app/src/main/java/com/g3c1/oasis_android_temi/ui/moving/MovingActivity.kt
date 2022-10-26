package com.g3c1.oasis_android_temi.ui.moving

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.animation.AnimationUtils
import com.g3c1.oasis_android_temi.R
import com.g3c1.oasis_android_temi.base.BaseActivity
import com.g3c1.oasis_android_temi.databinding.ActivityMovingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovingActivity : BaseActivity<ActivityMovingBinding>(R.layout.activity_moving) {
    override fun init() {
        animation()
    }

    private fun animation() {
        val anim = AnimationUtils.loadAnimation(this, R.anim.alpha)
        val anim2 = AnimationUtils.loadAnimation(this, R.anim.alpha2)
        val anim3 = AnimationUtils.loadAnimation(this, R.anim.alpha3)

        val goFront = ObjectAnimator.ofFloat(binding.temi, "translationX", 480f).apply {
            duration = 3000
            repeatCount = ValueAnimator.INFINITE
        }

        with(binding) {
            exDot1.animation = anim
            exDot2.animation = anim2
            exDot3.animation = anim3
        }
        goFront.start()
    }
}