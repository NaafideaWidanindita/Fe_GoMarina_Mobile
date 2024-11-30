package com.example.gomarina_mobile

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.component.Beranda.Beranda
import com.example.gomarina_mobile.component.Login.LoginScreen
import com.example.gomarina_mobile.component.SplashScreen.SplashComponent
import com.example.gomarina_mobile.component.Product.ProductScreen
import com.example.gomarina_mobile.component.Pembayaran.PesananScreen
import com.example.gomarina_mobile.component.Register.SignUpScreen
import com.example.gomarina_mobile.component.RiwayatPesanan.RiwayatPesananScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashComponent(navController = navController) }
        composable("login") { LoginScreen(navController = navController) }
        composable("register"){ SignUpScreen(navController = navController)}
        composable("beranda") { Beranda(navController = navController)}
        composable("product") { ProductScreen(navController = navController) }
        composable("pesanan") { PesananScreen(navController = navController) }
        composable("riwayatpesanan") { RiwayatPesananScreen(navController = navController) }
    }
}
