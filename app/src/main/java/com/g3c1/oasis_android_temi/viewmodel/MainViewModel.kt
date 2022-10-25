package com.g3c1.oasis_android_temi.viewmodel

import androidx.lifecycle.ViewModel
import com.robotemi.sdk.Robot
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val robot: Robot
) : ViewModel() {

}