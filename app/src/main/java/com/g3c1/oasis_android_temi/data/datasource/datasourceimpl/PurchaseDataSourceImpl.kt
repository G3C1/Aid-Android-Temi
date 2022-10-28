package com.g3c1.oasis_android_temi.data.datasource.datasourceimpl

import com.g3c1.oasis_android_temi.data.datasource.datasource.PurchaseDataSource
import com.g3c1.oasis_android_temi.data.remote.PurchaseAPI
import com.g3c1.oasis_android_temi.data.remote.util.ApiState
import com.g3c1.oasis_android_temi.dto.purchase.OrderInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class PurchaseDataSourceImpl @Inject constructor(
    private val api: PurchaseAPI
) : PurchaseDataSource {
    override suspend fun getOrderList(): Flow<ApiState<List<OrderInfo>>> {
        return flow {
            try {
                val response = api.getOrderList()
                if (response.isSuccessful) {
                    response.body()?.run {
                        emit(ApiState.Success(this))
                    }
                } else {
                    try {
                        emit(ApiState.Error(response.errorBody()!!.string()))
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}