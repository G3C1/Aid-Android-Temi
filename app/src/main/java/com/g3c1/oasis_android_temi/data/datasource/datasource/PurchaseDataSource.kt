package com.g3c1.oasis_android_temi.data.datasource.datasource

import com.g3c1.oasis_android_temi.data.remote.util.ApiState
import com.g3c1.oasis_android_temi.dto.purchase.OrderInfoDTO
import kotlinx.coroutines.flow.Flow

interface PurchaseDataSource {
    suspend fun getOrderList(): Flow<ApiState<List<OrderInfoDTO>>>

    suspend fun moveTemi(seatId: Long): Flow<ApiState<Void>>
}