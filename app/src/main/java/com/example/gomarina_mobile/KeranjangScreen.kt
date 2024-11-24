package com.example.gomarina_mobile

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.component.KeranjangContent
import com.example.gomarina_mobile.component.KeranjangHeader
import com.example.gomarina_mobile.dummyData.DummyData

@Composable
fun KeranjangScreen(navController: NavHostController) {
    // Ambil data keranjang dari DummyData
    val keranjangItems = DummyData.dataKeranjangItems
    Column {
        // Header berada di atas
        KeranjangHeader(navController)

        // Content menerima data keranjang dari DummyData
        KeranjangContent(navController = navController, items = keranjangItems)
    }
}

@Preview(showBackground = true)
@Composable
private fun KeranjangScreenPreview() {
    val navController = rememberNavController()
    // Ambil data dummy untuk preview
    val dummyItems = DummyData.dataKeranjangItems
    KeranjangScreen(navController = navController)
}
