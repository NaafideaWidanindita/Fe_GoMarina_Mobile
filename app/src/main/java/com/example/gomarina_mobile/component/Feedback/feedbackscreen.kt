package com.example.gomarina_mobile.component.Feedback

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.R
import com.example.gomarina_mobile.ui.theme.*


@Composable
fun FeedbackScreen(navController: NavController) {
    var feedbackText by remember { mutableStateOf(TextFieldValue("")) }
    var rating by remember { mutableStateOf(0f) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .background(warnab),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp)
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = "Kembali"
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Feedback", fontSize = 29.sp, color = textColorPrimary)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Berikan penilaian anda", fontSize = 18.sp, color = textColorPrimary)

        Spacer(modifier = Modifier.height(20.dp))
        StarRating(rating = rating, onRatingChanged = { rating = it })
        Spacer(modifier = Modifier.height(20.dp))
        BasicTextField(
            value = feedbackText,
            onValueChange = { feedbackText = it },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(150.dp)
                .padding(horizontal = 30.dp, vertical = 0.dp)
                .background(Kotak, shape = RoundedCornerShape(8.dp))
                .padding(12.dp),
            decorationBox = { innerTextField ->
                if (feedbackText.text.isEmpty()) {
                    Text(text = "Ceritakan pengalaman anda", color = textColorPrimary)
                }
                innerTextField()
            }
        )
        Spacer(modifier = Modifier.height(200.dp))
        Button(
            onClick = {
                if (rating == 0f) {
                    Toast.makeText(context, "Harap pilih rating!", Toast.LENGTH_SHORT).show()
                } else if (feedbackText.text.isEmpty()) {
                    Toast.makeText(context, "Harap isi feedback!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Feedback terkirim! Terima kasih!", Toast.LENGTH_SHORT).show()
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = button),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(bottom = 60.dp)
        ) {
            Text(text = "Kirim", color = Color.White)
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}



@Preview(showBackground = true)
@Composable
fun FeedbackScreenPreview() {
    val navController = rememberNavController()
    FeedbackScreen(navController)
}
