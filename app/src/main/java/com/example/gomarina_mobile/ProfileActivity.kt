package com.example.gomarina_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.component.ProfileHeader
import com.example.gomarina_mobile.component.ProfileContent

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
