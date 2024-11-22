package com.example.gomarina_mobile.model

import java.math.BigInteger;

data class OrderItem(
    val id: Int,
    val order_id:BigInteger,
    val cart_item_id:BigInteger,
    val jumlah_item:Int,
)