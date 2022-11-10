package com.g3c1.oasis_android_temi.presentation.ui.moving

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.util.Log
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import com.g3c1.oasis_android_temi.R
import com.g3c1.oasis_android_temi.databinding.ActivityMovingBinding
import com.g3c1.oasis_android_temi.presentation.base.BaseActivity
import com.g3c1.oasis_android_temi.presentation.ui.foodarrived.FoodArriveActivity
import com.g3c1.oasis_android_temi.presentation.ui.orderinfo.MainActivity
import com.g3c1.oasis_android_temi.presentation.viewmodel.MainViewModel
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovingActivity : BaseActivity<ActivityMovingBinding>(R.layout.activity_moving),
    OnGoToLocationStatusChangedListener {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun init() {
        goTable()
        initFun()
    }

    override fun onGoToLocationStatusChanged(
        location: String,
        status: String,
        descriptionId: Int,
        description: String
    ) {
        Log.d("goto Status", status)
        when (status) {
            "complete" -> {
                startActivity(
                    if (intent.getStringExtra("seatNum") != "홈베이스") Intent(
                        this,
                        FoodArriveActivity::class.java
                    ) else Intent(this, MainActivity::class.java)
                )
            }
        }
    }

    private fun initFun() {
        initRobot()
        initAnimation()
        initUi()
    }

    private fun initRobot() {
        mainViewModel.robot.addOnGoToLocationStatusChangedListener(this)
        mainViewModel.robot.hideTopBar()
    }

    private fun initUi() {
        val seatNum = intent.getStringExtra("seatNum")
        binding.movingText.text =
            when (seatNum) {
                "홈베이스" -> {
                    getString(R.string.go_homebase)
                }
                else -> {
                    getString(R.string.go_table, seatNum)
                }
            }
    }

    private fun initAnimation() {
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

    private fun goTable() {
        mainViewModel.robot.goTo(intent.getStringExtra("seatNum")!!)
    }
}