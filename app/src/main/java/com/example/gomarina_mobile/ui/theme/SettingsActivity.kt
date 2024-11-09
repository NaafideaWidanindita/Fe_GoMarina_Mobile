package com.example.gomarina_mobile.ui.theme

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.R

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "settings") {
                composable("settings") { SettingsScreen(navController) }
                composable("Feedback") { FeedbackScreen(navController) }
                composable("profile") { ProfileScreen() }

            }
        }
    }
}

@Composable
fun SettingsScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        BackButton(navController)
        TitleSection()
        UserProfileSection()
        FeedbackSection(navController)
        Spacer(modifier = Modifier.weight(1f))
        SettingsBottomNavigationBar(navController)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSettingsScreen() {
    SettingsScreen(navController = rememberNavController())
}

@Composable
fun BackButton(navController: NavController) {
    IconButton(
        onClick = { navController.popBackStack() },
        modifier = Modifier.padding(16.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
            contentDescription = "Kembali"
        )
    }
}

@Composable
fun TitleSection() {
    Text(
        text = "Pengaturan",
        fontSize = 28.sp,
        color = Color(0xFF525252),
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun UserProfileSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(top = 16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = "Gambar Profil",
            modifier = Modifier
                .size(60.dp)
                .padding(end = 16.dp)
        )

        Column(
            modifier = Modifier
                .wrapContentHeight()
        ) {
            Text(
                text = "Arifandi",
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Lihat profil",
                fontSize = 14.sp,
                color = Color(0xFF888888),

            )
        }
    }
}

@Composable
fun FeedbackSection(navController: NavController) {
    TextButton(
        onClick = { navController.navigate("feedback") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Feedback", fontSize = 18.sp, color = Color.Black)
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_right_24),
            contentDescription = "Lanjutkan ke Feedback",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun SettingsBottomNavigationBar(navController: NavController) {
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
            label = {
                Text(
                    "Beranda",
                    color = Color.Gray
                )
            },
            selected = false,
            onClick = { /* kode */ }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_order),
                    contentDescription = "Pesanan",
                    tint = Color.Gray
                )
            },
            label = {
                Text(
                    "Pesanan",
                    color = Color.Gray
                )
            },
            selected = true,
            onClick = { navController.navigate("pesanan") }
        )

        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = "Pengaturan",
                    tint = Color(0xFF00796B)
                )
            },
            label = {
                Text(
                    "Pengaturan",
                    color = Color(0xFF00796B)
                )
            },
            selected = false,
            onClick = { navController.navigate("settings") }
        )
    }
}



