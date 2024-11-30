package com.example.gomarina_mobile.component.Beranda

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gomarina_mobile.dummyData.DummyData
import com.example.gomarina_mobile.model.Produk
import com.example.gomarina_mobile.ui.theme.bacground

@Composable
fun Beranda (
    navController: NavController,
    produk: List<Produk> = DummyData.dataProduk
) {
    Column(
        modifier = Modifier
            .background(bacground)
    ) {
        TopBar()

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
        ) {
            items(produk, key = { it.id }) {
                BerandaContent(produk = it) { produkId ->
                    navController.navigate("detail/$produkId")
                }
            }
        }
    }
}
@Preview (showBackground = true)
@Composable
private fun BerandaPrev() {
    Beranda(navController = NavController(LocalContext.current),
        produk = DummyData.dataProduk)
}