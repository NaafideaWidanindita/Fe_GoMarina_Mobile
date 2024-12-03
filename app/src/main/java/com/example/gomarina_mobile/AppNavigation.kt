package com.example.gomarina_mobile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gomarina_mobile.component.Beranda.Beranda
import com.example.gomarina_mobile.component.Cek.CekPesanan
import com.example.gomarina_mobile.component.Cek.CekPesananScreen
import com.example.gomarina_mobile.component.Cek.CekScreen
import com.example.gomarina_mobile.component.Feedback.FeedbackScreen
import com.example.gomarina_mobile.component.Keranjang.KeranjangScreen
import com.example.gomarina_mobile.component.ListPesanan.ListPesananScreen
import com.example.gomarina_mobile.component.Login.LoginScreen
import com.example.gomarina_mobile.component.Pembayaran.PesananScreen
import com.example.gomarina_mobile.component.SplashScreen.SplashComponent
import com.example.gomarina_mobile.component.Product.ProductScreen
import com.example.gomarina_mobile.component.Pembayaran.PesananScreen
import com.example.gomarina_mobile.component.PembayaranBerhasil.PembayaranBerhasilScreen
import com.example.gomarina_mobile.component.Profil.ProfileContent
import com.example.gomarina_mobile.component.Profil.ProfileScreen
import com.example.gomarina_mobile.component.Register.SignUpScreen
import com.example.gomarina_mobile.component.RiwayatPesanan.RiwayatPesananScreen
import com.example.gomarina_mobile.component.Setting.SettingsScreen

sealed class NavigationItem(val route: String, val label: String, val icon: ImageVector) {
    object Beranda : NavigationItem("beranda", "Beranda", Icons.Default.Home)
    object Pesanan : NavigationItem("pesanan", "Pesanan", Icons.Default.List)
    object Pengaturan : NavigationItem("Setting", "Setting", Icons.Default.Settings)
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Beranda,
        NavigationItem.Pesanan,
        NavigationItem.Pengaturan
    )

    BottomNavigation(
        modifier = Modifier.background(Color.White),
        contentColor = Color.Green
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        color = if (currentRoute == item.route) Color.Green else Color.Gray
                    )
                },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            if (currentRoute !in listOf("splash", "login", "register", "keranjang", "berhasil")) {
                BottomNavigationBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "splash",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("splash") { SplashComponent(navController = navController) }
            composable("login") { LoginScreen(navController = navController) }
            composable("register") { SignUpScreen(navController = navController) }
            composable("beranda") { Beranda(navController = navController) }
            composable("pesanan") { PesananScreen(navController = navController) }
            composable("riwayatpesanan") { RiwayatPesananScreen(navController = navController) }
            composable("keranjang") { KeranjangScreen(navController = navController) }
            composable("Pembayaran") { PesananScreen(navController = navController) }
            composable("berhasil") { PembayaranBerhasilScreen(navController = navController) }
            composable("Listpesanan") { ListPesananScreen(navController = navController) }
            composable("Cek") { CekScreen(navController = navController) }
            composable("profile") { ProfileScreen() }
            composable("CekPesanan") { CekPesananScreen(navController = navController) }
            composable("Setting") { SettingsScreen(navController = navController) }
            composable("feedback") { FeedbackScreen(navController = navController) }
            composable("detail/{produkId}",
                arguments = listOf(navArgument("produkId") { type = NavType.IntType })
            ) { backStackEntry ->
                val produkId = backStackEntry.arguments?.getInt("produkId") ?: 0
                ProductScreen(navController = navController, produkId = produkId)
            }
            composable("pengaturan") { PengaturanScreen() }
        }
    }
}


@Composable
fun PengaturanScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Pengaturan")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAppNavigation() {
    AppNavigation()
}

