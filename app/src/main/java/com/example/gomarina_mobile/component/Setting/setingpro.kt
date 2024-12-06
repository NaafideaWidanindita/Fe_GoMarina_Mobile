package com.example.gomarina_mobile.component.Setting

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.TextButton
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.R
import com.example.gomarina_mobile.component.Profil.ProfileImage

@Composable
fun UserProfileSection(
    name: String = "Arifandi",
    profileImageRes: Int = R.drawable.ic_profile,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(top = 16.dp)
    ) {
        ProfileImage()

        Column(
            modifier = Modifier
                .wrapContentHeight()
        ) {
            Text(
                text = name,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            TextButton(
                onClick = { navController.navigate("profile") }, // Navigasi ke halaman profil
                modifier = Modifier.padding(bottom = 4.dp)
            ) {
                Text(
                    text = "Lihat profil",
                    fontSize = 14.sp,
                    color = Color(0xFF888888)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserProfileSection() {
    UserProfileSection(navController = rememberNavController())
}