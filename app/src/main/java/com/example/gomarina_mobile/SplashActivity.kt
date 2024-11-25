package com.example.gomarina_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.gomarina_mobile.component.SplashScreen
import com.example.gomarina_mobile.ui.theme.GoMarina_MobileTheme

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoMarina_MobileTheme {
                // Panggil SplashScreen dan berikan activity ini sebagai parameter
                SplashScreen(activity = this)
            }
        }
    }
}
