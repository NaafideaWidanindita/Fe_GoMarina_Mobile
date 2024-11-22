package com.example.gomarina_mobile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gomarina_mobile.ui.theme.bacground
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.R
import com.example.gomarina_mobile.model.Produk
import com.example.gomarina_mobile.ui.theme.button
import com.example.gomarina_mobile.ui.theme.poppinsFamily
import java.math.BigDecimal

@Composable
fun Detail_ProdukContent(navController: NavHostController, produk: Produk) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bacground)
    ) {
        ProdukHeader(navController)
        ProdukContent(produk)
        AddKeranjangButton(navController = navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProdukHeader(navController: NavHostController) {
    TopAppBar(
        title = { Text("Product", color = Color.Black) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = bacground
        ),
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.Black)
            }
        },
    )
}


@Composable
fun ProdukContent(produk: Produk) {
    var quantity by remember { mutableStateOf(1) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(start = 32.dp, top=0.dp, end = 32.dp)
    ) {
        ProdukImage(imageRes = produk.image)
        ProdukDetails(produk)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            ProdukJumlah(quantity) { quantity = it }
            ProdukHarga(harga = produk.price)
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
            .aspectRatio(1f)
            .padding(start = 16.dp, top=0.dp, end = 16.dp, bottom = 0.dp)
    )
}

@Composable
fun ProdukDetails(produk: Produk) {
    Spacer(modifier = Modifier.height(16.dp))
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
        repeat(5) {
            Icon(
                painter = painterResource(id = R.drawable.star),
                contentDescription = "Rating",
                tint = Color(0xFFFFD700),
                modifier = Modifier.size(20.dp)
            )
        }
    }
    Text(
        text = "Details",
        fontSize = 20.sp,
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Bold
    )
    Text(
        text = produk.description,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = poppinsFamily
    )
    Text(
        text = "Stok: ${produk.stok}kg",
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = poppinsFamily
    )
}

@Composable
fun ProdukHarga(harga: BigDecimal) {
    Text(
        text = "Rp${harga.toPlainString()}",
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
                    .size(30.dp)
                    .background(Color(0xFF52B788), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "-", fontSize = 24.sp, color = Color.Black, fontWeight = FontWeight.Bold
                )
            }
        }
        Text(
            text = "${quantity}kg",
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 5.dp)
        )
        IconButton(
            onClick = { onQuantityChange(quantity + 1) }
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(Color(0xFF52B788), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "+", fontSize = 22.sp, color = Color.Black
                )
            }
        }
    }
}

@Composable
fun AddKeranjangButton(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(vertical = 16.dp, horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { navController.navigate("Keranjang") },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = button)
        ) {
            Text(text = "Masukkan Keranjang", color = Color.White, fontFamily = poppinsFamily, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailProdukPreview() {
    val navController = rememberNavController()
    val produk = com.example.gomarina_mobile.dummyData.dummyData.dataProduk[0]
    Detail_ProdukContent(navController = navController, produk = produk)
}
