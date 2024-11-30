package com.example.gomarina_mobile.component.Keranjang

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.dummyData.DummyData

@Composable
fun KeranjangScreen(navController: NavHostController) {
    val keranjangItems = DummyData.dataKeranjangItems
    Column {
        KeranjangHeader(navController)
        KeranjangContent(navController = navController, items = keranjangItems)
    }
}

@Preview(showBackground = true)
@Composable
private fun KeranjangScreenPreview() {
    val navController = rememberNavController()
    KeranjangScreen(navController = navController)
}
