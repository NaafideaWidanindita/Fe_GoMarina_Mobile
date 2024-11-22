package com.example.gomarina_mobile.model

import java.math.BigInteger

data class Feedback(
    val id: BigInteger,
    val role_id: BigInteger,
    val star: Int,
    val kritik_saran: String
)