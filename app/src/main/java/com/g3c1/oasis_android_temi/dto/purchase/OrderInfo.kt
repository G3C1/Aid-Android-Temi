package com.g3c1.oasis_android_temi.dto.purchase

data class OrderInfo (
    var foodInfoList: List<FoodInfo>,
    var seatNumber: Int,
    var seatId: Long
)