package com.example.gomarina_mobile.component.Setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.R
import com.example.gomarina_mobile.component.BottomNavigationBar

@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            BackButton(navController)
            TitleSection()
            UserProfileSection(navController = navController)
            FeedbackSection(navController)
            Spacer(modifier = Modifier.weight(1f))
        }
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
fun FeedbackSection(navController: NavController) {
    TextButton(
        onClick = { navController.navigate("Feedback") },
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
