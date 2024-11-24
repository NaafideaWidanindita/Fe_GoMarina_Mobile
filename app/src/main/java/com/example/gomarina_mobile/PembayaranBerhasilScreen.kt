package com.example.gomarina_mobile

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.component.PembayaranBerhasilContent
import com.example.gomarina_mobile.component.PembayaranBerhasilHeader
import com.example.gomarina_mobile.dummyData.DummyData
import java.lang.reflect.Modifier

@Composable
fun PembayaranBerhasilScreen(navController: NavHostController) {

        PembayaranBerhasilHeader(navController = navController)
        PembayaranBerhasilContent()
}

@Preview(showBackground = true)
@Composable
private fun PembayaranBerhasilScreenPreview() {
    val navController = rememberNavController()
    PembayaranBerhasilScreen(navController)
}
