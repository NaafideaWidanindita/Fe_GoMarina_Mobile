package com.example.gomarina_mobile.component.Beranda

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gomarina_mobile.R
import com.example.gomarina_mobile.model.Produk
import com.example.gomarina_mobile.ui.theme.poppinsFamily
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

@Composable
fun BerandaContent (
    modifier: Modifier = Modifier,
    produk: Produk,
    onItemClick:(Int) -> Unit,
    
) {
    val formattedPrice = NumberFormat.getNumberInstance(Locale("id", "ID")).format(produk.price)

    Surface(
        modifier = modifier
            .padding(16.dp)
            .width(200.dp)
            .height(220.dp),
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 4.dp,
        color = Color.White
    ){
        Column(
            Modifier
                .padding(10.dp)
        ) {
            Image(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .clickable { onItemClick(produk.id) }, // Navigasi menggunakan produk ID
                painter = painterResource(id = produk.image),
                contentDescription = produk.name,
                contentScale = ContentScale.Crop,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = produk.name,
                        fontFamily = poppinsFamily,
                        fontSize = 13.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Rp$formattedPrice/kg",
                        fontFamily = poppinsFamily,
                        fontSize = 12.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium
                    )
                }
                Icon(
                    Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {  }
                )
            }
        }
    }
}

@Preview
@Composable
private fun BerandaContentPrev() {
        BerandaContent(
            produk = Produk(1,"Jambu",
                R.drawable.buahsatu,"Buah Jambu Yang Manis", BigDecimal("32000"),100),
            onItemClick ={produkId ->
                println("Produk id: $produkId")
            }
        )
    }