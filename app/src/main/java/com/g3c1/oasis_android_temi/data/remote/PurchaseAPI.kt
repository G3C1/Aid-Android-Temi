package com.g3c1.oasis_android_temi.data.remote

import com.g3c1.oasis_android_temi.dto.OrderInfo
import retrofit2.Response
import retrofit2.http.GET

interface PurchaseAPI {
    @GET("/purchase/")
    suspend fun getOrderList(): Response<List<OrderInfo>>
}