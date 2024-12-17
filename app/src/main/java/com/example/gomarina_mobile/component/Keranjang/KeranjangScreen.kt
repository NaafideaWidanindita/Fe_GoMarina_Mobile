package com.example.gomarina_mobile.component.Keranjang

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.gomarina_mobile.model.KeranjangItem

@Composable
fun KeranjangScreen(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

    // Menampilkan loading sementara data diambil
    var keranjangItems by remember { mutableStateOf<List<KeranjangItem>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    // Fetch data saat `role_id` berubah
    val userId = sharedPreferences.getInt("user_id", 0)

    LaunchedEffect(userId) {
        if (userId != 0) {
            getItemKeranjangByUserId(userId) { items, status ->
                Log.d("KeranjangScreen", "Fetch Status: $status")
                if (status == "Success" && items != null) {
                    keranjangItems = items
                } else {
                    Log.e("KeranjangScreen", "Error fetching data: $status")
                }
                isLoading = false
            }
        } else {
            isLoading = false
            Log.e("KeranjangScreen", "user_id is not valid")
        }
    }

    Column {
        KeranjangHeader(navController)

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (keranjangItems.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Keranjang Kosong",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        } else {
            KeranjangContent(navController = navController, items = keranjangItems)
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun KeranjangScreenPreview() {
//    val navController = rememberNavController()
//    KeranjangScreen(navController = navController)
//}
