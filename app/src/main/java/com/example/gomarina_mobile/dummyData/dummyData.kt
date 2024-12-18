package com.example.gomarina_mobile.dummyData

import com.example.gomarina_mobile.R
import com.example.gomarina_mobile.model.Produk
import com.example.gomarina_mobile.model.Address
import com.example.gomarina_mobile.model.Delivery
import com.example.gomarina_mobile.model.DeliveryOption
import com.example.gomarina_mobile.model.DeliveryStatus
import com.example.gomarina_mobile.model.KeranjangItem
import java.math.BigDecimal
import java.math.BigInteger

object DummyData {
//    val dataProduk = listOf(
//        Produk(
//            id = 1,
//            name = "Jambu",
//            description = "Deskripsi produk A yang sangat menarik.",
//            price = BigDecimal(32000),
//            stok = 100,
//            image = R.drawable.jambu
//        ),
//        Produk(
//            id = 2,
//            name = "Selai Jambu",
//            image = R.drawable.selaijambu,
//            description = "Selai Jambu Cocok Untuk Sarapan Pagi Dengan Roti",
//            price = BigDecimal("25000"),
//            stok = 100
//        ),
//        Produk(
//            id = 3,
//            name = "Eskrim Jambu",
//            image = R.drawable.eskrimjambu,
//            description = "Eskrim Jambu Bikin Tenggorokan Adem ",
//            price = BigDecimal("20000"),
//            stok = 100
//        ),
//        Produk(
//            id = 4,
//            name = "Eskrim Jambu",
//            image = R.drawable.eskrimjambu,
//            description = "Eskrim Jambu Bikin Tenggorokan Adem ",
//            price = BigDecimal("10000"),
//            stok = 100
//        ),
//        Produk(
//            id = 5,
//            name = "Jambu",
//            description = "Deskripsi produk A yang sangat menarik.",
//            price = BigDecimal(50000),
//            stok = 100,
//            image = R.drawable.jambu
//        ),
//    )

    val dataKeranjangItems = listOf(
        KeranjangItem(id = 1, name = "Jambu", price = BigDecimal(32000), image = R.drawable.jambu, imageUrl = "jambu"),
        KeranjangItem(id = 2, name = "Selai Jambu", price = BigDecimal(25000), image = R.drawable.selaijambu, imageUrl = "Selai Jambu"),
        KeranjangItem(id = 3, name = "Eskrim Jambu", price = BigDecimal(10000), image = R.drawable.eskrimjambu, imageUrl = "Eskrim Jambu"),
        KeranjangItem(id = 4, name = "Rambutan", price = BigDecimal(32000), image = R.drawable.jambu, imageUrl = "jambu"),
        KeranjangItem(id = 5, name = "Angga", price = BigDecimal(25000), image = R.drawable.jambu, imageUrl = "Selai Jambu"),
        KeranjangItem(id = 6, name = "Rina", price = BigDecimal(15000), image = R.drawable.jambu, imageUrl = "Eskrim Jambu"),
        KeranjangItem(id = 7, name = "Meli", price = BigDecimal(15000), image = R.drawable.jambu, imageUrl = "jambu"),
        KeranjangItem(id = 8, name = "Aku", price = BigDecimal(32000), image = R.drawable.jambu, imageUrl = "Selai Jambu"),
        KeranjangItem(id = 9, name = "Ara", price = BigDecimal(25000), image = R.drawable.jambu, imageUrl = "Eskrim Jambu"),
        KeranjangItem(id = 10, name = "Mika", price = BigDecimal(15000), image = R.drawable.jambu, imageUrl = "jambu")
        // Tambahkan item lain sesuai kebutuhan
    )

    val dataPesanan = listOf(
        KeranjangItem(id = 1, name = "Jambu", jumlah = 1, price = BigDecimal(32000) , image = R.drawable.jambu, imageUrl = "jambu"),
        KeranjangItem(id = 2, name = "Selai Jambu", jumlah = 2,price = BigDecimal(25000), image = R.drawable.selaijambu, imageUrl = "Selai Jambu"),
        KeranjangItem(id = 3, name = "Eskrim Jambu", jumlah = 7,price = BigDecimal(10000), image = R.drawable.eskrimjambu, imageUrl = "Eskrim Jambu")
    )

    val dataAddress = listOf(
        Address(
            provinsi = "Kepulauan Riau",
            city = "Batam",
            kecamatan = "Batu Aji",
            postalCode = 29424,
            street = "Jl. Letjend Suprapto No. 45",
            detail = "Rumah warna biru dekat pasar"
        )
    )

    // Data dummy untuk pengiriman
    val dataDelivery = listOf(
        Delivery(
            id = 1,
            order_id = BigInteger("10001"),
            option = DeliveryOption.jnt,  // Memilih pengiriman JNT
            ongkir = 10000,
            status = DeliveryStatus.dikirim  // Status pengiriman adalah dikirim
        ),
        Delivery(
            id = 2,
            order_id = BigInteger("10002"),
            option = DeliveryOption.jne,  // Memilih pengiriman JNE
            ongkir = 20000,
            status = DeliveryStatus.dikirim  // Status pengiriman adalah dikemas
        ),
        Delivery(
            id = 3,
            order_id = BigInteger("10003"),
            option = DeliveryOption.langsung,
            ongkir = 25000,
            status = DeliveryStatus.dikirim
        )
        )
}