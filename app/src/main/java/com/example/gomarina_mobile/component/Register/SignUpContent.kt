package com.example.gomarina_mobile.component.Register

import android.annotation.SuppressLint
import android.content.Context
import android.os.CountDownTimer
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.ui.theme.SecondaryColor
import com.example.gomarina_mobile.ui.theme.bacground
import com.example.gomarina_mobile.ui.theme.button
import com.example.gomarina_mobile.ui.theme.poppinsFamily
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import androidx.compose.material3.CircularProgressIndicator as CircularProgressIndicator1

@Composable
fun SignBox(navController: NavController) {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var telp by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var resultMessage by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var telpError by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .width(300.dp),
        shape = RoundedCornerShape(percent = 6),
        border = BorderStroke(3.dp, color = SecondaryColor),
        color = Color.White,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Sign Up",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier.clickable { navController.navigate("login") }
            ) {
                Text(
                    text = "Sudah punya akun? ",
                    color = Color.Black
                )
                Text(
                    text = "Masuk",
                    fontSize = 16.sp,
                    color = Color.Blue,
                    style = TextStyle(textDecoration = TextDecoration.Underline),
                    modifier = Modifier.clickable { navController.navigate("login") },
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Nama
            TextField(
                value = name,
                onValueChange = {
                    // hanya huruf
                    val regex = "^[a-zA-Z\\s]*$"
                    if (it.matches(regex.toRegex()) || it.isEmpty()) {
                        name = it
                        nameError = ""
                    } else {
                        nameError = "Nama hanya boleh huruf"
                    }
                },
                label = { Text("Nama") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp, horizontal = 12.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = bacground,
                    focusedContainerColor = bacground,
                    unfocusedLabelColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black
                )
            )
            if (nameError.isNotEmpty()) {
                Text(text = nameError, color = Color.Red, style = TextStyle(fontSize = 12.sp))
            }

            // Nomor Telepon
            TextField(
                value = telp,
                onValueChange = {
                    if (it.all{char -> char.isDigit()}){
                        telp = it
                        telpError = if (it.length in 12..13)"" else "Nomor hanya boleh 12-13 digit"
                    }
                },
                label = { Text("Nomor Telepon") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp, horizontal = 12.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                isError = telpError.isNotEmpty(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = bacground,
                    focusedContainerColor = bacground,
                    unfocusedLabelColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black
                )
            )
            if (telpError.isNotEmpty()) {
                Text(text = telpError, color = Color.Red, style = TextStyle(fontSize = 12.sp))
            }

            // Username
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp, horizontal = 12.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = bacground,
                    focusedContainerColor = bacground,
                    unfocusedLabelColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black
                )
            )

            // Password
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp, horizontal = 12.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { showPassword = !showPassword }) {
                        Icon(
                            imageVector = if (showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = if (showPassword) "Hide Password" else "Show Password"
                        )
                    }
                },

                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = bacground,
                    focusedContainerColor = bacground,
                    unfocusedLabelColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black
                )
            )

            // Button Sign Up
            var countdownValue by remember { mutableStateOf(3) }
            var isCountingDown by remember { mutableStateOf(false) }
            Button(
                onClick = {
                    if (name.isNotEmpty() && telp.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty() && telpError.isEmpty()) {
                        isLoading = true
                        registerUser(name, telp, username, password, context) { success, message ->
                            isLoading = false
                            resultMessage = message
                            Toast.makeText(context, resultMessage, Toast.LENGTH_SHORT).show()
                            if (success) {
                                resultMessage = "Registrasi Anda diterima, silakan login dalam $countdownValue detik"
                                isCountingDown = true

                                // Timer 3-1 detik
                                object : CountDownTimer(3000, 1000) {
                                    override fun onTick(millisUntilFinished: Long) {
                                        countdownValue = (millisUntilFinished / 1000).toInt()
                                        resultMessage = "Dialihkan ke halaman login dalam $countdownValue detik"
                                    }

                                    override fun onFinish() {
                                        isCountingDown = false
                                        countdownValue = 1
                                        navController.navigate("login") {
                                            popUpTo("register") { inclusive = true }
                                        }
                                    }
                                }.start()
                            }
                        }
                    } else {
                        resultMessage = "Semua data wajib terisi!"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 12.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = button,
                    contentColor = Color.White
                )
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp))
                } else {
                    Text(text = "Daftar", fontSize = 16.sp, modifier = Modifier.padding(8.dp))
                }
            }
            if (resultMessage.isNotEmpty()) {
                Text(
                    text = resultMessage,
                    textAlign = TextAlign.Center,
                    color = if (resultMessage == "Semua data wajib terisi!") Color.Red else Color.Green, // Menyesuaikan warna pesan
                    style = TextStyle(fontSize = 12.sp, fontFamily = poppinsFamily, fontWeight = FontWeight.Medium),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(50.dp))
}

@SuppressLint("CoroutineCreationDuringComposition")
fun registerUser(
    name: String,
    telp: String,
    username: String,
    password: String,
    context: Context,
    onResult: (Boolean, String) -> Unit
) {
    val client = OkHttpClient()
    val jsonObject = JSONObject().apply {
        put("name", name)
        put("telp", telp)
        put("username", username)
        put("password", password)
    }

    val requestBody = jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())
    val request = Request.Builder()
        .url("http://10.0.2.2:5000/api/v1/signup")
        .post(requestBody)
        .build()

    val scope = CoroutineScope(Dispatchers.IO)
    scope.launch {
        try {
            val response = client.newCall(request).execute()
            val message = response.body?.string()
            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    onResult(true, "Registration Successful")
                }
            } else {
                withContext(Dispatchers.Main) {
                    onResult(false, "Error: $message")
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                onResult(false, "Registration Failed: ${e.message}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUpBox() {
    val navController = rememberNavController()
    SignBox(navController = navController)
}
