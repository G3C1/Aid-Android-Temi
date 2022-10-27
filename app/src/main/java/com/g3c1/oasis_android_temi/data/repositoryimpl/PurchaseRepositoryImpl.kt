package com.g3c1.oasis_android_temi.data.repositoryimpl

import com.g3c1.oasis_android_temi.data.datasource.datasource.PurchaseDataSource
import com.g3c1.oasis_android_temi.data.remote.util.ApiState
import com.g3c1.oasis_android_temi.domain.repository.PurchaseRepository
import com.g3c1.oasis_android_temi.dto.OrderInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PurchaseRepositoryImpl @Inject constructor(
    private val dataSource: PurchaseDataSource
) : PurchaseRepository {
    override suspend fun getOrderList(): Flow<ApiState<List<OrderInfo>>> {
        return dataSource.getOrderList()
    }
}