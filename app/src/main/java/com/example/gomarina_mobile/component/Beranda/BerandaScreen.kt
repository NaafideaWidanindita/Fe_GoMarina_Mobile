package com.example.gomarina_mobile.component.Beranda

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gomarina_mobile.component.BottomNavigationBar
import com.example.gomarina_mobile.dummyData.DummyData
import com.example.gomarina_mobile.model.Produk
import com.example.gomarina_mobile.ui.theme.bacground

@Composable
fun Beranda(
    navController: NavController,
    produk: List<Produk> = DummyData.dataProduk
) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    val userId = sharedPreferences.getInt("id", 0)
    val userUsername = sharedPreferences.getString("username", "Pengguna")
    val userRole = sharedPreferences.getString("role", "Tidak Ada")

    Log.d("UserData", "ID: $userId, Username: $userUsername, Role: $userRole")

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(bacground)
                .padding(paddingValues)
        ) {
            TopBar(navController = navController)

            // Display user info
            Text(
                text = "Selamat datang, $userUsername!",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Text(
                text = "Role: $userRole",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
            ) {
                items(produk, key = { it.id }) { produkItem ->
                    BerandaContent(
                        produk = produkItem,
                        onItemClick = { produkId ->
                            navController.navigate("Produk/$produkId") // Navigasi ke halaman produk
                        }
                    )
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



