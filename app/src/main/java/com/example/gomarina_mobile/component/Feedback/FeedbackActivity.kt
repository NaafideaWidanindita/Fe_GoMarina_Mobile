package com.example.gomarina_mobile.component.Feedback

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

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
