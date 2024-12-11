package com.example.gomarina_mobile.component.Product

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.gomarina_mobile.R
import com.example.gomarina_mobile.model.Produk
import com.example.gomarina_mobile.ui.theme.bacground
import com.example.gomarina_mobile.ui.theme.button
import com.example.gomarina_mobile.ui.theme.poppinsFamily
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.math.BigDecimal

@Composable
fun ProdukContent(navController: NavHostController, productId: Int) {
    var produk by remember { mutableStateOf<Produk?>(null) }
    val errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(productId) {
        ProductDetail(id = productId) { result ->
            produk = result
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(bacground)) {
        when {
            errorMessage != null -> {
                Text(
                    text = errorMessage!!,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center),
                    fontWeight = FontWeight.Bold
                )
            }

            produk != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(bottom = 80.dp)
                ) {
                    ProdukImage(imageURL = produk!!.imageUrl) // Menggunakan imageUrl dari API response
                    ProdukDetails(produk = produk!!)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp, horizontal = 20.dp)
                    ) {
                        var quantity by remember { mutableStateOf(1) }
                        ProdukJumlah(quantity = quantity) { quantity = it }
                        ProdukHarga(harga = produk!!.price, quantity = quantity)
                    }
                }

                // Tombol "Masukkan Keranjang" tetap di bagian bawah
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                ) {
                    AddKeranjangButton(navController = navController)
                }
            }
        }
    }
}

@Composable
fun ProdukImage(imageURL: String) {
    val imageRequest = if (imageURL.isNotEmpty()) {
        ImageRequest.Builder(LocalContext.current)
            .data(imageURL.replace("localhost", "10.0.2.2"))
            .crossfade(true)
            .build()
    } else {
        ImageRequest.Builder(LocalContext.current)
            .data(R.drawable.noimage)
            .crossfade(true)
            .build()
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp))
    ) {
        AsyncImage(
            model = imageRequest,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}

@Composable
fun ProdukDetails(produk: Produk) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Text(
            text = produk.name,
            fontSize = 24.sp,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Bold
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            repeat(5) { index ->
                val color = if (index < 3.5) Color(0xFFFFD700) else Color.Gray
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Rating",
                    tint = color,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        Text(
            text = "Deskripsi Produk",
            fontSize = 20.sp,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
            text = produk.description,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = poppinsFamily
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Stok: ${produk.stok}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
            
        }
}

@Composable
fun ProdukHarga(harga: BigDecimal, quantity: Int) {
    val totalHarga = harga * quantity.toBigDecimal()
    Text(
        text = "Rp${totalHarga.toPlainString()}",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = poppinsFamily
    )
}

@Composable
fun ProdukJumlah(quantity: Int, onQuantityChange: (Int) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(
            onClick = { if (quantity > 1) onQuantityChange(quantity - 1) }
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(Color.White, shape = CircleShape)
                    .border(2.dp, button, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "-", fontSize = 30.sp, color = Color.Black)
            }
        }
        Text(
            text = "${quantity}kg",
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 5.dp)
        )
        IconButton(
            onClick = { onQuantityChange(quantity + 1) }
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(button, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "+", fontSize = 30.sp, color = Color.White)
            }
        }
    }
}

@Composable
fun AddKeranjangButton(navController: NavHostController) {
    Button(
        onClick = { navController.navigate("Keranjang") },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(containerColor = button)
    ) {
        Text(
            text = "Masukkan Keranjang",
            color = Color.White,
            fontFamily = poppinsFamily,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

fun ProductDetail(
    id: Int,
    onResult: (Produk) -> Unit
) {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("http://10.0.2.2:5000/api/v1/products/$id") // Pastikan ID dikirim dengan benar di URL
        .build()
    Log.d("ID","$id")
    val scope = CoroutineScope(Dispatchers.IO)
    scope.launch {
        try {
            val response = client.newCall(request).execute()
            val responseData = response.body?.string() ?: ""

            if (response.isSuccessful) {
                val jsonObject = JSONObject(responseData)
                val productObject = jsonObject.getJSONObject("data")
                val product = Produk(
                    id = productObject.getInt("id"),
                    name = productObject.getString("name"),
                    image = productObject.optString("image", "0").toIntOrNull() ?:0,
                    imageUrl = productObject.optString("imageUrl"),
                    description = productObject.getString("description"),
                    stok = productObject.optInt("stok", 0),
                    price = productObject.optString("price", "0").toBigDecimalOrNull() ?: BigDecimal.ZERO
                )
                Log.d("PRODUKDETAIL","$product")
                Log.d("ProdukImage", "imageURL: ${product.imageUrl}")
                onResult(product) // Mengirimkan product dan imageUrl ke hasil callback
            } else {
                Log.d("EROR","${response.message}")
            }

        } catch (e: Exception) {
            Log.d("REQUEST FAILED","${e.message}")
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ProdukContentPreview() {
//    val navController = rememberNavController()
//    val produk = DummyData.dataProduk[2]
//    ProdukContent(navController = navController, productId = produk)
//}
