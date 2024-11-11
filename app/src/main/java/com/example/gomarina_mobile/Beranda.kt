package com.example.gomarina_mobile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.mutableStateOf
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.WidthNormal
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gomarina_mobile.ui.theme.warnaSec
import androidx.compose.material3.TopAppBar as Top


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Beranda (){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )

    val navItems = listOf(
        NavBottom("Home", Icons.Default.Home),
        NavBottom("Pesanan", Icons.Default.WidthNormal),
        NavBottom("Pengaturan", Icons.Default.Settings)
    )

    var selectedItem by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopSearchBar(
                modifier = Modifier
                    .clip(RoundedCornerShape(percent = 50))
                    .background(Color(0xFFF0F7F6)),
                scrollBehavior = scrollBehavior)},
        bottomBar = {
            NavigationBar (
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .shadow(elevation = 50.dp)
                    .background(Color(0xFFF0F7F6)),

                ){
                navItems.forEachIndexed() { index, NavBottom ->
                    NavigationBarItem(
                        modifier = Modifier
                            .padding(bottom = 10.dp),
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                        icon = {
                            Icon(imageVector = NavBottom.Icon, contentDescription = "Icon")
                        },
                        label = {
                            Text(text = NavBottom.label)
                        }
                    )
                }
            }
        }
    )
    {
//        innerPadding ->
//        HomeContent(modifier = Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopSearchBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior
) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 26.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .height(100.dp)
        )
        TextField(
            value = text,
            onValueChange = { text = it },
            shape = RoundedCornerShape(percent = 50),
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            placeholder = {
                Text(text = "Search")
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            textStyle = TextStyle(fontSize = 16.sp),
            trailingIcon = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            }
        )
        Surface(
            modifier = Modifier
                .size(50.dp)
                .padding(start = 5.dp),
            color = warnaSec,
            shape = CircleShape
        ) {
            IconButton(
                onClick = {
                    println("Cart icon clicked")
                },
                modifier = Modifier

            ) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = "Cart",
                )
            }
        }
    }
}

//@Composable
//fun HomeContent(
//    modifier: Modifier = Modifier
//) {
//    ElevatedCard(
//        elevation = CardDefaults.cardElevation(
//            defaultElevation = 6.dp
//        ),
//        modifier = Modifier
//            .size(width = 240.dp, height = 100.dp)
//    ) {
//        Text(
//            text = "Elevated",
//            modifier = Modifier
//                .padding(16.dp),
//            textAlign = TextAlign.Center,
//        )
//    }
//
//
//}

@Preview (showBackground = true)
@Composable
fun BerandaPreview() {
    Beranda()
}