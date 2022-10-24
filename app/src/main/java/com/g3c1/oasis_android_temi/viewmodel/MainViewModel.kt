package com.g3c1.oasis_android_temi.viewmodel

import androidx.lifecycle.ViewModel
import com.g3c1.oasis_android_temi.ui.adapter.dto.FoodInfo
import com.robotemi.sdk.Robot
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val robot: Robot
): ViewModel() {
    val foodList = mutableListOf(
        FoodInfo("스파게티", 3),
        FoodInfo("돈까스", 2),
        FoodInfo("치킨", 1),
        FoodInfo("짜장면", 4),
        FoodInfo("족발", 1),
    )
}