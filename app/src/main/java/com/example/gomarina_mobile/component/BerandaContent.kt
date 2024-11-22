package com.example.gomarina_mobile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gomarina_mobile.R
import com.example.gomarina_mobile.ui.theme.GoMarina_MobileTheme
import com.example.gomarina_mobile.ui.theme.poppinsFamily

@Composable
fun BerandaContent (modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ){
        Column(
            Modifier
                .padding(18.dp)
        ) {
            Surface(
                modifier = Modifier
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.jambu),
                    contentDescription = "Buah",
                    modifier = Modifier
                        .aspectRatio(9f / 9f),
                    contentScale = ContentScale.Crop,
                )
            }
            Row {
                Column {
                    Text(
                        text = "Jambu",
                        fontFamily = poppinsFamily,
                        fontSize = 30.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Rp10.000/kg",
                        fontFamily = poppinsFamily,
                        fontSize = 30.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun BerandaContentPrev() {
    GoMarina_MobileTheme{
        BerandaContent()
    }
}