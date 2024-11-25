package com.example.gomarina_mobile.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gomarina_mobile.ui.theme.poppinsFamily

@Composable
fun SignBox(
    username: String,
    password: String,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit
) {
    Surface(
        modifier = Modifier.width(350.dp),
        shape = RoundedCornerShape(percent = 6),
        border = BorderStroke(2.dp, Color.White),
        shadowElevation = 5.dp,
        color = Color.White.copy(alpha = 0.7f),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sign Up",
                color = Color.Black,
                fontSize = 30.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Bold,
            )
            Row(
                modifier = Modifier.padding(top = 6.dp),
            ) {
                Text(
                    text = "Sudah punya akun? ",
                    fontFamily = poppinsFamily,
                    fontSize = 16.sp
                )
                Text(
                    modifier = Modifier.clickable { /* Navigasi ke halaman daftar */ },
                    text = "Masuk",
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Column(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Username",
                    fontSize = 18.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Bold,
                )
                TextField(
                    value = username,
                    onValueChange = onUsernameChange,
                    shape = RoundedCornerShape(percent = 30),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    textStyle = TextStyle(fontSize = 18.sp),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Password",
                    fontSize = 18.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Bold,
                )
                TextField(
                    value = password,
                    onValueChange = onPasswordChange,
                    shape = RoundedCornerShape(percent = 30),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    textStyle = TextStyle(fontSize = 30.sp),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
                )
                Spacer(modifier = Modifier.height(26.dp))
            }
            Button(
                onClick = onLoginClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2B4944)),
                shape = RoundedCornerShape(percent = 30)
            ) {
                Text(
                    text = "SignUp",
                    modifier = Modifier.padding(horizontal = 26.dp, vertical = 6.dp),
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(26.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSignUpBox() {
    SignBox(
        username = "",
        password = "",
        onUsernameChange = {},
        onPasswordChange = {},
        onLoginClick = {}
    )
}
