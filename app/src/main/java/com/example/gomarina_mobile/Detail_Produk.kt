package com.example.gomarina_mobile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

val poppinsFontFamily = FontFamily(
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_medium, FontWeight.Medium)
)

@Composable
fun DetailProdukScreen(navController: NavHostController) {
    Scaffold(
        topBar = { ProdukHeader() },
        bottomBar = {  AddKeranjangButton(navController = navController) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF0F7F6))
                    .padding(paddingValues)
            ) {
                ProdukContent()
            }
        }
    )
}

@Composable
fun ProdukHeader(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Produk", // Judul di bagian atas
            fontSize = 32.sp,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

@Composable
fun ProdukContent() {
    var quantity by remember { mutableStateOf(1) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(top = 8.dp, bottom = 32.dp, start = 32.dp, end = 32.dp)
    ) {
        ProdukImage()
        ProdukDetails()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 8.dp)
        ) {
            ProdukJumlah(quantity) { quantity = it }
            ProdukHarga()
        }
    }
}

@Composable
fun ProdukImage() {
    Image(
        painter = painterResource(id = R.drawable.jambu),
        contentDescription = "Jambu",
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(Color(0xFFC8F0DE), shape = MaterialTheme.shapes.medium)
            .padding(16.dp)
    )
}

@Composable
fun ProdukDetails() {
    Spacer(modifier = Modifier.height(16.dp))
    Text(text = "Jambu", fontSize = 24.sp, fontFamily = poppinsFontFamily, fontWeight = FontWeight.Bold
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
    Text( text = "Details", fontSize = 20.sp, fontFamily = poppinsFontFamily, fontWeight = FontWeight.Bold
    )
    Text(text = "Buah jambu yang manis", fontSize = 16.sp, fontWeight = FontWeight.Medium, fontFamily = poppinsFontFamily
    )
    Text(text = "Stok: 100kg", fontSize = 16.sp, fontWeight = FontWeight.Bold, fontFamily = poppinsFontFamily
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
fun ProdukHarga() {
    Text(
        text = "Rp32.000",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = poppinsFontFamily
    )
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
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF72D7AA))
        ) {
            Text(text = "Masukkan Keranjang", color = Color.Black, fontFamily = poppinsFontFamily, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailProdukPreview() {
    val navController = rememberNavController()
    DetailProdukScreen(navController = navController)
}