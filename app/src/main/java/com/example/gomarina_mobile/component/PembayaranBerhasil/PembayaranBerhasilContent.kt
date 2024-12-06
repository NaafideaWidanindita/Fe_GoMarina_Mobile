package com.example.gomarina_mobile.component.PembayaranBerhasil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gomarina_mobile.ui.theme.bacground
import com.example.gomarina_mobile.ui.theme.button
import com.example.gomarina_mobile.ui.theme.poppinsFamily
import com.example.gomarina_mobile.ui.theme.robotoFamily
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun PembayaranBerhasilContent(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bacground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Content(modifier = Modifier.weight(1f), navController = navController)
        Spacer(modifier = Modifier.height(16.dp))
        Footer()
    }
}

@Composable
fun AnimatedTuingTuingIcon() {
    val animatedOffsetX by animateFloatAsState(
        targetValue = 10f,
        animationSpec = repeatable(
            iterations = 5,
            animation = tween(
                durationMillis = 200,
                delayMillis = 0
            )
        )
    )

    // Ikon dengan animasi
    Icon(
        imageVector = Icons.Default.CheckCircle,
        contentDescription = "Check Icon",
        tint = Color.Green,
        modifier = Modifier
            .size(100.dp)
            .offset(x = animatedOffsetX.dp)
    )
}

@Composable
fun Content(modifier: Modifier = Modifier, navController: NavController) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedTuingTuingIcon() // Menampilkan ikon dengan animasi
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Pembayaran Berhasil",
            fontSize = 28.sp,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Transaksi anda berhasil pada ${getCurrentTime()}", // Menggabungkan teks dan waktu
            fontSize = 16.sp,
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(32.dp))
        ButtonNav(navController )
    }
}

@Composable
fun getCurrentTime(): String {
    val sdf = SimpleDateFormat("HH:mm:ss")
    return sdf.format(Date())
}

@Composable
fun ButtonNav(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        Button(
            onClick = { navController.navigate("Beranda") },
            colors = ButtonDefaults.buttonColors(containerColor = button), // Mengatur warna tombol
            modifier = Modifier.weight(1.01f)
        ) {
            Text(
                text = "Kembali ke Beranda",
                color = Color.White,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 8.sp
            )
        }

        Button(
            onClick = { navController.navigate("Listpesanan") },
            colors = ButtonDefaults.buttonColors(containerColor = button), // Mengatur warna tombol
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Pesanan",
                color = Color.White,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 10.sp
            )
        }
    }
}

@Composable
fun Footer() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "GoMarina",
            fontSize = 12.sp,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PembayaranBerhasilContentPreview() {
    val navController = rememberNavController()
    PembayaranBerhasilContent(navController = navController)
}
