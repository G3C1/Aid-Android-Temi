package com.g3c1.oasis_android_temi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> get() = _state

    fun setValue(boolean: Boolean) {
        _state.value = boolean
    }
}