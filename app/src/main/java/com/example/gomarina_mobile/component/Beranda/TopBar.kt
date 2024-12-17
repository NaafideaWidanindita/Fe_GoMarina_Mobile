package com.example.gomarina_mobile.component.Beranda

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            .padding(vertical = 10.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .height(70.dp)
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .background(Color.White, shape = RoundedCornerShape(10.dp))
                .height(42.dp)
                .padding(top = 12.dp, bottom = 12.dp, start = 16.dp, end = 8.dp)
        ) {
            BasicTextField(
                value = text,
                onValueChange = { query ->
                    text = query
                    onSearch(query)
                },
                textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                singleLine = true,
                modifier = Modifier.fillMaxSize()
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (text.isEmpty()) {
                    Text(
                        text = "Search",
                        color = Color.Gray,
                        style = TextStyle(fontSize = 16.sp),
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search"
                )
            }

        }
        IconButton(
            onClick = {
                navController.navigate("keranjang")
            },
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .size(25.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.ShoppingCart,
                contentDescription = "Cart",
                tint = Color.White,
                modifier = Modifier.size(25.dp)
            )
        }
        IconButton(
            onClick = {
                navController.navigate("favorite")
            },
            modifier = Modifier
                .padding(0.dp)
                .size(25.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = "Favorite",
                tint = Color.White,
                modifier = Modifier
                      // Pastikan ikon mengisi IconButton sepenuhnya
                    .padding(0.dp)
            )
        }
    }
}

//@Composable
//fun TopBar(navController: NavController, onSearch: (String) -> Unit) {
//    var text by remember { mutableStateOf("") }
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(button)
//            .padding(horizontal = 8.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Spacer(
//            modifier = Modifier
//                .height(70.dp)
//        )
//        TextField(
//            value = text,
//            onValueChange = { query ->
//                text = query
//                onSearch(query) // Memanggil onSearch saat teks pencarian berubah
//            },
//            shape = RoundedCornerShape(percent = 20),
//            modifier = Modifier
//                .height(40.dp),
//            placeholder = {
//                Text(text = "Search")
//            },
//            colors = TextFieldDefaults.colors(
//                focusedIndicatorColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Transparent,
//                focusedContainerColor = Color.White,
//                unfocusedContainerColor = Color.White
//            ),
//            trailingIcon = {
//                Icon(
//                    imageVector = Icons.Filled.Search, contentDescription = "Search")
//            }
//        )
//        IconButton(
//            onClick = {
//                navController.navigate("keranjang")
//            },
//        ) {
//            Icon(
//                imageVector = Icons.Outlined.ShoppingCart,
//                contentDescription = "Cart",
//                tint = Color.White,
//                modifier = Modifier.size(32.dp)
//            )
//        }
//        IconButton(
//            onClick = {
//                navController.navigate("keranjang")
//            },
//        ) {
//            Icon(
//                imageVector = Icons.Outlined.FavoriteBorder,
//                contentDescription = "Cart",
//                tint = Color.White,
//                modifier = Modifier.size(32.dp)
//            )
//        }
//    }
//}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    val navController = rememberNavController()
    TopBar(
        navController = navController,
        onSearch = { query ->

        }
    )
}
