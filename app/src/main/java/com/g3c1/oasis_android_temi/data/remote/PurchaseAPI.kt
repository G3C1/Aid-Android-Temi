package com.g3c1.oasis_android_temi.data.remote

import com.g3c1.oasis_android_temi.dto.purchase.OrderInfo
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface PurchaseAPI {
    @GET("purchase/")
    suspend fun getOrderList(): Response<List<OrderInfo>>

    @DELETE("purchase/{seatId}")
    suspend fun moveTemi(@Path("seatId") seatId: Long): Response<Void>
}