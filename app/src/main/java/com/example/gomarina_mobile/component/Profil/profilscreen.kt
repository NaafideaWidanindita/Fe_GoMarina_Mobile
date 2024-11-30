package com.example.gomarina_mobile.component.Profil

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gomarina_mobile.R
import com.example.gomarina_mobile.ui.theme.button


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
@Preview(showBackground = true)
fun ProfileContentPreview() {
    ProfileContent(paddingValues = PaddingValues(16.dp))
}

