package com.example.gomarina_mobile.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gomarina_mobile.ui.theme.SecondaryColor
import com.example.gomarina_mobile.ui.theme.poppinsFamily

@Composable
fun RiwayatPesananItem(){
    Surface(
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 6.dp,
        color = Color.White,
        modifier = Modifier
            .padding(16.dp)
            .height(170.dp)
            .fillMaxWidth(),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(18.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Jambu",
                        fontFamily = poppinsFamily,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                    )
                    Text(
                        text = "Keripik Jambu",
                        fontFamily = poppinsFamily,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                    )
                    Text(
                        text = "Jus Jambu",
                        fontFamily = poppinsFamily,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                    )
                }

                Row {
                    Text(
                        text = "Total",
                        fontFamily = poppinsFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = SecondaryColor,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .padding(end = 8.dp)
                    )
                    Text(
                        text = "Rp100.000",
                        fontFamily = poppinsFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )
                }
            }
            Text(
                text = "20/11/2026",
                fontFamily = poppinsFamily,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview
@Composable
private fun RiwayatItemPrev() {
    RiwayatPesananItem()
}