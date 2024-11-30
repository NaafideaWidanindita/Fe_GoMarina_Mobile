package com.example.gomarina_mobile.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TotalPembayaranViewModel : ViewModel() {
    var totalALL by mutableStateOf(0f)
        private set

    fun updateTotal(total: Float) {
        totalALL = total
    }
}
