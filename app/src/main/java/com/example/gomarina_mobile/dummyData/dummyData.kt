package com.example.gomarina_mobile.dummyData

import com.example.gomarina_mobile.R
import com.example.gomarina_mobile.model.Produk
import com.example.gomarina_mobile.model.KeranjangItem
import com.example.gomarina_mobile.model.Address
import com.example.gomarina_mobile.model.Delivery
import com.example.gomarina_mobile.model.DeliveryOption
import com.example.gomarina_mobile.model.DeliveryStatus
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
        KeranjangItem(id = 1, name = "Jambu", price = 32000, image = R.drawable.jambu),
        KeranjangItem(id = 2, name = "Selai Jambu", price = 25000, image = R.drawable.selaijambu),
        KeranjangItem(id = 3, name = "Eskrim Jambu", price = 10000, image = R.drawable.eskrimjambu),
        KeranjangItem(id = 4, name = "Rambutan", price = 32000, image = R.drawable.jambu),
        KeranjangItem(id = 5, name = "Angga", price = 25000, image = R.drawable.jambu),
        KeranjangItem(id = 6, name = "Rina", price = 15000, image = R.drawable.jambu),
        KeranjangItem(id = 7, name = "Meli", price = 15000, image = R.drawable.jambu),
        KeranjangItem(id = 8, name = "Aku", price = 32000, image = R.drawable.jambu),
        KeranjangItem(id = 9, name = "Ara", price = 25000, image = R.drawable.jambu),
        KeranjangItem(id = 10, name = "Mika", price = 15000, image = R.drawable.jambu)
        // Tambahkan item lain sesuai kebutuhan
    )

    val dataPesanan = listOf(
        KeranjangItem(id = 1, name = "Jambu", quantity = 1, price = 32000, image = R.drawable.jambu),
        KeranjangItem(id = 2, name = "Selai Jambu", quantity = 2,price = 25000, image = R.drawable.selaijambu),
        KeranjangItem(id = 3, name = "Eskrim Jambu", quantity = 7,price = 10000, image = R.drawable.eskrimjambu),
    )

    val dataAddress = listOf(
        Address(
            id = 1,
            role_id = BigInteger.valueOf(1),
            provinsi = "Kepulauan Riau",
            city = "Batam",
            kecamatan = "Batu Aji",
            kode_pos = 29424,
            street = "Jl. Letjend Suprapto No. 45",
            detail = "Rumah warna biru dekat pasar"
        ),
        Address(
            id = 2,
            role_id = BigInteger.valueOf(2),
            provinsi = "Jawa Tengah",
            city = "Semarang",
            kecamatan = "Tembalang",
            kode_pos = 50275,
            street = "Jl. Soekarno Hatta No. 45",
            detail = "Gedung dengan pintu merah besar"
        ),
        Address(
            id = 3,
            role_id = BigInteger.valueOf(3),
            provinsi = "DKI Jakarta",
            city = "Jakarta Selatan",
            kecamatan = "Kebayoran Baru",
            kode_pos = 12110,
            street = "Jl. Sudirman No. 10",
            detail = "Lantai 2 gedung perkantoran"
        ),
        Address(
            id = 4,
            role_id = BigInteger.valueOf(1),
            provinsi = "Yogyakarta",
            city = "Sleman",
            kecamatan = "Depok",
            kode_pos = 55281,
            street = "Jl. Kaliurang KM 7",
            detail = "Kosan warna biru sebelah warung"
        ),
        Address(
            id = 5,
            role_id = BigInteger.valueOf(2),
            provinsi = "Bali",
            city = "Denpasar",
            kecamatan = "Kuta Selatan",
            kode_pos = 80361,
            street = "Jl. Sunset Road No. 88",
            detail = "Villa dengan kolam renang di halaman depan"
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