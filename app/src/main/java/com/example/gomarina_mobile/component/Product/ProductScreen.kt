package com.example.gomarina_mobile.component.Product

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.dummyData.DummyData

@Composable
fun ProductScreen(navController: NavHostController, produkId: Int) {
    val produk = DummyData.dataProduk.find { it.id == produkId }
    if (produk != null) {
        Column {
            ProdukHeader(navController)
            ProdukContent(navController, produk = produk)
        }
    } else {
        // produk tidak ditemukan
        Column {
            ProdukHeader(navController)
            Text("Produk tidak ditemukan.")
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ProductScreenPreview() {
    val navController = rememberNavController()
    ProductScreen(navController = navController, produkId = 1)
}