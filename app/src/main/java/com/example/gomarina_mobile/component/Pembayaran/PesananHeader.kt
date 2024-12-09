package com.example.gomarina_mobile.component.Pembayaran

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.ui.theme.bacground
import com.example.gomarina_mobile.ui.theme.GoMarina_MobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PesananHeader(navController: NavHostController) {
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
}

@Preview(showBackground = true)
@Composable
fun PesananHeaderPreview() {
    GoMarina_MobileTheme {
        PesananHeader(navController = rememberNavController())
    }
}