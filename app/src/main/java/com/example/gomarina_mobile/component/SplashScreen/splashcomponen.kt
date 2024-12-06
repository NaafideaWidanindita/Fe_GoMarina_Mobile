package com.example.gomarina_mobile.component.SplashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.R
import com.example.gomarina_mobile.ui.theme.bacground
import kotlinx.coroutines.delay

@Composable
fun SplashComponent(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .background(bacground),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.newlogo),
                contentDescription = "Logo",
                modifier = Modifier.size(180.dp)
            )
        }
    }

    LaunchedEffect(Unit) {
        delay(1500)
        navController.navigate("login")
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    val navController = rememberNavController() // Create a mock NavController for preview
    SplashComponent(navController = navController)
}
