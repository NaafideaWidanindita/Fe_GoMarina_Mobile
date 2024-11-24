package com.example.gomarina_mobile

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.component.CekHeader
import com.example.gomarina_mobile.component.CekContent

@Composable
fun CekScreen(navController: NavHostController) {
    Column {
        CekHeader(navController)
        CekContent(navController)
    }
}

@Preview(showBackground = true)
@Composable
private fun CekScreenPreview() {
    val navController = rememberNavController()
    CekScreen(navController = navController)
}
