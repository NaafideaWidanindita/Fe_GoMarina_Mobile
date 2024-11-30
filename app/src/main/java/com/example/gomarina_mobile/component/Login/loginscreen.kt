package com.example.gomarina_mobile.component.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.Snackbar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gomarina_mobile.dummyData.dummyUsers
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.R
import com.example.gomarina_mobile.ui.theme.PrimaryColor

@Composable
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var loginError by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }

    fun handleLogin() {
        val user = dummyUsers.find { it.username == username && it.password == password }
        loginError = user == null

        if (!loginError) {
            // Navigate to the "beranda" screen after a successful login
            navController.navigate("beranda")
        }
    }

    LaunchedEffect(loginError) {
        if (loginError) {
            snackbarHostState.showSnackbar("Username or password is incorrect")
        } else {
            snackbarHostState.showSnackbar("Login successful")
        }
    }

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

        LoginBox(
            username = username,
            password = password,
            onUsernameChange = { username = it },
            onPasswordChange = { password = it },
            onLoginClick = { navController.navigate("beranda") },
            navController = navController
        )



        if (loginError) {
            Snackbar(
                modifier = Modifier.padding(8.dp),
                containerColor = Color.Red,
                contentColor = Color.White,
                actionContentColor = Color.White
            ) {
                Text(text = "Incorrect username or password", fontSize = 14.sp)
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(navController = rememberNavController())
}