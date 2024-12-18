package com.example.gomarina_mobile.component.Pembayaran

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.ui.theme.bacground
import com.example.gomarina_mobile.ui.theme.bg_button
import com.example.gomarina_mobile.ui.theme.button
import com.example.gomarina_mobile.ui.theme.poppinsFamily

@Composable
fun PesananScreen(navController: NavHostController, selectedItems: String) {
    var totalALL by remember { mutableStateOf(0.0) }
    var selectedItemsState by remember { mutableStateOf(selectedItems) }

    Log.d("PesananScreen", "Selected Items: $selectedItemsState")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bacground)
    ) {
        PesananHeader(navController)
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            PesananContent(selectedItems = selectedItemsState)
        }
        ButtonPembayaran(totalALL = totalALL)
    }
}

@Composable
fun ButtonPembayaran(totalALL: Double) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .navigationBarsPadding()
            .background(bg_button)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Total Rp${totalALL}",
                    fontFamily = poppinsFamily,
                    fontSize = 15.sp,
                    color = Color(0xFF666464),
                    fontWeight = FontWeight.Medium
                )
            }

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = button),
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .fillMaxHeight()
                    .width(120.dp)
            ) {
                Text(
                    text = "Pesan",
                    color = Color.White,
                    fontFamily = poppinsFamily,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PesananScreenPreview() {
    val navController = rememberNavController()
    val selectedItems = "Item 1, Item 2, Item 3"
    PesananScreen(navController = navController, selectedItems = selectedItems)
}
