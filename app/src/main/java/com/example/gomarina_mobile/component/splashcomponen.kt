package com.example.gomarina_mobile.component

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.example.gomarina_mobile.LoginScreen
import com.example.gomarina_mobile.R
import com.example.gomarina_mobile.ui.theme.bacground
import com.example.gomarina_mobile.ui.theme.button
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(activity: ComponentActivity) {
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
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Splash Image",
                modifier = Modifier.size(130.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Go Marina",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = button
            )
        }
    }

    // Using LaunchedEffect to handle delay and navigate to LoginScreen
    LaunchedEffect(Unit) {
        delay(3000)  // Wait for 3 seconds
        activity.setContent {
            LoginScreen()  // Navigate directly to LoginScreen
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(activity = ComponentActivity())
}
