package com.example.gomarina_mobile.component.Beranda

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ShoppingCart
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.ui.theme.button

@Composable
fun TopBar(navController: NavController, onSearch: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(button)
    ) {
        Row (
            modifier = Modifier
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Spacer(
                modifier = Modifier
                    .height(85.dp)
            )
            TextField(
                value = text,
                onValueChange = { query ->
                    text = query
                    onSearch(query) // Memanggil onSearch saat teks pencarian berubah
                },
                shape = RoundedCornerShape(percent = 20),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                placeholder = {
                    Text(text = "Search")
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                textStyle = TextStyle(fontSize = 16.sp),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search, contentDescription = "Search")
                }
            )
            Surface(
                modifier = Modifier
                    .size(50.dp)
                    .shadow(5.dp, shape = CircleShape)
                    .padding(start = 5.dp),
                shape = CircleShape,
                shadowElevation = 10.dp,
                color = Color.White
            ) {
                IconButton(
                    onClick = {
                        navController.navigate("keranjang")
                    },
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ShoppingCart,
                        contentDescription = "Cart",
                    )
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun TopBarPreview() {
//    val navController = rememberNavController()
//    TopBar(navController = navController)
//}