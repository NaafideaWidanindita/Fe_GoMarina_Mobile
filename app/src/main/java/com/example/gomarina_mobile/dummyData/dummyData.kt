package com.example.gomarina_mobile.dummyData

import com.example.gomarina_mobile.R
import com.example.gomarina_mobile.model.Produk

import java.math.BigDecimal

object dummyData {
    val dataProduk = listOf(
        Produk(
            id = 1,
            name = "Jambu",
            image = R.drawable.jambu,
            description = "Buah Jambu yang manis",
            price = BigDecimal("32000"),
            stok = 100
        )
    )
}