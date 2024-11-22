package com.example.gomarina_mobile.model

import java.math.BigInteger;
import java.text.DecimalFormat

data class Order(
    val id: Int,
    val address_id:BigInteger,
    val role_id:BigInteger,
    val total:DecimalFormat,
    val notes:String
)