package com.example.gomarina_mobile.component.Feedback

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.component.BottomNavigationBar

@Composable
fun MainFeedbackScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
        FeedbackScreen(navController = navController)
    }
}

@Preview(showBackground = true)
@Composable
fun FeedbackPreview() {
    val navController = rememberNavController()
    MainFeedbackScreen(navController)
}
