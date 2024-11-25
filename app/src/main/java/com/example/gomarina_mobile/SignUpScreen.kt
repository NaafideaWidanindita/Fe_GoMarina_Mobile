package com.example.gomarina_mobile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gomarina_mobile.component.LoginBox
import com.example.gomarina_mobile.component.SignBox
import com.example.gomarina_mobile.component.WelcomeHeader
import com.example.gomarina_mobile.ui.theme.PrimaryColor
import com.example.gomarina_mobile.ui.theme.SecondaryColor
import com.example.gomarina_mobile.ui.theme.poppinsFamily
import com.example.gomarina_mobile.ui.theme.warnaBg


@Composable
fun SignUpScreen() {
    var username by remember { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.newlogo),
            contentDescription = "Logo",
            modifier = Modifier.size(180.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        SignBox(
            username = username,
            password = password,
            onUsernameChange = { username = it },
            onPasswordChange = { password = it },
            onLoginClick = { /* Aksi Login */ }
        )
    }
}
@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun PreviewSignUpScreen() {
    SignUpScreen()
}
