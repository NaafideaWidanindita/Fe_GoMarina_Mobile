package com.example.gomarina_mobile.component.PembayaranBerhasil

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun PembayaranBerhasilScreen(navController: NavHostController) {

        PembayaranBerhasilHeader(navController = navController)
        PembayaranBerhasilContent(navController = navController)
}

@Preview(showBackground = true)
@Composable
private fun PembayaranBerhasilScreenPreview() {
    val navController = rememberNavController()
    PembayaranBerhasilScreen(navController)
}
