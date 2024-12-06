package com.example.gomarina_mobile.component.ListPesanan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.gomarina_mobile.component.RiwayatPesanan.RiwayatPesananScreen
import com.example.gomarina_mobile.ui.theme.PrimaryColor
import com.example.gomarina_mobile.ui.theme.bacground
import com.example.gomarina_mobile.ui.theme.poppinsFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListPesananScreen(
    navController: NavController) {
    var selectedTab by remember { mutableStateOf("List Pesanan") }

    Column(
        modifier = Modifier
            .background(PrimaryColor)
            .fillMaxHeight()
    ) {
        TopAppBar(
            title = { Text("Pesanan", color = Color.Black) },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = bacground
            ),
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.Black)
                }
            }
        )

        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TabItem("List Pesanan", isSelected = selectedTab == "List Pesanan") {
                selectedTab = "List Pesanan"
            }
            TabItem("Riwayat Pesanan", isSelected = selectedTab == "Riwayat Pesanan") {
                selectedTab = "Riwayat Pesanan"
            }
        }

        Divider(modifier = Modifier
            .width(500.dp)
            .padding(horizontal = 30.dp),
            thickness = 1.dp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (selectedTab == "List Pesanan") {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 6.dp ),
            ){
               items(5){
                   ListPesananItem(navController = navController)
               }
            }

        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 6.dp ),
            ) {
                items(2){
                    RiwayatPesananScreen(navController = navController)
                }
            }
        }
    }
}

@Composable
fun TabItem(title: String, isSelected: Boolean, onClick: () -> Unit) {
    Column {
        Row(
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                color = if (isSelected) Color.Black else Color.Gray,
                fontFamily = poppinsFamily,
                fontSize = 18.sp,
                fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
            )
        }
    }
}

@Preview (showBackground = true)
@Composable
private fun ListPesananPrev() {
    ListPesananScreen(navController = rememberNavController())
}