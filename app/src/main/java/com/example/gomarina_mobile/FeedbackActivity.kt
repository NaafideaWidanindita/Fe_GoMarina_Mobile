package com.example.gomarina_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.component.FeedbackScreen
import com.example.gomarina_mobile.ui.theme.GoMarina_MobileTheme

@Composable
fun MainFeedbackScreen(navController: NavHostController) {
    FeedbackScreen(navController = navController)

}

@Preview(showBackground = true)
@Composable
fun FeedbackPreview() {
    val navController = rememberNavController()
    MainFeedbackScreen(navController)
}
