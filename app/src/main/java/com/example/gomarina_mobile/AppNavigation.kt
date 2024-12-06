package com.example.gomarina_mobile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gomarina_mobile.component.Beranda.Beranda
import com.example.gomarina_mobile.component.Cek.CekPesananScreen
import com.example.gomarina_mobile.component.Cek.CekScreen
import com.example.gomarina_mobile.component.Feedback.FeedbackScreen
import com.example.gomarina_mobile.component.Keranjang.KeranjangScreen
import com.example.gomarina_mobile.component.ListPesanan.ListPesananScreen
import com.example.gomarina_mobile.component.Login.LoginScreen
import com.example.gomarina_mobile.component.SplashScreen.SplashComponent
import com.example.gomarina_mobile.component.Product.ProductScreen
import com.example.gomarina_mobile.component.Pembayaran.PesananScreen
import com.example.gomarina_mobile.component.PembayaranBerhasil.PembayaranBerhasilScreen
import com.example.gomarina_mobile.component.Profil.ProfileScreen
import com.example.gomarina_mobile.component.Register.SignUpScreen
import com.example.gomarina_mobile.component.RiwayatPesanan.RiwayatPesananScreen
import com.example.gomarina_mobile.component.Setting.SettingsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashComponent(navController = navController) }
        composable("login") { LoginScreen(navController = navController) }
        composable("register"){ SignUpScreen(navController = navController)}
        composable("beranda") { Beranda(navController = navController)}
        composable("Produk/{produkId}") { backStackEntry ->
            val produkId = backStackEntry.arguments?.getString("produkId")?.toIntOrNull()
            if (produkId != null) {
                ProductScreen(navController = navController, produkId = produkId)
            } else {
                Text("Error: Produk ID tidak valid.")
            }
        }
        composable("pesanan") { PesananScreen(navController = navController) }
        composable("riwayatpesanan") { RiwayatPesananScreen(navController = navController) }
        composable("riwayatpesanan") { RiwayatPesananScreen(navController = navController) }
        composable("keranjang") { KeranjangScreen(navController = navController) }
        composable("Pembayaran") { PesananScreen(navController = navController) }
        composable("berhasil") { PembayaranBerhasilScreen(navController = navController) }
        composable("listpesanan") { ListPesananScreen(navController = navController) }
        composable("Cek") { CekScreen(navController = navController) }
        composable("profile") { ProfileScreen(navController = navController) }
        composable("CekPesanan") { CekPesananScreen(navController = navController) }
        composable("Setting") { SettingsScreen(navController = navController) }
        composable("feedback") { FeedbackScreen(navController = navController) }
    }
}