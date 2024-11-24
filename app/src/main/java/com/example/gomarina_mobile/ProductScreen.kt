package com.example.gomarina_mobile

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.component.ProdukContent
import com.example.gomarina_mobile.component.ProdukHeader
import com.example.gomarina_mobile.dummyData.DummyData

@Composable
fun ProductScreen(navController: NavHostController) {
    val produk = DummyData.dataProduk[0] // Ambil produk pertama
    Column {
        ProdukHeader(navController) // Header berada di atas
        ProdukContent(navController, produk = produk) // Content di bawah Header
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductScreenPreview() {
    val navController = rememberNavController()
    ProductScreen(navController = navController)
}
