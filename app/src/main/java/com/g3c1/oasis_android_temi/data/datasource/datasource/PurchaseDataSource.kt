package com.g3c1.oasis_android_temi.data.datasource.datasource

import com.g3c1.oasis_android_temi.data.remote.util.ApiState
import com.g3c1.oasis_android_temi.dto.purchase.OrderInfo
import kotlinx.coroutines.flow.Flow

interface PurchaseDataSource {
    suspend fun getOrderList() : Flow<ApiState<List<OrderInfo>>>
}