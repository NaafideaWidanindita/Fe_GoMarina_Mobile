package com.example.gomarina_mobile.component.Pembayaran

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.dummyData.DummyData

@Composable
fun PembayaranScreen(navController: NavHostController) {
    val keranjangItems = DummyData.dataKeranjangItems
    Column {
        PembayaranHeader(navController)
        PembayaranContent()
    }
}

@Preview(showBackground = true)
@Composable
private fun PembayaranBerhasilScreenPreview() {
    val navController = rememberNavController()
    PembayaranScreen(navController)
}