package com.g3c1.oasis_android_temi.dto.purchase

data class OrderInfoDTO (
    var foodInfoList: List<FoodInfoDTO>,
    var seatNumber: Int,
    var seatId: Long
)