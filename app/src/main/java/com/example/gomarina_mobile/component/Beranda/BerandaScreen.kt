package com.example.gomarina_mobile.component.Beranda

import android.content.Context
import android.util.Log

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gomarina_mobile.component.BottomNavigationBar
import com.example.gomarina_mobile.dummyData.DummyData
import com.example.gomarina_mobile.model.Produk

@Composable
fun Beranda(
    navController: NavController,
    produk: List<Produk> = emptyList()
) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    val userId = sharedPreferences.getInt("id", 0)
    val userUsername = sharedPreferences.getString("username", "Pengguna")
    val userRole = sharedPreferences.getString("role", "Tidak Ada")

    Log.d("UserData", "ID: $userId, Username: $userUsername, Role: $userRole")

    val productsState = remember { mutableStateOf(produk) }
    val isLoading = remember { mutableStateOf(true) }
    val errorMessage = remember { mutableStateOf<String?>(null) }

    fetchProducts { products, error ->
        if (products != null) {
            productsState.value = products
            isLoading.value = false
            Log.d("Beranda", "Produk berhasil diambil: $products")
        } else {
            errorMessage.value = error
            isLoading.value = false
            Log.e("Beranda", "Gagal mengambil produk: $error")
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            TopBar(navController = navController)

            // Display loading, error, or products
            when {
                isLoading.value -> {
                    Text("Loading...", modifier = Modifier.padding(16.dp))
                }
                errorMessage.value != null -> {
                    Text("Error: ${errorMessage.value}", modifier = Modifier.padding(16.dp))
                }
                else -> {
                    if (productsState.value.isEmpty()) {
                        Text("Tidak ada produk tersedia.", modifier = Modifier.padding(16.dp))
                    } else {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(8.dp),
                        ) {
                            items(productsState.value, key = { it.id }) { produkItem ->
                                BerandaContent(
                                    produk = produkItem,
                                    onItemClick = { produkId ->
                                        Log.d("BerandaContent", "Produk diklik: $produkId")
                                        navController.navigate("Produk/$produkId") // Navigasi ke halaman produk
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


//@Preview (showBackground = true)
//@Composable
//private fun BerandaPrev() {
//    Beranda(navController = NavController(LocalContext.current),
//        produk = DummyData.dataProduk)
//}



