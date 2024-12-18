package com.example.gomarina_mobile.component.Pembayaran

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.gomarina_mobile.dummyData.DummyData
import com.example.gomarina_mobile.dummyData.DummyData.dataDelivery
import com.example.gomarina_mobile.model.Delivery
import com.example.gomarina_mobile.ui.theme.bacground
import com.example.gomarina_mobile.ui.theme.bg_card
import com.example.gomarina_mobile.ui.theme.bg_card_pesan
import com.example.gomarina_mobile.ui.theme.button
import com.example.gomarina_mobile.ui.theme.poppinsFamily
import androidx.compose.ui.unit.dp
import com.example.gomarina_mobile.model.Address
import com.example.gomarina_mobile.model.KeranjangItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

@Composable
fun PesananContent(selectedItems: String) {
    val scrollState = rememberScrollState()
    var totalALL by remember { mutableStateOf(0f) }

    Log.d("ItemDiPesananContent", "$selectedItems")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bacground)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
        ) {
            Alamat()
            DummyData.dataPesanan.forEach { item ->
                PesananItem(item = item)
            }
            DetailBayar(items = DummyData.dataPesanan, onTotalChanged = { total ->
                totalALL = total
            })
            PembayaranContent()
        }
    }
}

@Composable
fun Alamat() {
    var address by remember { mutableStateOf<List<Address>>(emptyList()) }
    var isEditDialogOpen by remember { mutableStateOf(false) }
    var currentAddress by remember { mutableStateOf("") }
    var provinsi by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var kecamatan by remember { mutableStateOf("") }
    var street by remember { mutableStateOf("") }
    var postalCode by remember { mutableStateOf("") }
    var currentDetail by remember { mutableStateOf("") }

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    val name = sharedPreferences.getString("name", "User")
    val telp = sharedPreferences.getString("telp", "08**********")

    LaunchedEffect(Unit) {
        fetchAddress(context) { addressList, message ->
            Log.d("ADRESS", "$addressList")
            addressList?.let {
                val firstAddress = it.firstOrNull()
                firstAddress?.let { addr ->
                    provinsi = addr.provinsi
                    city = addr.city
                    kecamatan = addr.kecamatan
                    street = addr.street
                    postalCode = addr.postalCode.toString()
                }
                address = it
                currentAddress = it.joinToString(", ") { addr ->
                    "${addr.provinsi}, ${addr.city}, ${addr.kecamatan}, ${addr.street}, ${addr.postalCode}"
                }
                currentDetail = it.firstOrNull()?.detail.orEmpty()
                Log.d("ALAMAT", "$currentAddress")
                Log.d("PROVINSI", provinsi)
                Log.d("CITY", city)
                Log.d("KECAMATAN", kecamatan)
                Log.d("STREET", street)
                Log.d("POSTAL CODE", postalCode)
                Log.d("Detail", "$currentDetail")
            }
        }
    }

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
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Alamat Pengiriman",
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 5.dp)
                )
                IconButton(
                    onClick = { isEditDialogOpen = true },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Address"
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column {
                // Baris nama dan telepon
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$name | $telp",
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Baris alamat
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = currentAddress.ifEmpty { "Alamat belum tersedia" },
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Baris detail
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "(${currentDetail.ifEmpty { "Detail tidak tersedia" }})",
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }
        }
    }

    GarisBatas()
    Spacer(modifier = Modifier.height(16.dp))

    // Edit Alamat
    if (isEditDialogOpen) {
        AlamatEditDialog(
            currentAddress = currentAddress,
            currentDetail = currentDetail,
            onDismiss = { isEditDialogOpen = false },
            onSave = { context, street, kecamatan, city, provinsi, kodePos, detail ->
                //Manggil API Edit ALAMATTTT
                updateAddress(
                    context = context,
                    kecamatan = kecamatan,
                    postalCode = kodePos.toIntOrNull() ?: 0,
                    street = street,
                    detail = detail
                ) { isSuccess, message ->
                    if (isSuccess) {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        currentAddress = "$street, $kecamatan, $city, $provinsi, $kodePos"
                        currentDetail = detail
                    } else {
                        Toast.makeText(context, "Error: $message", Toast.LENGTH_SHORT).show()
                    }
                }
                isEditDialogOpen = false
            }
        )
    }
}

