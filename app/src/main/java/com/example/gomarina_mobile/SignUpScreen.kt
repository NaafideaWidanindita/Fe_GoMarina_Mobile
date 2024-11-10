package com.example.gomarina_mobile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gomarina_mobile.ui.theme.poppinsFamily
import com.example.gomarina_mobile.ui.theme.warnaBg

@Composable

fun SignUpScreen(){
    var username by remember { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = warnaBg),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .padding(start = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .padding(end = 22.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Bottom
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo2),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(120.dp)
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
                modifier = Modifier
                    .padding(bottom = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Surface (
            modifier = Modifier
                .width(350.dp),
            shape = RoundedCornerShape(percent = 6),
            border = BorderStroke(2.dp, Color.White),
            shadowElevation = 5.dp,
            color = Color.White.copy(alpha = 0.7f),
        ){

            Column(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .padding(start = 8.dp)
                    .padding(end = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "SIGN UP",
                    color = Color.Black,
                    fontSize = 30.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Bold,
                )
                Row(
                    modifier = Modifier
                        .padding(top = 6.dp),
                ) {
                    Text(
                        text = "Sudah Punya Akun? ",
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    )
                    Text(
                        modifier = Modifier
                            .clickable {},
                        text = "Login",
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Username",
                        fontSize = 18.sp,
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.Bold,
                    )
                    TextField(
                        modifier = Modifier
                            .height(53.dp),
                        value = username,
                        onValueChange = {username = it},
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
                        modifier = Modifier
                            .height(53.dp),
                        value = password,
                        onValueChange = {password = it},
                        shape = RoundedCornerShape(percent = 30),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                        textStyle = TextStyle(fontSize = 30.sp),
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                    Spacer(modifier = Modifier.height(26.dp))

                }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2B4944)),
                    shape = RoundedCornerShape(percent = 30)
                ) {
                    Text(text = "SIGN UP",
                        modifier = Modifier
                            .padding(horizontal = 26.dp)
                            .padding(vertical= 6.dp),
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )}

                Spacer(modifier = Modifier.height(26.dp))
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}