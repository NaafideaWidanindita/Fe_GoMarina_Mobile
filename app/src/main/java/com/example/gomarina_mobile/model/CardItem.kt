//package com.example.gomarina_mobile.model
//
//import java.math.BigInteger;
//
//data class CardItem(
//    val id: Int,
//    val role_id:BigInteger,
//    val produk_id: String,
//    val jumlah: Int,
//    val price: Int
//)

package com.example.gomarina_mobile.model

data class KeranjangItem(
    val id: Int,
    val name: String,
    val price: Int,
    val image: Int,
    var quantity: Int = 1,
    var isChecked: Boolean = false
)
