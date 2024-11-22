package com.example.gomarina_mobile

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.ui.theme.poppinsFamily

@Composable
fun PembayaranScreen(navController: NavHostController) {
    Scaffold(
        topBar = {PembayaranHeader()},
        bottomBar = { ButtonPembayaran(navController) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF0F7F6))
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues)
            ) {
                Alamat()

                Spacer(modifier = Modifier.height(24.dp))

                Pembayaran()
            }
        }
    )
}

@Composable
fun PembayaranHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Pembayaran", fontSize = 32.sp, fontFamily = poppinsFamily, fontWeight = FontWeight.Bold, color = Color.Black,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

@Composable
fun Alamat() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Alamat", color = Color.Black, fontSize = 24.sp, fontFamily = poppinsFamily, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick = { /* Action for edit */ },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit, // Ganti dengan ikon edit yang diinginkan
                    contentDescription = "Edit Address"
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Nama: ", color = Color.Black, fontSize = 15.sp, fontFamily = poppinsFamily, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f)
                )
                Text(text = "Naafi'dea", color = Color.Black, fontSize = 15.sp, fontFamily = poppinsFamily, fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Home: ", color = Color.Black, fontSize = 15.sp, fontFamily = poppinsFamily, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f)
                )
                Text(text = "Gang Delima No.4, RT/RW 01/01", color = Color.Black, fontSize = 15.sp, fontFamily = poppinsFamily, fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Patokan: ", color = Color.Black, fontSize = 15.sp, fontFamily = poppinsFamily, fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                )
                Text(text = "Sebelah rumah pak RT", color = Color.Black, fontSize = 15.sp, fontFamily = poppinsFamily, fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun Pembayaran() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Pembayaran", color = Color.Black, fontSize = 20.sp, fontFamily = poppinsFamily, fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE5F8E6), RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Transfer", color = Color.Black, fontSize = 15.sp, fontFamily = poppinsFamily, fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.jambu), // Replace with BCA logo resource
                        contentDescription = "BCA Logo",
                        modifier = Modifier
                            .padding(top = 16.dp, end = 16.dp)
                            .size(24.dp)
                    )
                    Text(text = "123***543", color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row (verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Upload Bukti TF", color = Color.Black, fontSize = 15.sp, fontFamily = poppinsFamily, fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)
                    )
                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .background(Color(0x99BABDBA), RoundedCornerShape(15.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                painter = painterResource(id = R.drawable.upload), // Replace with upload icon resource
                                contentDescription = "Upload",
                                tint = Color.Gray,
                                modifier = Modifier.size(40.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = "Unggah bukti", color = Color.Gray, fontSize = 14.sp, textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ButtonPembayaran(navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .navigationBarsPadding(),
        shape = RoundedCornerShape(
            topStart = 24.dp,
            topEnd = 24.dp
        ),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF4CC543))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF225F3E), RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SummaryRow(label = "Ongkos kirim", amount = "Rp5.000")
            SummaryRow(label = "Subtotal", amount = "Rp32.000")
            SummaryRow(label = "Total", amount = "Rp37.000", isTotal = true)

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("PembayaranBerhasil") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9CE5A5)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Pesan",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun SummaryRow(label: String, amount: String, isTotal: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = if (isTotal) Color.White else Color.LightGray,
            fontSize = if (isTotal) 18.sp else 16.sp,
            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal
        )
        Text(
            text = amount,
            color = if (isTotal) Color.White else Color.LightGray,
            fontSize = if (isTotal) 18.sp else 16.sp,
            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PembayaranPreview() {
    val navController = rememberNavController()
    PembayaranScreen(navController = navController)
}