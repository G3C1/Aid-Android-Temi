package com.g3c1.oasis_android_temi.presentation.ui.splash

import android.content.Intent
import com.g3c1.oasis_android_temi.R
import com.g3c1.oasis_android_temi.databinding.ActivitySplashBinding
import com.g3c1.oasis_android_temi.di.TemiApplication
import com.g3c1.oasis_android_temi.presentation.base.BaseActivity
import com.g3c1.oasis_android_temi.presentation.ui.orderinfo.MainActivity
import com.g3c1.oasis_android_temi.presentation.ui.qrscan.QrScanActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    override fun init() {
        CoroutineScope(Dispatchers.Main).launch {
            if (TemiApplication.getInstance()
                    .getSearialNumberManager().searialNumber.first() == 0
            ) {
                startActivity(Intent(this@SplashActivity, QrScanActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
        }
    }
}