package com.example.gomarina_mobile.component.Product

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.dummyData.DummyData
import com.example.gomarina_mobile.model.Produk
import com.example.gomarina_mobile.ui.theme.bacground
import com.example.gomarina_mobile.ui.theme.button
import com.example.gomarina_mobile.ui.theme.poppinsFamily
import java.math.BigDecimal

@Composable
fun ProdukContent(navController: NavHostController, produk: Produk) {
    var quantity by remember { mutableStateOf(1) } // quantity yang dapat diubah

    Box(modifier = Modifier
        .fillMaxSize()
        .background(bacground)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 80.dp,)
        ) {
            ProdukImage(imageRes = produk.image)
            ProdukDetails(produk)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 20.dp)
            ) {
                ProdukJumlah(quantity) { quantity = it } // Mengubah jumlah
                ProdukHarga(harga = produk.price, quantity = quantity) // Mengupdate harga
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


@Composable
fun ProdukImage(imageRes: Int) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(6/5f)
            .padding(vertical = 10.dp)
    )
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
        onClick = { navController.navigate("com/example/gomarina_mobile/component/Keranjang") },
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

@Preview(showBackground = true)
@Composable
fun ProdukContentPreview() {
    val navController = rememberNavController()
    val produk = DummyData.dataProduk[0]
    ProdukContent(navController = navController, produk = produk)
}
