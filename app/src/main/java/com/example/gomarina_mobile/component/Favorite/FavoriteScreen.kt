package com.example.gomarina_mobile.component.Favorite

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.component.Beranda.BerandaContent
import com.example.gomarina_mobile.component.Beranda.TopBar
import com.example.gomarina_mobile.component.BottomNavigationBar
import com.example.gomarina_mobile.dummyData.DummyData
import com.example.gomarina_mobile.model.Produk
import com.example.gomarina_mobile.ui.theme.bacground
import java.math.BigDecimal

@Composable
fun FavoriteScreen(
    navController: NavHostController,
    produk: List<Produk> = emptyList()
) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    val userId = sharedPreferences.getInt("id", 0)
    val userUsername = sharedPreferences.getString("username", "Pengguna")
    val userRole = sharedPreferences.getString("role", "Tidak Ada")

    Log.d("UserData", "ID: $userId, Username: $userUsername, Role: $userRole")

    val productsFavState = remember { mutableStateOf(produk) }
    val filteredProducts = remember { mutableStateOf(produk) } // untuk menyimpan produk yang sudah difilter
    val isLoading = remember { mutableStateOf(true) }
    val errorMessage = remember { mutableStateOf<String?>(null) }
    var searchText by remember { mutableStateOf("") } // untuk menyimpan teks pencarian

    fetchFavoriteProducts { products, error ->
        if (products != null) {
            productsFavState.value = products
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
            productsFavState.value
        } else {
            productsFavState.value.filter {
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
            TopFavorite(navController = navController, onSearch = { query ->
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

@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview() {
    val navController = rememberNavController()

    // Data mock sederhana untuk produk
    val produkList = listOf(
        Produk(id = 1, name = "Produk 1", price = BigDecimal(32000), description = "dua", image = 0, imageUrl = "http://localhost:5000/product/1733978839250.png", stok = 3, isFavorite = false),
        Produk(id = 2, name = "Produk 2", price = BigDecimal(32000), description = "dua", image = 0, imageUrl = "http://localhost:5000/product/1733978839250.png", stok = 3, isFavorite = false),
        Produk(id = 3, name = "Produk 3", price = BigDecimal(32000), description = "dua", image = 0, imageUrl = "http://localhost:5000/product/1733978839250.png", stok = 3, isFavorite = false),
        Produk(id = 4, name = "Produk 4", price = BigDecimal(32000), description = "dua", image = 0, imageUrl = "http://localhost:5000/product/1733978839250.png", stok = 3, isFavorite = false)
    )

    FavoriteScreen(
        navController = navController,
        produk = produkList
    )
}