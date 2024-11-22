package com.example.gomarina_mobile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.ui.theme.poppinsFamily

@Composable
fun KeranjangScreen(navController: NavHostController) {
    Scaffold(
        topBar = { KeranjangHeader() },
        bottomBar = { ButtonCheckout(navController) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .background(Color(0xFFF0F7F6))
                    .padding(paddingValues)
            ) {
                KeranjangContent()
            }
        }
    )
}

@Composable
fun KeranjangHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Keranjang Saya",
            fontSize = 32.sp,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

@Composable
fun KeranjangContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        repeat(10) {
            ItemKeranjang()
            HorizontalDivider(thickness = 1.dp, color = Color.Gray)
        }
    }
}

@Composable
fun ItemKeranjang() {
    var quantity by remember { mutableIntStateOf(1) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 8.dp, end = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = false,
            onCheckedChange = {},
            modifier = Modifier.padding(end = 8.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.jambu),
            contentDescription = "Gambar Jambu",
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Jambu", fontFamily = poppinsFamily, fontWeight = FontWeight.Bold, fontSize = 16.sp
            )
            Text(text = "Rp32.000", color = Color.Gray, fontSize = 14.sp
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = { if (quantity > 1) quantity-- }
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
                modifier = Modifier
                    .padding(horizontal = 5.dp)
            )
            IconButton(
                onClick = { quantity++ }
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
}

@Composable
fun ButtonCheckout(navController: NavController) {
    val total by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .navigationBarsPadding()
            .background(Color(0xFFD8ECE9))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = false,
                    onCheckedChange = {},
                    modifier = Modifier.padding(16.dp)
                )
                Text(text = "Semua", fontSize = 16.sp)
            }
            Text(text = "Total Rp$total", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Red
            )
            Button(
                onClick = { navController.navigate("DetailPesanan") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF72D7AA)),
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .fillMaxHeight()
                    .width(150.dp)
            ) {
                Text(text = "Checkout (0)", color = Color.Black, fontFamily = poppinsFamily, fontWeight = FontWeight.Medium,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KeranjangPreview() {
    val navController = rememberNavController()
    KeranjangScreen(navController = navController)
}