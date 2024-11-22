package com.example.gomarina_mobile.model

import java.math.BigInteger;

enum class PaymentOption {
    qris, mandiri
}

enum class PaymentStatus {
    pending, success, failed
}

data class Payment(
        val id: Int,
        val order_id:BigInteger,
        val image: String,
        val option: PaymentOption,
        val status: PaymentStatus
)
