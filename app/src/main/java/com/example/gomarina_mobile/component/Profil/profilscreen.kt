package com.example.gomarina_mobile.component.Profil

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

@Composable
fun ProfileContent(context: Context) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var telp by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var successMessage by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    val id = sharedPreferences.getInt("id", -1)

    LaunchedEffect(Unit) {
        username = sharedPreferences.getString("username", "") ?: ""
        password = sharedPreferences.getString("password", "") ?: ""
        telp = sharedPreferences.getString("telp", "") ?: ""
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Update Profile",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Username Input Field
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                focusedLabelColor = MaterialTheme.colorScheme.primary
            )
        )

        // Password Input Field with visibility toggle
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        imageVector = if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = "Toggle Password Visibility"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                focusedLabelColor = MaterialTheme.colorScheme.primary
            )
        )

        // Phone Number Input Field
        TextField(
            value = telp,
            onValueChange = { telp = it },
            label = { Text("Phone Number") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                focusedLabelColor = MaterialTheme.colorScheme.primary
            )
        )

        // Update Button
        Button(
            onClick = {
                if (id != -1) {
                    isLoading = true
                    val currentUsername = sharedPreferences.getString("username", "") ?: ""
                    val currentPassword = sharedPreferences.getString("password", "") ?: ""
                    val currentTelp = sharedPreferences.getString("telp", "") ?: ""

                    val updatedData = mutableMapOf<String, String>()
                    if (username.isNotEmpty() && username != currentUsername) updatedData["username"] = username
                    if (password.isNotEmpty() && password != currentPassword) updatedData["password"] = password
                    if (telp.isNotEmpty() && telp != currentTelp) updatedData["telp"] = telp

                    if (updatedData.isNotEmpty()) {
                        updateUserData(
                            id = id,
                            data = updatedData,
                            onSuccess = {
                                isLoading = false
                                successMessage = "Data berhasil diperbarui"
                                val editor = sharedPreferences.edit()
                                updatedData.forEach { (key, value) -> editor.putString(key, value) }
                                editor.apply()
                            },
                            onError = { error ->
                                isLoading = false
                                errorMessage = error
                            }
                        )
                    } else {
                        isLoading = false
                        errorMessage = "Tidak ada perubahan yang dilakukan"
                    }
                } else {
                    errorMessage = "ID pengguna tidak valid"
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .padding(bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(text = if (isLoading) "Updating..." else "Update", color = Color.White)
        }

        // Processing Indicator
        if (isLoading) {
            Spacer(modifier = Modifier.height(16.dp))
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }

        // Success Message
        if (successMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = successMessage, color = Color.Green)
        }

        // Error Message
        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = errorMessage, color = Color.Red)
        }
    }
}

// Update user data function
fun updateUserData(
    id: Int,
    data: Map<String, String>,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    val client = OkHttpClient()
    val jsonObject = JSONObject(data)

    val requestBody = jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())
    val request = Request.Builder()
        .url("http://10.0.2.2:5000/api/v1/updateUser/$id") // Pastikan endpoint API benar
        .put(requestBody)
        .build()

    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                onSuccess()
            } else {
                onError("Gagal memperbarui data: ${response.code} - ${response.message}")
            }
        } catch (e: IOException) {
            onError("Kesalahan jaringan: ${e.message}")
        } catch (e: Exception) {
            onError("Terjadi kesalahan: ${e.message}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProfileContent(context = LocalContext.current)
}
