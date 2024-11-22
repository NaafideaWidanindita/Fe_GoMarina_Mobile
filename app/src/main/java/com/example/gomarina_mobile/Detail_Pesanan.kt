package com.example.gomarina_mobile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.ui.theme.poppinsFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPesananScreen(navController: NavHostController) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = { DetailPesananHeader() },
        bottomBar = { ButtonBayar(navController) },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color(0xFFF0F7F6))
                    .verticalScroll(scrollState)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    PesananItem(imageRes = R.drawable.jambu, name = "Jambu Air", price = "Rp32.000")
                    PesananItem(imageRes = R.drawable.jambu, name = "Jambu Batu", price = "Rp32.000")
                    PesananItem(imageRes = R.drawable.jambu, name = "Jambu Air", price = "Rp32.000")
                    PesananItem(imageRes = R.drawable.jambu, name = "Jambu Air", price = "Rp32.000")
                    PesananItem(imageRes = R.drawable.jambu, name = "Jambu Batu", price = "Rp32.000")
                    PesananItem(imageRes = R.drawable.jambu, name = "Jambu Air", price = "Rp32.000")
                }
            }
        }
    )
}

@Composable
fun DetailPesananHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .background(Color(0xFFF0F7F6)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Detail Pesanan",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun PesananItem(imageRes: Int, name: String, price: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFC8F0DE))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = name, fontSize = 18.sp, color = Color.Black)
                Text(text = price, fontSize = 16.sp, color = Color.Black)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "1 kg", modifier = Modifier.padding(horizontal = 8.dp))
            }
        }
    }
}

@Composable
fun ButtonBayar(navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1A6659))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFFFFF), shape = RoundedCornerShape(12.dp))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Total :", color = Color.Black, fontSize = 18.sp)
                Text(text = "Rp96.000", color = Color.Black, fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {navController.navigate("Pembayaran")},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6CE9BF))
            ) {
                Text(text = "Bayar", color = Color.Black, fontFamily = poppinsFamily, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailPesananPreview() {
    val navController = rememberNavController()
    DetailPesananScreen(navController = navController)
}