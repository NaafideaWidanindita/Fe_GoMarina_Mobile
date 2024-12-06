package com.example.gomarina_mobile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.gomarina_mobile.ui.theme.bg_button
import com.example.gomarina_mobile.ui.theme.button

sealed class NavigationItem(val route: String, val label: String, val icon: ImageVector) {
    object Beranda : NavigationItem("beranda", "Beranda", Icons.Default.Home)
    object Pesanan : NavigationItem("listpesanan", "Pesanan", Icons.Default.List)
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
        modifier = Modifier.background(bg_button),
        contentColor = Color.Gray
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    androidx.compose.material.Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = if (currentRoute == item.route) button else Color.Gray // Change icon color based on selection
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        color = if (currentRoute == item.route) button else Color.Gray // Change text color based on selection
                    )
                },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                modifier = Modifier
                    .background(bg_button) // Keep the background color as bg_button for all items
                    .padding(8.dp) // Optional padding for spacing
            )
        }
    }
}

@Preview
@Composable
fun PreviewBottomNavigationBar() {
    val navController = rememberNavController()

    // Simulate a navigation graph for preview purposes
    NavHost(navController = navController, startDestination = NavigationItem.Beranda.route) {
        composable(NavigationItem.Beranda.route) {}
        composable(NavigationItem.Pesanan.route) {}
        composable(NavigationItem.Pengaturan.route) {}
    }

    BottomNavigationBar(navController = navController)
}
