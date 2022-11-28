package com.g3c1.oasis_android_temi.data.remote

import com.g3c1.oasis_android_temi.dto.purchase.OrderInfoDTO
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface PurchaseAPI {
    @GET("v2/purchase/find/{serialNumber}")
    suspend fun getOrderList(): Response<List<OrderInfoDTO>>

    @DELETE("purchase/{seatId}")
    suspend fun moveTemi(@Path("seatId") seatId: Long): Response<Void>
}