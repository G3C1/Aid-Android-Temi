package com.g3c1.oasis_android_temi.di

import android.app.Application
import com.g3c1.oasis_android_temi.presentation.util.SearialNumberManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TemiApplication : Application() {
    private lateinit var searialNumberManager: SearialNumberManager

    companion object {
        private lateinit var temiApplication: TemiApplication
        fun getInstance(): TemiApplication = temiApplication
    }

    override fun onCreate() {
        super.onCreate()
        temiApplication = this
        searialNumberManager = SearialNumberManager(this)
    }

    fun getSearialNumberManager(): SearialNumberManager = searialNumberManager
}