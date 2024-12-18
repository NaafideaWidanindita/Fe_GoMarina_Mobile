package com.example.gomarina_mobile.component.Keranjang

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.example.gomarina_mobile.model.KeranjangItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.math.BigDecimal

@Composable
fun KeranjangScreen(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    val userId = sharedPreferences.getInt("id", 0) // Ambil userId dari SharedPreferences

    var keranjangItems by remember { mutableStateOf<List<KeranjangItem>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Fetch data keranjang dari API
    LaunchedEffect(userId) {
        if (userId == 0) {
            errorMessage = "Anda belum login"
            isLoading = false
            return@LaunchedEffect
        }

        val url = "http://10.0.2.2:5000/api/v1/cart_byrole/$userId"
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        withContext(Dispatchers.IO) {
            try {
                val response = client.newCall(request).execute()
                val responseData = response.body?.string() ?: ""

                if (response.isSuccessful) {
                    val jsonObject = JSONObject(responseData)

                    if (jsonObject.has("data")) {
                        val jsonArray = jsonObject.getJSONArray("data")
                        val items = mutableListOf<KeranjangItem>()

                        for (i in 0 until jsonArray.length()) {
                            val itemObject = jsonArray.getJSONObject(i)
                            val keranjangItem = KeranjangItem(
                                id = itemObject.getInt("id"),
                                name = itemObject.getString("name"),
                                imageUrl = itemObject.optString("imageUrl", ""),
                                price = itemObject.optString("price", "0").toBigDecimalOrNull() ?: BigDecimal.ZERO,
                                image = 0,
                                jumlah = itemObject.optInt("jumlah", 1),
                                isChecked = false // Default unchecked
                            )
                            items.add(keranjangItem)
                        }
                        keranjangItems = items
                        isLoading = false
                    } else {
                        errorMessage = "'data' key tidak ditemukan di respons API"
                        isLoading = false
                    }
                } else {
                    errorMessage = "Gagal memuat data: ${response.message}"
                    isLoading = false
                }
            } catch (e: Exception) {
                errorMessage = "Terjadi kesalahan: ${e.message}"
                isLoading = false
            }
        }
    }

    // Tampilan UI
    Column {
        KeranjangHeader(navController)

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (keranjangItems.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Keranjang Kosong",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        } else {
            KeranjangContent(navController = navController, items = keranjangItems)
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun KeranjangScreenPreview() {
//    val navController = rememberNavController()
//    KeranjangScreen(navController = navController)
//}
