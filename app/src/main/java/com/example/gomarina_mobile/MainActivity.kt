package com.example.gomarina_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.ui.theme.GoMarina_MobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoMarina_MobileTheme {
                val navController = rememberNavController()
                NavigationApp(navController = navController)
            }
        }
    }
}

@Composable
fun NavigationApp(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "DetailProduk"
    ) {
        composable("DetailProduk") {
            DetailProdukScreen(navController)
        }
        composable("Keranjang") {
            KeranjangScreen(navController)
        }
        composable("DetailPesanan") {
            DetailPesananScreen(navController)
        }
        composable("Pembayaran") {
            PembayaranScreen(navController)
        }
        composable("PembayaranBerhasil") {
            PembayaranBerhasilScreen(
                onKembaliKeBeranda = { navController.popBackStack("DetailProduk", inclusive = false) },
                onPesanan = { navController.navigate("CekPesanan") }
            )
        }
        composable("CekPesanan") {
            CekPesananScreen(navController)
        }
    }
}
