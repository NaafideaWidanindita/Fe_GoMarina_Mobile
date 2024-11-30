package com.example.gomarina_mobile.component.Pembayaran

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.gomarina_mobile.R
import com.example.gomarina_mobile.dummyData.DummyData
import com.example.gomarina_mobile.ui.theme.bacground
import com.example.gomarina_mobile.ui.theme.bg_button
import com.example.gomarina_mobile.ui.theme.bg_card_pesan
import com.example.gomarina_mobile.ui.theme.button
import com.example.gomarina_mobile.ui.theme.poppinsFamily


@Composable
fun PembayaranContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bacground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 180.dp)
        ) {
            AlamatFix()
            Pembayaran()
        }

        Box (modifier = Modifier
            .align(Alignment.BottomCenter))
        {
            ButtonPembayaran()
        }
    }
}

@Composable
fun AlamatFix() {
    // State untuk alamat
    val address = DummyData.dataAddress.firstOrNull()
    val currentName by remember { mutableStateOf("Naafi'dea") }
    val currentAddress by remember { mutableStateOf(address?.let { "${it.street}, ${it.kecamatan}, ${it.city}, ${it.provinsi}, ${it.kode_pos}" } ?: "") }
    val currentDetail by remember { mutableStateOf(address?.detail ?: "") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = "Location Icon",
            tint = Color.Red,
            modifier = Modifier
                .size(40.dp)
                .padding(end = 8.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Alamat",
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 5.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Nama: $currentName",
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Alamat: $currentAddress",
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Detail: $currentDetail",
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }
        }
    }
    GarisBatas()
}
@Composable
fun Pembayaran() {
    var selectedPaymentMethod by remember { mutableStateOf("Pilih metode pembayaran") }
    val options = listOf("Nomor Rekening Bank Mandiri", "QRIS")
    val bankNumber = "09867363193981"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "com/example/gomarina_mobile/component/Pembayaran",
            color = Color.Black,
            fontSize = 20.sp,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(bg_card_pesan)
                .padding(16.dp)
        ) {
            Column {
                // Dropdown pilih pembayaran
                DropdownMenu(selectedOption = selectedPaymentMethod, options = options) { selected ->
                    selectedPaymentMethod = selected
                }

                Spacer(modifier = Modifier.height(8.dp))

                // pilihan mandiri/Qris
                if (selectedPaymentMethod == "Nomor Rekening Bank Mandiri") {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize() // Agar Box memenuhi ruang
                    ) {
                        Text(
                            text = bankNumber,
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                } else if (selectedPaymentMethod == "QRIS") {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize() // Agar Box memenuhi ruang
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.jambu), // Gambar QRIS
                            contentDescription = "QRIS",
                            modifier = Modifier.size(100.dp)
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
                        .clickable { /* Tambahkan fungsi upload foto */ },
                    //.clickable {
                    ////    // Memanggil fungsi untuk membuka kamera
                    ////           openCameraForPhoto {
                    ////           uri -> imageUri
                    ////       }
                    //  },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Upload Foto / Bukti Bayar",
                        color = Color.Gray,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
//                val context = LocalContext.current
//
////                // URI untuk file foto yang akan disimpan
////                val imageUri: Uri = FileProvider.getUriForFile(
////                    context,
////                    "${context.packageName}.provider", // nama provider sesuai manifest
////                    File(context.filesDir, "my_image.jpg") // Menyimpan foto di folder internal aplikasi
////                )
//
//                // Menampilkan foto yang diambil jika ada
//                imageUri?.let {
//                    Image(painter = rememberImagePainter(it), contentDescription = "Foto Bukti Bayar")
//                }
            }
        }
    }
}

//// coba buka ambil foto kamerra
//const val REQUEST_IMAGE_CAPTURE = 1
//
//@SuppressLint("QueryPermissionsNeeded")
//@Composable
//fun OpenCameraForPhoto(onPhotoTaken: (Uri?) -> Unit) {
//    val context = LocalContext.current
//    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//
//    // Cek apakah aplikasi kamera tersedia
//    if (intent.resolveActivity(context.packageManager) != null) {
//        val photoFile: File? = try {
//            createImageFile(context)
//        } catch (ex: IOException) {
//            null
//        }
//
//        photoFile?.also {
//            val photoUri: Uri = FileProvider.getUriForFile(
//                context,
//                "${context.packageName}.provider",
//                it
//            )
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
//            // Jalankan intent untuk mengambil foto
//            (context as Activity).startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
//            onPhotoTaken(photoUri) // Menyediakan URI foto yang diambil
//        }
//    }
//}
//
//fun createImageFile(context: Context): File {
//    val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
//    val storageDir: File = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES) ?: context.filesDir
//    return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
//}

@Composable
fun DropdownMenu(
    selectedOption: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit
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
                    color = if (selectedOption == "Pilih metode pembayaran") Color.Gray else Color.Black
                )
                Text(
                    text = if (expanded) "v" else ">",
                    color = Color.Black
                )
            }

            // DropdownMenu dengan custom warna
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
                            color = Color.Black // Pastikan warna teks tetap hitam
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

@Composable
fun ButtonPembayaran() {
    Box(
        modifier = Modifier
            .fillMaxWidth() // Tombol memenuhi lebar layar
            .navigationBarsPadding() // Padding untuk menghindari navbar
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
                .navigationBarsPadding(),
            shape = RoundedCornerShape(
                topStart = 24.dp,
                topEnd = 24.dp
            ),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF4CC543))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(bg_button)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SummaryRow(label = "Ongkos kirim", amount = "Rp5.000")
                SummaryRow(label = "Subtotal", amount = "Rp32.000")
                SummaryRow(label = "Total", amount = "Rp37.000")

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {  },
                    colors = ButtonDefaults.buttonColors(button),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Pesan",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PembayaranContentPreview() {
    val navController = rememberNavController()
    PembayaranContent()
}
