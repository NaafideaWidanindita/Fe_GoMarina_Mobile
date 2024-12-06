package com.example.gomarina_mobile.component.Profil

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.component.BottomNavigationBar

@Composable
fun ProfileScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ProfileHeader()

            Spacer(modifier = Modifier.height(16.dp))

            ProfileContent(paddingValues = PaddingValues(0.dp))
        }
    }
}

@Composable
fun MainProfileScreen(navController: NavHostController) {
    ProfileScreen(navController)
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    val navController = rememberNavController()
    MainProfileScreen(navController)
}
