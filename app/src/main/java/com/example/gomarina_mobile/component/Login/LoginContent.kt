package com.example.gomarina_mobile.component.Login

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

@Composable
fun LoginBox(navController: NavController) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .width(300.dp),
        shape = RoundedCornerShape(percent = 6),
        border = BorderStroke(3.dp, color = SecondaryColor),
        color = Color.White,
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Login",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier.clickable { navController.navigate("register") }
            ) {
                Text(
                    text = "Belum punya akun? ",
                    color = Color.Black
                )
                Text(
                    text = "Sign Up",
                    fontSize = 16.sp,
                    color = Color.Red,
                    style = TextStyle(textDecoration = TextDecoration.Underline),
                    modifier = Modifier.clickable { navController.navigate("register") },
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Username
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp, horizontal = 12.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = bacground,
                    focusedContainerColor = bacground,
                    unfocusedLabelColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black
                ),
            )

            // Password
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp, horizontal = 12.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = bacground,
                    focusedContainerColor = bacground,
                    unfocusedLabelColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black
                ),
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
            )

            // Login Button
            Button(
                onClick = {
                    if (username.isNotEmpty() && password.isNotEmpty()) {
                        isLoading = true
                        loginUser(
                            username = username,
                            password = password,
                            context = context,
                            navController = navController,
                            onSuccess = {
                                isLoading = false
                            },
                            onError = { error ->
                                isLoading = false
                                errorMessage = error
                            }
                        )
                    } else {
                        errorMessage = "Username dan Password tidak boleh kosong"
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
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text(
                        text = "Login",
                        modifier = Modifier.padding(horizontal = 26.dp, vertical = 6.dp),
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                }
            }
            // Tampilan eror
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    textAlign = TextAlign.Center,
                    color = Color.Red,
                    style = TextStyle(fontSize = 12.sp, fontFamily = poppinsFamily, fontWeight = FontWeight.Medium),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
    }
}

fun loginUser(
    username: String,
    password: String,
    context: Context,
    navController: NavController,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    val client = OkHttpClient()
    val jsonObject = JSONObject()
    jsonObject.put("username", username)
    jsonObject.put("password", password)

    val requestBody = jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())
    val request = Request.Builder()
        .url("http://10.0.2.2:5000/api/v1/signin")
        .post(requestBody)
        .build()

    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = client.newCall(request).execute()
            val message = response.body?.string()

            if (response.isSuccessful && message != null) {
                val jsonResponse = JSONObject(message)

                Log.d("Respon :","$jsonResponse")
                // Ambil data user yg login
                val userObject = jsonResponse.getJSONObject("user")
                val userId = userObject.getInt("id")
                val name = userObject.getString("name")
                val userUsername = userObject.getString("username")
                val userRole = userObject.getString("role")
                val userTelp = userObject.getString("telp")
                val userPassword = userObject.getString("password")




                // Simpan data ke SharedPreferences
                val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putInt("id", userId)
                editor.putString("name",name)
                editor.putString("username", userUsername)
                editor.putString("role", userRole)
                editor.putString("telp", userTelp)
                editor.putString("password", userPassword)
                editor.apply()

                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                    onSuccess()
                    navController.navigate("beranda")
                }
            } else {
                CoroutineScope(Dispatchers.Main).launch {
                    val errorMessage = if (message != null) {
                        val jsonResponse = JSONObject(message)
                        jsonResponse.optString("message", "Unknown Error")
                    } else {
                        "Unknown Error"
                    }
                    onError(errorMessage)
                    Toast.makeText(context, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            CoroutineScope(Dispatchers.Main).launch {
                onError("Login Failed: ${e.message}")
                Toast.makeText(context, "Login Failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginBox() {
    val navController = rememberNavController()
    LoginBox(navController = navController)
}

