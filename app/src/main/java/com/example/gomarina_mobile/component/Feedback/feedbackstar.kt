package com.example.gomarina_mobile.component.Feedback

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gomarina_mobile.R
import com.example.gomarina_mobile.ui.theme.yellowStar

@Composable
fun StarRating(rating: Float, onRatingChanged: (Float) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        (0..4).forEach { index ->
            IconButton(onClick = { onRatingChanged((index + 1).toFloat()) }) {
                Icon(
                    painter = painterResource(
                        id = if (index < rating) R.drawable.ic_star_filled else R.drawable.ic_star
                    ),
                    contentDescription = "Star Rating",
                    tint = yellowStar,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StarRatingPreview() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "")
        StarRating(rating = 3f, onRatingChanged = {})
    }
}
