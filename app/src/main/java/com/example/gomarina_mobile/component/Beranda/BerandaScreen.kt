package com.example.gomarina_mobile.component.Beranda

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gomarina_mobile.component.BottomNavigationBar
import com.example.gomarina_mobile.model.Produk
import com.example.gomarina_mobile.ui.theme.bacground

@Composable
fun Beranda(
    navController: NavHostController,
    produk: List<Produk> = emptyList()
) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    val userId = sharedPreferences.getInt("id", 0)
    val userUsername = sharedPreferences.getString("username", "Pengguna")
    val userRole = sharedPreferences.getString("role", "Tidak Ada")

    Log.d("UserData", "ID: $userId, Username: $userUsername, Role: $userRole")

    val productsState = remember { mutableStateOf(produk) }
    val filteredProducts = remember { mutableStateOf(produk) } // untuk menyimpan produk yang sudah difilter
    val isLoading = remember { mutableStateOf(true) }
    val errorMessage = remember { mutableStateOf<String?>(null) }
    var searchText by remember { mutableStateOf("") } // untuk menyimpan teks pencarian

    fetchProducts { products, error ->
        if (products != null) {
            productsState.value = products
            filteredProducts.value = products // menampilkan semua produk saat pertama kali
            isLoading.value = false
            Log.d("Beranda", "Produk berhasil diambil: $products")
        } else {
            errorMessage.value = error
            isLoading.value = false
            Log.e("Beranda", "Gagal mengambil produk: $error")
        }
    }

    // Filter berdasar pencarian
    val filterProducts = { query: String ->
        filteredProducts.value = if (query.isEmpty()) {
            productsState.value
        } else {
            productsState.value.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(bacground)
        ) {
            TopBar(navController = navController, onSearch = { query ->
                searchText = query
                filterProducts(query) // Menjalankan fungsi filter ketika ada perubahan input
            })

            // Display loading, error, or products
            when {
                isLoading.value -> {
                    Text("Loading...", modifier = Modifier.padding(16.dp))
                }
                errorMessage.value != null -> {
                    Text("Error: ${errorMessage.value}", modifier = Modifier.padding(16.dp))
                }
                else -> {
                    if (filteredProducts.value.isEmpty()) {
                        Text(
                            "Produk tidak ditemukan",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                    } else {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(bottom = 0.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(filteredProducts.value) { product ->
                                BerandaContent(
                                    produk = product,
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