@Composable
fun AlamatEditDialog(
    currentAddress: String,
    currentDetail: String,
    onDismiss: () -> Unit,
    onSave: (Context, String, String, String, String, String, String) -> Unit
) {
    val context = LocalContext.current
    var provinsi by remember { mutableStateOf(TextFieldValue(currentAddress.split(", ")[0])) }
    var city by remember { mutableStateOf(TextFieldValue(currentAddress.split(", ")[1])) }
    var kecamatan by remember { mutableStateOf(TextFieldValue(currentAddress.split(", ")[2])) }
    var street by remember { mutableStateOf(TextFieldValue(currentAddress.split(", ")[3])) }
    var postalCode by remember { mutableStateOf(TextFieldValue(currentAddress.split(", ")[4])) }
    var detail by remember { mutableStateOf(TextFieldValue(currentDetail)) }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        containerColor = bg_card,
        title = {
            Text(
                text = "Edit Alamat",
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Text("Provinsi")
                BasicTextField(
                    value = provinsi,
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray, shape = MaterialTheme.shapes.small)
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text("Kota (City)")
                BasicTextField(
                    value = city,
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray, shape = MaterialTheme.shapes.small)
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text("Kecamatan")
                BasicTextField(
                    value = kecamatan,
                    onValueChange = { kecamatan = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray, shape = MaterialTheme.shapes.small)
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text("Jalan (Street)")
                BasicTextField(
                    value = street,
                    onValueChange = { street = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray, shape = MaterialTheme.shapes.small)
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text("Kode Pos")
                BasicTextField(
                    value = postalCode,
                    onValueChange = { postalCode = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray, shape = MaterialTheme.shapes.small)
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text("Detail")
                BasicTextField(
                    value = detail,
                    onValueChange = { detail = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray, shape = MaterialTheme.shapes.small)
                        .padding(8.dp)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onSave(
                        context,
                        street.text,
                        kecamatan.text,
                        city.text,
                        provinsi.text,
                        postalCode.text,
                        detail.text
                    )
                },
                colors = ButtonDefaults.buttonColors(button)
            ) {
                Text("Simpan", color = Color.White)
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.White
                )
            ) {
                Text("Batal", color = Color.Black)
            }
        }
    )
}

@Composable
fun PesananItem(item: KeranjangItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp),
        colors = CardDefaults.cardColors(containerColor = bg_card_pesan),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.image),
                contentDescription = "Gambar ${item.name}",
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = "Rp${item.price}",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "${item.jumlah} kg",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }
}

@Composable
fun DetailBayar(items: List<KeranjangItem>, onTotalChanged: (Float) -> Unit) {
    val totalBayar = items.sumOf {
        it.price.multiply(it.jumlah.toBigDecimal())
    }.toFloat()
    val selectedCourier = remember { mutableStateOf("Pilih") }
    val selectedOngkir = remember { mutableStateOf(0f) } // Default ongkir 0
    val totalALL = totalBayar + selectedOngkir.value
    LaunchedEffect(totalALL) {
        onTotalChanged(totalALL) // Kirim totalALL ke PesananScreen
    }
    Column(modifier = Modifier.padding(16.dp)) {
        // Total Pesanan
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Total Pesanan:", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text("Rp$totalBayar", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        // Total Pengiriman
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Pilih Jasa Kirim:", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            PilihJasaKirim(
                selectedCourier = selectedCourier,
                dataDelivery = dataDelivery,
                selectedOngkir = selectedOngkir
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Divider(color = Color.Gray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Total Pembayaran:", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text("Rp$totalALL", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun PilihJasaKirim(
    selectedCourier: MutableState<String>,
    dataDelivery: List<Delivery>,
    selectedOngkir: MutableState<Float>
) {
    var showDialog by remember { mutableStateOf(false) }
    val courierOptions = dataDelivery.map { "${it.option.name.capitalize()} - Rp ${it.ongkir}" }

    Column {
        Row(
            modifier = Modifier
                .width(88.dp)
                .clickable { showDialog = true }
                .background(bacground),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedCourier.value,
                modifier = Modifier.weight(1f),
                fontSize = 14.sp
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Pilih jasa kirim",
                tint = Color.Gray
            )
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Pilih Jasa Kirim") },
                text = {
                    Column {
                        dataDelivery.forEach { courier ->
                            Text(
                                text = "${courier.option.name.capitalize()} - Rp ${courier.ongkir}",
                                modifier = Modifier
                                    .clickable {
                                        selectedCourier.value = courier.option.name
                                        selectedOngkir.value =
                                            courier.ongkir.toFloat() // Update nilai ongkir
                                        showDialog = false
                                    }
                                    .padding(vertical = 4.dp)
                            )
                        }
                    }
                },
                confirmButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("Tutup")
                    }
                }
            )
        }
    }
}

