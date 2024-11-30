package com.example.gomarina_mobile.component.Cek

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
@Composable
fun CekScreen(navController: NavHostController) {
    Column {
        CekHeader(navController)
        CekPesananScreen(navController = navController) // Menggunakan navController yang sudah dideklarasikan
    }
}

@Preview(showBackground = true)
@Composable
private fun CekScreenPreview() {
    val navController = rememberNavController() // Menginisialisasi navController
    CekScreen(navController = navController)
}
