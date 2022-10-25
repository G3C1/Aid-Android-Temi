package com.g3c1.oasis_android_temi.ui.moving

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import androidx.activity.viewModels
import com.g3c1.oasis_android_temi.R
import com.g3c1.oasis_android_temi.base.BaseActivity
import com.g3c1.oasis_android_temi.databinding.ActivityMovingBinding
import com.g3c1.oasis_android_temi.viewmodel.MainViewModel


class MovingActivity : BaseActivity<ActivityMovingBinding>(R.layout.activity_moving) {

    private val vm by viewModels<MainViewModel>()

    override fun init() {
        animation()
    }

    private fun animation() {
        val goFront = ObjectAnimator.ofFloat(binding.temi, "translationX", 430f).apply {
            duration = 4000
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
                    vm.setValue(false)
                }
            })
        }

        val goback = ObjectAnimator.ofFloat(binding.temi, "translationX", 0f).apply {
            duration = 4000
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
                    vm.setValue(true)
                }
            })
        }
        goFront.start()
        vm.state.observe(this) {
            when (it) {
                true -> goFront.start()
                false -> goback.start()
            }
        }
    }
}