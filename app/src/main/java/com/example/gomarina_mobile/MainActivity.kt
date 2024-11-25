package com.example.gomarina_mobile

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.ui.theme.GoMarina_MobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            GoMarina_MobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    GoMarinaApp()
                }
            }
        }
    }
}

@Composable
fun GoMarinaApp (modifier: Modifier = Modifier) {

}

//    private fun goToNextActivity() {
//        val intent = Intent(this, ListPesananScreen()::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }
//        startActivity(intent)
//        finish()
//    }
//}


//@Composable
//fun MainScreen(onFinish: () -> Unit) {
//    val progress1 = remember { mutableStateOf(0f) }
//    val progress2 = remember { mutableStateOf(0f) }
//    val progress3 = remember { mutableStateOf(0f) }
//    val imageResId = remember { mutableStateOf(R.drawable.vb) }
//    var currentProgressBar by remember { mutableStateOf(1) }
//    var isAnimationRunning by remember { mutableStateOf(true) }
//    var isButtonClicked by remember { mutableStateOf(false) }
//
//    LaunchedEffect(isButtonClicked) {
//        while (isAnimationRunning && !isButtonClicked) {
//            when (currentProgressBar) {
//                1 -> {
//                    for (i in 0..200) {
//                        delay(20)
//                        progress1.value = i.toFloat()
//                        if (isButtonClicked) return@LaunchedEffect
//                    }
//                    currentProgressBar = 2
//                    imageResId.value = R.drawable.vb
//                }
//                2 -> {
//                    for (i in 0..100) {
//                        delay(20)
//                        progress2.value = i.toFloat()
//                        if (isButtonClicked) return@LaunchedEffect
//                    }
//                    currentProgressBar = 3
//                    imageResId.value = R.drawable.vb
//                }
//                3 -> {
//                    for (i in 0..100) {
//                        delay(20)
//                        progress3.value = i.toFloat()
//                        if (isButtonClicked) return@LaunchedEffect
//                    }
//                    isAnimationRunning = false
//                    onFinish()
//                }
//            }
//        }
//        if (isButtonClicked) {
//            onFinish()
//        }
//    }
//
//    Box(modifier = Modifier.fillMaxSize()) {
//
//        Image(
//            painter = painterResource(id = imageResId.value),
//            contentDescription = null,
//            modifier = Modifier.fillMaxSize()
//        )
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            verticalArrangement = Arrangement.SpaceBetween,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 52.dp, start = 32.dp, end = 32.dp),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                LinearProgressIndicator(
//                    progress = progress1.value / 200,
//                    color = Color(0xFF4CAF50),
//                    modifier = Modifier
//                        .weight(10f)
//                        .height(5.dp)
//                )
//
//                Spacer(modifier = Modifier.width(8.dp))
//
//                LinearProgressIndicator(
//                    progress = progress2.value / 100,
//                    color = Color(0xFF4CAF50),
//                    modifier = Modifier
//                        .weight(10f)
//                        .height(5.dp)
//                )
//
//                Spacer(modifier = Modifier.width(8.dp))
//
//                LinearProgressIndicator(
//                    progress = progress3.value / 100,
//                    color = Color(0xFF4CAF50),
//                    modifier = Modifier
//                        .weight(10f)
//                        .height(5.dp)
//                )
//            }
//
//            Spacer(modifier = Modifier.height(50.dp))
//
//            Button(
//                onClick = {
//                    isButtonClicked = true
//                },
//                modifier = Modifier
//                    .align(Alignment.CenterHorizontally)
//                    .padding(bottom = 29.dp)
//            ) {
//                Text(text = "Mulai Belanja")
//
//                val navController = rememberNavController()
//                NavigationApp(navController = navController)
//            }
//        }
//    }
//}


//@Composable
//fun NavigationApp(navController: NavHostController) {
//    NavHost(
//        navController = navController,
//        startDestination = "DetailProduk"
//    ) {
////        composable("DetailProduk") {
////            Detail_Product(navController)
////        }
////        composable("Keranjang") {
////            KeranjangScreen(navController)
////        }
////        composable("DetailPesanan") {
////            DetailPesananScreen(navController)
////        }
////        composable("Pembayaran") {
////            PembayaranScreen(navController)
////        }
////        composable("PembayaranBerhasil") {
////            PembayaranBerhasilScreen(
////                onKembaliKeBeranda = { navController.popBackStack("DetailProduk", inclusive = false) },
////                onPesanan = { navController.navigate("CekPesanan") }
////            )
////        }
//        composable("CekPesanan") {
//            CekScreen(navController)
//        }
//    }
//}

