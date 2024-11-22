package com.example.gomarina_mobile.model

import java.math.BigInteger;

data class CardItem(
    val id: Int,
    val role_id:BigInteger,
    val produk_id: String,
    val jumlah: Int,
    val price: Int
)