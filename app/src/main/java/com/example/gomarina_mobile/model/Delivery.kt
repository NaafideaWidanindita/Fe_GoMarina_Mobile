package com.example.gomarina_mobile.model

import java.math.BigInteger

enum class DeliveryOption {
    jnt, jne, langsung
}

enum class DeliveryStatus {
    dikemas, dikirim, sampai
}

data class Delivery (
    val id: Int,
    val order_id: BigInteger,
    val option: DeliveryOption,
    val ongkir : Int,
    val status: DeliveryStatus
)