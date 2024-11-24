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
        ),
        Produk(
            id = 2,
            name = "Jus Jambu",
            image = R.drawable.jusjambu,
            description = "Jus Jambu Seger Bener",
            price = BigDecimal("15000"),
            stok = 100
        ),
        Produk(
            id = 3,
            name = "Selai Jambu",
            image = R.drawable.selaijambu,
            description = "Selai Jambu Cocok Untuk Sarapan Pagi Dengan Roti",
            price = BigDecimal("25000"),
            stok = 100
        ),
        Produk(
            id = 4,
            name = "Eskrim Jambu",
            image = R.drawable.eskrimjambu,
            description = "Eskrim Jambu Bikin Tenggorokan Adem ",
            price = BigDecimal("20000"),
            stok = 100
        ),
    )
}