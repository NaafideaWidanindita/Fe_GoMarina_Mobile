package com.example.gomarina_mobile.component.Profil

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
    ) {
        ProfileHeader()

        Spacer(modifier = Modifier.height(16.dp))

        ProfileContent(paddingValues = PaddingValues(0.dp))
    }
}

@Composable
fun MainProfileScreen(navController: NavHostController) {
    ProfileScreen()
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    val navController = rememberNavController()
    MainProfileScreen(navController)
}
