package com.example.gomarina_mobile

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.component.PesananContent
import com.example.gomarina_mobile.component.PesananHeader
import com.example.gomarina_mobile.dummyData.DummyData

@Composable
fun PesananScreen(navController: NavHostController) {
    val produk = DummyData.dataProduk[0] // Ambil produk pertama
    Column {
        PesananHeader(navController) // Header berada di atas
        PesananContent(navController) // Content di bawah Header
    }
}

@Preview(showBackground = true)
@Composable
private fun PesananScreenPreview() {
    val navController = rememberNavController()
    PesananScreen(navController = navController)
}
