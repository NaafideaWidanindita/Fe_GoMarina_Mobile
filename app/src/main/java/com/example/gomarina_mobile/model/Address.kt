package com.example.gomarina_mobile.model

import java.math.BigInteger

data class Address(
    val provinsi: String,
    val city: String,
    val kecamatan: String,
    val postalCode: Int,
    val street: String,
    val detail:String
)