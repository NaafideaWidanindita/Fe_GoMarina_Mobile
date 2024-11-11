package com.example.gomarina_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.clickable
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.gomarina_mobile.R
import com.example.gomarina_mobile.ui.theme.GoMarina_MobileTheme

class Listpesanan : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoMarina_MobileTheme {
                AppNavigation()
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "listPesanan") {
        composable("listPesanan") { ListPesananScreen(navController) }

        composable("Settings") { SettingsScreen(navController) }
    }
}

@Composable
fun ListPesananScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf("List Pesanan") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(1.dp)
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.padding(start = 0.dp)
        ) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Kembali")
        }

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "List Pesanan",
            fontSize = 18.sp,
            color = Color(0xFF525252),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TabItem("List Pesanan", isSelected = selectedTab == "List Pesanan") {
                selectedTab = "List Pesanan"
            }
            TabItem("Riwayat Pesanan", isSelected = selectedTab == "Riwayat Pesanan") {
                selectedTab = "Riwayat Pesanan"
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (selectedTab == "List Pesanan") {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(containerColor = Color(0xFF04211F)),
                elevation = CardDefaults.cardElevation(43.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Jambu Air\nJambu Batu\nJambu Biji",
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "20/11/26",
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = { /* Cek Button Action */ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00B395)),
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(text = "Cek", color = Color.White)
                        }
                    }

                    Text(
                        text = "Total  Rp37.000",
                        color = Color.White,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        } else {

            Text(
                text = "Riwayat Pesanan akan ditampilkan di sini.",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth(),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .shadow(6.dp, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
        ) {
            BottomNavigation(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = Color.White,
                elevation = 0.dp
            ) {
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_home),
                            contentDescription = "Beranda",
                            tint = Color.Gray
                        )
                    },
                    label = {
                        Text(
                            "Beranda",
                            color = Color.Gray
                        )
                    },
                    selected = false,
                    onClick = {
                        navController.navigate("beranda")
                    }
                )
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_order),
                            contentDescription = "Pesanan",
                            tint = Color(0xFF00796B)
                        )
                    },
                    label = {
                        Text(
                            "Pesanan",
                            color = Color(0xFF00796B)
                        )
                    },
                    selected = true,
                    onClick = { /* kode menu */ }
                )
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_settings),
                            contentDescription = "Pengaturan",
                            tint = Color.Gray
                        )
                    },
                    label = {
                        Text(
                            "Pengaturan",
                            color = Color.Gray
                        )
                    },
                    selected = false,
                    onClick = {
                        navController.navigate("Settings")
                    }
                )
            }
        }
    }
}

@Composable
fun TabItem(title: String, isSelected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            color = if (isSelected) Color.Black else Color.Gray,
            fontSize = 16.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
        )
    }
}
