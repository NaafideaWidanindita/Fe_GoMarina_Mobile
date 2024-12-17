package com.example.gomarina_mobile.component.Favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.component.Keranjang.KeranjangHeader
import com.example.gomarina_mobile.ui.theme.button

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopFavorite(
    navController: NavHostController,
    onSearch: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }

    // Wrap everything inside a Column
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // KeranjangHeader is placed at the top
        FavoriteHeader(navController)

        // Row for search and cart icon
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(button)
                .padding(vertical = 10.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
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
                    .padding(start = 8.dp)
                    .size(25.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = "Cart",
                    tint = Color.White,
                    modifier = Modifier.size(25.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopFavoritePreview() {
    val navController = rememberNavController()
    val navHostController = rememberNavController() // Create another NavController for the preview
    TopFavorite(
        navController = navController,
        onSearch = { query -> }
    )
}

//package com.example.gomarina_mobile.component.Favorite
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.BasicTextField
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material.icons.filled.Search
//import androidx.compose.material.icons.outlined.ShoppingCart
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import com.example.gomarina_mobile.ui.theme.bg_button
//import com.example.gomarina_mobile.ui.theme.button
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun TopFavorite(navController: NavController, onSearch: (String) -> Unit) {
//    var text by remember { mutableStateOf("") }
//
//        // The existing TopFavorite content below
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(button)
//                .padding(horizontal = 8.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            // Add the TopAppBar here
//            TopAppBar(
//                title = { Text("Favorite Saya", color = Color.Black) },
//                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
//                    containerColor = button // You can change the background color here
//                ),
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.Black)
//                    }
//                }
//            )
//
//            // Menambahkan Divider sebagai garis pemisah
//            Divider(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(1.dp) // Setting ketebalan garis
//                    .background(Color.White) // Menentukan warna garis
//            )
//
//            Spacer(modifier = Modifier.height(70.dp)) // Adjusting height for spacing under TopAppBar
//            Box(
//                modifier = Modifier
//                    .weight(1f)
//                    .background(Color.White, shape = RoundedCornerShape(10.dp))
//                    .height(42.dp)
//                    .padding(top = 12.dp, bottom = 12.dp, start = 16.dp, end = 8.dp)
//            ) {
//                BasicTextField(
//                    value = text,
//                    onValueChange = { query ->
//                        text = query
//                        onSearch(query)
//                    },
//                    textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
//                    singleLine = true,
//                    modifier = Modifier.fillMaxSize()
//                )
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    if (text.isEmpty()) {
//                        Text(
//                            text = "Search",
//                            color = Color.Gray,
//                            style = TextStyle(fontSize = 16.sp),
//                            modifier = Modifier.weight(1f)
//                        )
//                    }
//
//                    Spacer(modifier = Modifier.weight(1f))
//
//                    Icon(
//                        imageVector = Icons.Filled.Search,
//                        contentDescription = "Search"
//                    )
//                }
//            }
//
//            IconButton(
//                onClick = {
//                    navController.navigate("keranjang")
//                },
//                modifier = Modifier
//                    .padding(start = 8.dp)
//                    .size(25.dp)
//            ) {
//                Icon(
//                    imageVector = Icons.Outlined.ShoppingCart,
//                    contentDescription = "Cart",
//                    tint = Color.White,
//                    modifier = Modifier.size(25.dp)
//                )
//            }
//        }
//    }
//
//@Preview(showBackground = true)
//@Composable
//fun TopFavoritePreview() {
//    val navController = rememberNavController()
//    TopFavorite(
//        navController = navController,
//        onSearch = { query ->
//
//        }
//    )
//}
