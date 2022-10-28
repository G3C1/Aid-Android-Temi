package com.g3c1.oasis_android_temi.domain.usecase.purchase

import com.g3c1.oasis_android_temi.domain.repository.PurchaseRepository
import javax.inject.Inject

class GetOrderListUseCase @Inject constructor(
    private val repository: PurchaseRepository
) {
    suspend fun getOrderList() = repository.getOrderList()
}