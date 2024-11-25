package com.example.gomarina_mobile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gomarina_mobile.R
import com.example.gomarina_mobile.ui.theme.poppinsFamily

@Composable
fun WelcomeHeader() {
    Column(
        modifier = Modifier.padding(start = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.padding(end = 22.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo2),
                contentDescription = "Logo",
                modifier = Modifier.size(120.dp)
            )
            Text(
                text = "Welcome to",
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.SemiBold,
            )
        }
        Text(
            text = "Go Marina",
            color = Color.White,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

@Preview
@Composable
private fun WelcomePrev() {
    WelcomeHeader()
}
