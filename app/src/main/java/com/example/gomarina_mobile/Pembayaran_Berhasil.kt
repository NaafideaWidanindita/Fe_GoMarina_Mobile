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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PembayaranBerhasilScreen(
    onKembaliKeBeranda: () -> Unit,
    onPesanan: () -> Unit
) {
    Scaffold(
        topBar = { PembayaranBerhasilHeader() },
        bottomBar = { ThanksCard() },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                PembayaranBerhasil(
                    onKembaliKeBeranda = onKembaliKeBeranda,
                    onPesanan = onPesanan
                )
            }
        }
    )
}

@Composable
fun PembayaranBerhasilHeader(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .statusBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Pembayaran Berhasil", fontSize = 10.sp, fontFamily = poppinsFontFamily, fontWeight = FontWeight.Medium, color = Color.Black
        )
    }
}

@Composable
fun PembayaranBerhasil(
    onKembaliKeBeranda: () -> Unit,
    onPesanan: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.check), // Ganti dengan ID drawable gambar centang Anda
                contentDescription = "Check Icon",
                modifier = Modifier.size(100.dp) // Sesuaikan ukuran sesuai kebutuhan
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Pembayaran Berhasil", fontSize = 28.sp, fontFamily = poppinsFontFamily, fontWeight = FontWeight.Bold, color = Color.Black
            )
            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                horizontalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                Button(
                    onClick = onKembaliKeBeranda,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6CE9BF)), // Mengatur warna tombol
                    modifier = Modifier.weight(2f)
                ) {
                    Text(text = "Kembali ke Beranda", color = Color.Black, fontFamily = poppinsFontFamily, fontWeight = FontWeight.Medium, fontSize = 10.sp
                    )
                }

                Button(
                    onClick = onPesanan,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6CE9BF)), // Mengatur warna tombol
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Pesanan", color = Color.Black, fontFamily = poppinsFontFamily, fontWeight = FontWeight.Medium, fontSize = 10.sp
                    )
                }
            }
        }
    }
}
@Composable
fun ThanksCard(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .navigationBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "GoMarina", fontSize = 12.sp, fontFamily = poppinsFontFamily, fontWeight = FontWeight.Medium, color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PembayaranBerhasilPreview() {
    PembayaranBerhasilScreen(
        onKembaliKeBeranda = {},
        onPesanan = {}
    )
}
