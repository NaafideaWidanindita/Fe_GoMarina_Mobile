package com.example.gomarina_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.R
import com.example.gomarina_mobile.ui.theme.button
import com.example.gomarina_mobile.ui.theme.profilwar
import com.example.gomarina_mobile.ui.theme.warnaSec

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreen() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            ProfileHeader()
        },
        bottomBar = {
            BottomNavigation(navController = navController)
        },
        content = { paddingValues ->

            NavHost(navController = navController, startDestination = "profile") {
                composable("profile") {
                    ProfileContent(paddingValues = paddingValues)
                }
                composable("Home") {

                }
                composable("Pesanan") {

                    AppNavigation()
                }
                composable("settings") {

                    SettingsScreen(navController)
                }
            }
        }
    )
}

@Composable
fun ProfileHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
            .background(profilwar)
            .padding(0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = { /* kode buat back */ }, modifier = Modifier.align(Alignment.Start)) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "Back",
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(50))
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "ARIFANDI",
            color = Color.White,
            style = MaterialTheme.typography.h5
        )
    }
}

@Composable
fun ProfileContent(paddingValues: PaddingValues) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(paddingValues)
    ) {
        TextFieldWithLabel(label = "Nama", value = name, onValueChange = { name = it })
        TextFieldWithLabel(label = "Email", value = email, onValueChange = { email = it })
        TextFieldWithLabel(
            label = "Password",
            value = password,
            onValueChange = { password = it },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            togglePasswordVisibility = { passwordVisible = !passwordVisible }
        )
        TextFieldWithLabel(label = "No. Telp", value = phoneNumber, onValueChange = { phoneNumber = it })

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = { /*kode */ },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
            colors = ButtonDefaults.buttonColors(backgroundColor = button)
        ) {
            Text(text = "Update", color = Color.White)
        }

    }
}

@Composable
fun TextFieldWithLabel(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    togglePasswordVisibility: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = label, style = MaterialTheme.typography.body1)
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = visualTransformation,
            trailingIcon = {
                if (togglePasswordVisibility != null) {
                    val icon = if (visualTransformation == PasswordVisualTransformation()) {
                        painterResource(id = R.drawable.ic_visibility_off)
                    } else {
                        painterResource(id = R.drawable.ic_visibility)
                    }
                    IconButton(onClick = togglePasswordVisibility) {
                        Icon(painter = icon, contentDescription = "Toggle password visibility")
                    }
                }
            },
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent)
        )
    }
}

@Composable
fun BottomNavigation(navController: NavController) {
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
                Text("Beranda", color = Color.Gray)
            },
            selected = false,
            onClick = {
                navController.navigate("home")
            }
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
                Text("Pesanan", color = Color.Gray)
            },
            selected = true,
            onClick = {
                navController.navigate("Pesanan")
            }
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
                Text("Pengaturan", color = Color(0xFF00796B))
            },
            selected = false,
            onClick = {
                navController.navigate("settings")
            }
        )
    }
}
