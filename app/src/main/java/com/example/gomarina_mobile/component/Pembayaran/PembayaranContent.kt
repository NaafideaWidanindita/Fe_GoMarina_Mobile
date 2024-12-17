package com.example.gomarina_mobile.component.Pembayaran

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.ui.theme.bg_card_pesan
import com.example.gomarina_mobile.ui.theme.poppinsFamily
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.Button
import androidx.compose.ui.text.style.TextAlign
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.gomarina_mobile.R
import com.example.gomarina_mobile.component.Pembayaran.Camera.CameraActivity

@Composable
fun PembayaranContent() {
    Pembayaran()
}

@Composable
fun Pembayaran() {
    var selectedPaymentMethod by remember { mutableStateOf("Metode pembayaran (Bank Mandiri)") }
    val bankNumber = "09867363193981"
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    // URI gambar
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    // AlertDialog
    var showDialog by remember { mutableStateOf(false) }

    // Hasil kamera
    val imageCaptureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uriString = result.data?.getStringExtra("image_uri")
            uriString?.let {
                imageUri = Uri.parse(it)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Judul Pembayaran
        Row {
            Icon(
                imageVector = Icons.Default.Payments,
                contentDescription = "Payment Icon",
                tint = Color.Green,
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 8.dp)
            )
            Text(
                text = "Pembayaran",
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Konten Pembayaran
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(bg_card_pesan)
        ) {
            Column {
                // Dropdown untuk metode pembayaran
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = !expanded },
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = selectedPaymentMethod,
                                color = if (selectedPaymentMethod == "Metode pembayaran (Bank Mandiri)") Color.Gray else Color.Black,
                            )
                            Icon(
                                imageVector = if (expanded) Icons.Default.ArrowBack else Icons.Default.ArrowForward,
                                contentDescription = "Arrow Icon",
                                tint = Color.Black
                            )
                        }
                    }
                }

                // Nomor Rekening Bank jika expanded = true
                if (expanded) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = bankNumber,
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Box untuk upload bukti bayar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(Color.LightGray, RoundedCornerShape(8.dp))
                        .clickable {
                            val intent = Intent(context, CameraActivity::class.java)
                            imageCaptureLauncher.launch(intent)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    if (imageUri != null) {
                        Log.d("PaymentImage", "$imageUri")
                        // Menampilkan gambar jika sudah ada
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current).data(data = imageUri)
                                    .apply(block = fun ImageRequest.Builder.() {
                                        placeholder(R.drawable.jambu)
                                        error(R.drawable.jambu)
                                    }).build()
                            ),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize().padding(8.dp)
                        )
                    } else {
                        // Menampilkan ikon dan teks jika gambar belum ada
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.CameraAlt,
                                contentDescription = "Camera Icon",
                                tint = Color.Gray,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                text = "Upload Foto / Bukti Bayar",
                                color = Color.Gray,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }

        // Row untuk tombol Change dan View
        if (imageUri != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        val intent = Intent(context, CameraActivity::class.java)
                        imageCaptureLauncher.launch(intent)
                    },
                    modifier = Modifier.weight(1f).padding(end = 4.dp)
                ) {
                    Text("Change")
                }
                Button(
                    onClick = {
                        showDialog = true
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("View")
                }
            }
        }
    }

    // AlertDialog untuk menampilkan gambar
    if (showDialog && imageUri != null) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Bukti Pembayaran") },
            text = {
                Image(
                    painter = rememberAsyncImagePainter(imageUri),
                    contentDescription = null,
                    modifier = Modifier.size(300.dp)
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("Close")
                }
            }
        )
    }
}

@Composable
fun DropdownMenu(
    selectedOption: String,
    options: List<String>,
    expanded: Boolean,
    onOptionSelected: (String) -> Unit,
    onExpandedChange: (Boolean) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedOption,
                    color = if (selectedOption == "Metode pembayaran (Bank Mandiri)") Color.Gray else Color.Black
                )
                Icon(
                    imageVector = if (expanded) Icons.Default.ArrowBack else Icons.Default.ArrowForward, // Ikon berubah berdasarkan kondisi expanded
                    contentDescription = "Arrow Icon",
                    tint = Color.Black
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(Color.White)
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        },
                        modifier = Modifier.background(Color.White)
                    ) {
                        Text(
                            text = option,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun SummaryRow(label: String, amount: String, isTotal: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal
        )
        Text(
            text = amount,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PembayaranContentPreview() {
    val navController = rememberNavController()
    PembayaranContent()
}