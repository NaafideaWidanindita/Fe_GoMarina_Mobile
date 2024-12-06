package com.example.gomarina_mobile.component.Product

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.dummyData.DummyData

@Composable
fun ProductScreen(navController: NavHostController, produkId: Int) {
    val produk = DummyData.dataProduk[0] // Ambil produk pertama
    Column {
        ProdukHeader(navController) // Header berada di atas
        ProdukContent(navController, produk = produk) // Content di bawah Header
    }
}


