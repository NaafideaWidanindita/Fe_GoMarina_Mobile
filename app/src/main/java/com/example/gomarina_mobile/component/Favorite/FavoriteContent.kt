package com.example.gomarina_mobile.component.Favorite

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gomarina_mobile.R
import com.example.gomarina_mobile.model.Produk
import com.example.gomarina_mobile.ui.theme.poppinsFamily
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.text.NumberFormat
import java.util.Locale
import java.math.BigDecimal
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.withContext
import okhttp3.FormBody

@Composable
fun FavoriteContent (
    modifier: Modifier = Modifier,
    produk: Produk,
    onItemClick:(Int) -> Unit,

    ) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    val userId = sharedPreferences.getInt("id", 0)
    val formattedPrice = NumberFormat.getNumberInstance(Locale("id", "ID")).format(produk.price)

    // Status Favorite diambil sesuai yg tersimpan
    var isFavorite by remember { mutableStateOf(produk.isFavorite) }

    val imageRequest  = if (produk.imageUrl.isNotEmpty()) {
        ImageRequest.Builder(LocalContext.current)
            .data(produk.imageUrl.replace("localhost", "10.0.2.2"))
            .crossfade(true)
            .build()
    } else {
        ImageRequest.Builder(LocalContext.current)
            .data(R.drawable.noimage)
            .crossfade(true)
            .build()
    }

    // Menambah/update favorite
    fun toggleFavorite() {
        if (userId == 0) {
            Toast.makeText(context, "Anda belum login", Toast.LENGTH_SHORT).show()
            return
        }

        val url = "http://10.0.2.2:5000/api/v1/fav/$userId/${produk.id}"
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .post(FormBody.Builder().build())
            .build()

        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            try {
                val response = client.newCall(request).execute()
                val responseData = response.body?.string() ?: ""
                Log.d("RESPON", "Response: $response,$responseData")

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val jsonObject = JSONObject(responseData)

                        if (jsonObject.has("message")) {
                            val message = jsonObject.getString("message")
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

                            isFavorite = !isFavorite
                        }
                    } else {
                        Toast.makeText(context, "Gagal memperbarui favorit", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Terjadi kesalahan: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Surface(
        modifier = modifier
            .padding(16.dp)
            .height(220.dp)
            .clickable {
                Log.d("BerandaContent", "Produk Diklik: ${produk.id}")
                onItemClick(produk.id) },
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 4.dp,
        color = Color.White
    ) {
        Column(
            Modifier.padding(10.dp)
        ) {
            AsyncImage(
                model = imageRequest,
                contentDescription = null,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .height(140.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.noimage),
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = produk.name,
                        fontFamily = poppinsFamily,
                        fontSize = 13.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Rp$formattedPrice/kg",
                        fontFamily = poppinsFamily,
                        fontSize = 12.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium
                    )
                }
                // Tombol Favorite
                IconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = { toggleFavorite() }
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) Color.Red else Color.Gray
                    )
                }
            }
        }
    }
}
@Composable
@SuppressLint("CoroutineCreationDuringComposition")
fun fetchFavoriteProducts(onResult: (List<Produk>?, String) -> Unit) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    val userId = sharedPreferences.getInt("id", 0)
    Log.d("USERID", "$userId")
    val url = "http://10.0.2.2:5000/api/v1/products?role_id=$userId" // Menambahkan role_id ke URL

    val client = OkHttpClient()
    val request = Request.Builder()
        .url(url)
        .build()

    val scope = CoroutineScope(Dispatchers.IO)
    scope.launch {
        try {
            val response = client.newCall(request).execute()
            val responseData = response.body?.string() ?: ""

            Log.d("fetchProducts", "Respons API: $responseData")

            if (response.isSuccessful) {
                val jsonObject = JSONObject(responseData)

                if (jsonObject.has("data")) {
                    val jsonArray = jsonObject.getJSONArray("data")
                    val products = mutableListOf<Produk>()

                    for (i in 0 until jsonArray.length()) {
                        val productObject = jsonArray.getJSONObject(i)
                        val product = Produk(
                            id = productObject.getInt("id"),
                            name = productObject.getString("name"),
                            image = productObject.optString("image", "0").toIntOrNull() ?: 0,
                            imageUrl = productObject.getString("imageUrl"),
                            description = productObject.getString("description"),
                            stok = productObject.optInt("stok", 0),
                            price = productObject.optString("price", "0").toBigDecimalOrNull() ?: BigDecimal.ZERO,
                            isFavorite = productObject.optBoolean("isFavorite")
                        )

                        // Filter produk yang hanya isFavorite = true
                        if (product.isFavorite) {
                            products.add(product)
                        }
                    }
                    onResult(products, "Success")
                } else {
                    Log.e("fetchProducts", "'products' key tidak ditemukan")
                    onResult(null, "Error: 'products' key not found in JSON response")
                }
            } else {
                Log.e("fetchProducts", "Respons gagal: ${response.message}")
                onResult(null, "Error: ${response.message}")
            }
        } catch (e: Exception) {
            Log.e("fetchProducts", "Request gagal: ${e.message}")
            onResult(null, "Request Failed: ${e.message}")
        }
    }
}

@Preview
@Composable
private fun BerandaContentPrev() {
    FavoriteContent(
        produk = Produk(1,"Jambu",0,"drawable.","Buah Jambu Yang Manis", BigDecimal("32000"),100),
        onItemClick ={produkId ->
            println("Produk id: $produkId")
        }
    )
}