@Composable
fun GarisBatas() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .padding(horizontal = 16.dp)
    ) {
        val colors = listOf(button, Color.Green) // Dua warna bergantian
        val segmentWidth = size.width / 2 // Setengah halaman
        var currentX = 0f
        var colorIndex = 0

        while (currentX < size.width) {
            val endX = (currentX + segmentWidth).coerceAtMost(size.width)

            drawLine(
                color = colors[colorIndex % colors.size],
                start = Offset(currentX, 0f),
                end = Offset(endX, 0f),
                strokeWidth = 8f
            )

            currentX = endX
            colorIndex++
        }
    }
}

// Mengambil alamat
fun fetchAddress(context: Context, onResult: (List<Address>?, String) -> Unit) {
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    val userId = sharedPreferences.getInt("id", 0)
    Log.d("ROLE_ID", "$userId")

    val url = "http://10.0.2.2:5000/api/v1/address?role_id=$userId"

    val client = OkHttpClient()
    val request = Request.Builder()
        .url(url)
        .build()

    val scope = CoroutineScope(Dispatchers.IO)
    scope.launch {
        try {
            val response = client.newCall(request).execute()
            val responseData = response.body?.string() ?: ""
            Log.d("fetchAddress", "Respons API: $responseData")

            if (response.isSuccessful) {
                val jsonObject = JSONObject(responseData)

                if (jsonObject.has("address")) {
                    val addressObject = jsonObject.getJSONObject("address")
                    val addressItem = Address(
                        provinsi = addressObject.optString("provinsi", ""),
                        city = addressObject.optString("city", ""),
                        kecamatan = addressObject.optString("kecamatan", ""),
                        postalCode = addressObject.optInt("postalCode", 0),
                        street = addressObject.optString("street", ""),
                        detail = addressObject.optString("detail", "")
                    )
                    Log.d("ADRESS", "$addressItem")
                    onResult(listOf(addressItem), "Success") // Bungkus object ke dalam list
                } else {
                    onResult(null, "Error: 'address' key not found in JSON response")
                }
            } else {
                Log.e("fetchAddress", "Respons gagal: ${response.message}")
                onResult(null, "Error: ${response.message}")
            }
        } catch (e: Exception) {
            Log.e("fetchAddress", "Request gagal: ${e.message}")
            onResult(null, "Request Failed: ${e.message}")
        }
    }
}

// UUpdate alamat
fun updateAddress(
    context: Context,
    kecamatan: String,
    postalCode: Int,
    street: String,
    detail: String,
    onResult: (Boolean, String) -> Unit
) {
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    val roleId = sharedPreferences.getInt("id", 0)

    if (roleId == 0) {
        onResult(false, "Role ID is missing!")
        return
    }
    val url = "http://10.0.2.2:5000/api/v1/updateAddress/$roleId"
    // Membuat JSON request body
    val jsonObject = JSONObject().apply {
        put("kecamatan", kecamatan)
        put("postalCode", postalCode)
        put("street", street)
        put("detail", detail)
    }

    val requestBody = jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())

    val client = OkHttpClient()
    val request = Request.Builder()
        .url(url)
        .put(requestBody)
        .build()

    CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = client.newCall(request).execute()
            val responseData = response.body?.string() ?: ""

            Log.d("updateAddress", "Response API: $responseData")

            if (response.isSuccessful) {
                val jsonResponse = JSONObject(responseData)
                val success = jsonResponse.optBoolean("success", false)
                val message = jsonResponse.optString("message", "Update failed")

                withContext(Dispatchers.Main) {
                    if (success) {
                        onResult(true, "Address updated successfully!")
                    } else {
                        onResult(false, message)
                    }
                }
            } else {
                val errorMessage = "Error: ${response.message}"
                Log.e("updateAddress", errorMessage)
                withContext(Dispatchers.Main) {
                    onResult(false, errorMessage)
                }
            }
        } catch (e: Exception) {
            Log.e("updateAddress", "Request failed: ${e.message}")
            withContext(Dispatchers.Main) {
                onResult(false, "Request failed: ${e.message}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PesananContentPreview() {

    val selectedItems = "Item 1, Item 2, Item 3"

    PesananContent(selectedItems = selectedItems)
}
