package com.example.gomarina_mobile.model

import java.math.BigInteger

data class Address(
    val id: Int,
    val role_id: BigInteger,
    val provinsi: String,
    val city: String,
    val kecamatan: String,
    val kode_pos: Int,
    val street: String,
    val detail:String
)