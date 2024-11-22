package com.example.gomarina_mobile.model

import java.math.BigInteger

data class Review(
    val id: BigInteger,
    val role_id: BigInteger,
    val produk_id: String,
    val order_id: BigInteger,
    val star: Int,
    val review: String
)
