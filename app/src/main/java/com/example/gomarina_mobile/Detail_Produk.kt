package com.example.gomarina_mobile

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.component.Detail_ProdukContent
import com.example.gomarina_mobile.model.Produk

@Composable
fun Detail_Product(navController: NavHostController) {
    val produk = com.example.gomarina_mobile.dummyData.dummyData.dataProduk.first() // Ambil produk pertama
    Column {
        Detail_ProdukContent(navController, produk = produk)
    }
}

@Preview(showBackground = true)
@Composable
private fun Detail_ProductPreview() {
    val navController = rememberNavController()
    Detail_Product(navController = navController)
}
