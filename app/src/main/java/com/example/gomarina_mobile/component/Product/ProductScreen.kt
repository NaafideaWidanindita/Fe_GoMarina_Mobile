package com.example.gomarina_mobile.component.Product

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun ProductScreen(navController: NavHostController, produkId: Int) {
    Log.d("DATA","$produkId")
    if (produkId != null) {
        Column {
            ProdukHeader(navController)
            ProdukContent(navController, produkId)
        }
    } else {
        // produk tidak ditemukan
        Column {
            ProdukHeader(navController)
            Text("Produk tidak ditemukan.")
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//private fun ProductScreenPreview() {
//    val navController = rememberNavController()
//    ProductScreen(navController = navController, produkId = 1)
//}
