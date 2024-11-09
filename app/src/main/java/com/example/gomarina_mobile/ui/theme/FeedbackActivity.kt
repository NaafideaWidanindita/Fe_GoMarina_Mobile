package com.example.gomarina_mobile.ui.theme

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.R

class FeedbackActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "feedback") {
                composable("settings") { SettingsScreen(navController) }
                composable("feedback") { FeedbackScreen(navController) }
                composable("profile") { ProfileScreen() }
                composable("pesanan") { AppNavigation() }
            }
        }
    }
}

@Composable
fun StarRating(rating: Float, onRatingChanged: (Float) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        (0..4).forEach { index ->
            IconButton(onClick = { onRatingChanged((index + 1).toFloat()) }) {
                Icon(
                    painter = painterResource(id = if (index < rating) R.drawable.ic_star_filled else R.drawable.ic_star),
                    contentDescription = "Star Rating",
                    tint = Color.Yellow,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}




@Composable
fun FeedbackScreen(navController: NavController) {
    var feedbackText by remember { mutableStateOf(TextFieldValue("")) }
    var rating by remember { mutableStateOf(0f) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0F7FA)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp)
        ) {
            IconButton(onClick = { navController.popBackStack() }) { // Navigasi kembali ke halaman sebelumnya
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = "Kembali"
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Feedback", fontSize = 29.sp, color = Color(0xFF525252))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Berikan penilaian anda", fontSize = 18.sp, color = Color(0xFF525252))

        Spacer(modifier = Modifier.height(20.dp))
        StarRating(rating = rating, onRatingChanged = { rating = it })
        Spacer(modifier = Modifier.height(20.dp))
        BasicTextField(
            value = feedbackText,
            onValueChange = { feedbackText = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 10.dp)
                .background(Color(0xFFD3D3D3), shape = RoundedCornerShape(8.dp))
                .padding(8.dp),
            decorationBox = { innerTextField ->
                if (feedbackText.text.isEmpty()) {
                    Text(text = "Ceritakan pengalaman anda", color = Color(0xFF525252))
                }
                innerTextField()
            }
        )
        Spacer(modifier = Modifier.height(200.dp))
        Button(
            onClick = {
                if (rating == 0f) {
                    Toast.makeText(context, "Harap pilih rating!", Toast.LENGTH_SHORT).show()
                } else if (feedbackText.text.isEmpty()) {
                    Toast.makeText(context, "Harap isi feedback!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Feedback terkirim! Terima kasih!", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(bottom = 60.dp)
        ) {
            Text(text = "Kirim", color = Color.White)
        }

        Spacer(modifier = Modifier.weight(1f))
        BottomNavigationBar(navController)
    }
}













@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 1.dp)
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .shadow(elevation = 4.dp),
        backgroundColor = Color.White,
        elevation = 0.dp
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_home),
                    contentDescription = "Beranda",
                    tint = Color.Gray
                )
            },
            label = { Text("Beranda", color = Color.Gray) },
            selected = false,
            onClick = { navController.navigate("home") } // Navigate to home
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_order),
                    contentDescription = "Pesanan",
                    tint = Color.Gray
                )
            },
            label = { Text("Pesanan", color = Color.Gray) },
            selected = false,
            onClick = { navController.navigate("pesanan") } // Navigate to order page
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = "Pengaturan",
                    tint = Color(0xFF00796B)
                )
            },
            label = { Text("Pengaturan", color = Color(0xFF00796B)) },
            selected = false,
            onClick = { navController.navigate("settings") } // Navigate to settings page
        )
    }
}
