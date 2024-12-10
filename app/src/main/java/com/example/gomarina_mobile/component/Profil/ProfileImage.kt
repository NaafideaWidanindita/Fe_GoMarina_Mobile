package com.example.gomarina_mobile.component.Profil

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gomarina_mobile.R

@Composable
fun ProfileImage(
    imageRes: Int = R.drawable.profile, // Default resource
    imageSize: Int = 80
) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = "Profile Image",
        modifier = Modifier
            .size(imageSize.dp)
            .clip(CircleShape)
    )
}

@Preview(showBackground = true)
@Composable
fun CustomProfileImagePreview() {
    ProfileImage( )
}
