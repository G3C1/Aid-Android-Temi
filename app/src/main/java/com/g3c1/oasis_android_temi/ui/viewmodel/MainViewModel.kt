package com.g3c1.oasis_android_temi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.g3c1.oasis_android_temi.data.remote.util.ApiState
import com.g3c1.oasis_android_temi.domain.usecase.purchase.GetOrderListUseCase
import com.g3c1.oasis_android_temi.domain.usecase.purchase.MoveTemiUseCase
import com.g3c1.oasis_android_temi.dto.purchase.OrderInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getOrderListUseCase: GetOrderListUseCase,
    private val moveTemiUseCase: MoveTemiUseCase
) : ViewModel() {
    val mOrderDataList: MutableStateFlow<ApiState<List<OrderInfo>>> =
        MutableStateFlow(ApiState.Loading())

    val mMoveTemi: MutableStateFlow<ApiState<Void>> =
        MutableStateFlow(ApiState.Loading())

    fun getOrderList() = viewModelScope.launch {
        mOrderDataList.value = ApiState.Loading()
        getOrderListUseCase.getOrderList().catch { error ->
            mOrderDataList.value = ApiState.Error(error.message.toString())
        }.collect { value ->
            mOrderDataList.value = value
        }
    }

    fun moveTemi(seatId: Long) = viewModelScope.launch {
        mMoveTemi.value = ApiState.Loading()
        moveTemiUseCase.moveTemi(seatId = seatId).catch { error ->
            mMoveTemi.value = ApiState.Error(error.message.toString())
        }.collect { value ->
            mMoveTemi.value = value
        }
    }
}