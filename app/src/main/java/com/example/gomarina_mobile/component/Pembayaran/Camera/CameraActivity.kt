package com.example.gomarina_mobile.component.Pembayaran.Camera

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.ViewGroup
import androidx.activity.compose.setContent
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.gomarina_mobile.component.Pembayaran.Camera.CameraActivity.Companion.GALLERY_REQUEST_CODE
import com.example.gomarina_mobile.ui.theme.BlackBlur
import java.io.File
import kotlin.concurrent.timer
import com.example.gomarina_mobile.component.Pembayaran.Camera.fungsi.Timer

class CameraActivity : CameraPermission() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Izin Kamera
        handleCameraPermission()

        setContent {
            val isPermissionGranted by isCameraPermissionGranted.collectAsState()

            if (isPermissionGranted) {
                CameraPreview()
            } else {
                PermissionDeniedScreen()
            }
        }
    }

    companion object {
        const val GALLERY_REQUEST_CODE = 1001
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK) {
            data?.data?.let { selectedImageUri ->
                try {
                    // Membuat file baru di penyimpanan aplikasi
                    val photoFile = File(
                        filesDir,
                        "photo_${System.currentTimeMillis()}.jpg"
                    )

                    // Menyalin file dari URI dari galeri ke file aplikasi
                    contentResolver.openInputStream(selectedImageUri)?.use { inputStream ->
                        photoFile.outputStream().use { outputStream ->
                            inputStream.copyTo(outputStream)
                        }
                    }

                    // Mengirim URI hasil ke aktivitas pemanggil
                    val savedUri = Uri.fromFile(photoFile)
                    val resultIntent = Intent().apply {
                        putExtra("image_uri", savedUri.toString())
                    }
                    setResult(RESULT_OK, resultIntent)
                    finish() // Menutup CameraActivity dan kembali ke aktivitas sebelumnya
                } catch (e: Exception) {
                    Log.e("Gallery", "Error saving image: ${e.message}")
                }
            }
        }
    }
}

@Composable
fun CameraPreview() {
    val context = LocalContext.current
    val lifecycleOwner = LocalContext.current as LifecycleOwner
    var imageCapture by remember { mutableStateOf<ImageCapture?>(null) }
    var selectedTimer by remember { mutableStateOf(Timer.OFF) }
    var countdown by remember { mutableStateOf(0) }
    var isTimerActive by remember { mutableStateOf(false) }
    var isTimerMenuVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            factory = { viewContext ->
                val cameraProviderFuture = ProcessCameraProvider.getInstance(viewContext)
                val previewView = PreviewView(viewContext).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }

                val executor = ContextCompat.getMainExecutor(viewContext)

                cameraProviderFuture.addListener({
                    val cameraProvider = cameraProviderFuture.get()
                    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                    val preview = Preview.Builder()
                        .setTargetAspectRatio(AspectRatio.RATIO_16_9)
                        .setTargetRotation(previewView.display.rotation)
                        .build()
                        .also {
                            it.setSurfaceProvider(previewView.surfaceProvider)
                        }

                    imageCapture = ImageCapture.Builder()
                        .setTargetAspectRatio(AspectRatio.RATIO_16_9)
                        .setTargetRotation(previewView.display.rotation)
                        .build()

                    val camera = cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        imageCapture
                    )
                }, executor)

                previewView
            },
            modifier = Modifier.fillMaxSize()
        )

        // countdown
        if (isTimerActive && countdown > 0) {
            Text(
                text = "$countdown",
                fontSize = 48.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Bagian atas untuk icon
        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .background(BlackBlur),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            // Ikon Timer
            IconButton(onClick = { isTimerMenuVisible = !isTimerMenuVisible }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.AccessAlarm,
                        contentDescription = "Timer",
                        tint = Color.White
                    )
                    if (selectedTimer != Timer.OFF) {
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = when (selectedTimer) {
                                Timer.S3 -> "3 sec"
                                Timer.S5 -> "5 sec"
                                else -> ""
                            },
                            color = Color.White,
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }

        // Menu Timer
        if (isTimerMenuVisible) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(top = 64.dp)
            ) {
                Timer.values().forEach { timer ->
                    TextButton(onClick = {
                        selectedTimer = timer
                        isTimerMenuVisible = false
                    }) {
                        Text(
                            text = when (timer) {
                                Timer.OFF -> "No Timer"
                                Timer.S3 -> "3 sec"
                                Timer.S5 -> "5 sec"
                            },
                            color = Color.White
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(BlackBlur)
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            // Tombol untuk foto
            Button(
                onClick = {
                    if (selectedTimer != Timer.OFF) {
                        isTimerActive = true
                        countdown = when (selectedTimer) {
                            Timer.S3 -> 4
                            Timer.S5 -> 6
                            else -> 0
                        }
                        timer("Countdown", initialDelay = 0, period = 1000) {
                            if (countdown > 0) {
                                countdown--
                            } else {
                                cancel()
                                isTimerActive = false
                                capturePhoto(context, imageCapture)
                            }
                        }
                    } else {
                        capturePhoto(context, imageCapture)
                    }
                },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                modifier = Modifier
                    .size(60.dp)
                    .border(3.dp, Color.Gray, CircleShape)
            ) {

            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .fillMaxWidth()
                .padding(20.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            val context = LocalContext.current

            Button(
                onClick = { openGallery(context, GALLERY_REQUEST_CODE) },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                modifier = Modifier.size(60.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.PhotoLibrary,
                        contentDescription = "Gallery Icon",
                        modifier = Modifier.size(20.dp),
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        color = Color.Black,
                        text = "Galeri",
                        fontSize = 8.sp
                    )
                }
            }

        }
    }
}

fun openGallery(context: Context, requestCode: Int) {
    if (context is Activity) {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        context.startActivityForResult(intent, requestCode)
    } else {
        Log.e("OpenGallery", "Context is not an Activity")
    }
}

// Foto Kamera
fun capturePhoto(context: Context, imageCapture: ImageCapture?) {
    val photoFile = File(
        context.filesDir,
        "photo_${System.currentTimeMillis()}.jpg"
    )
    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

    imageCapture?.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onError(exception: ImageCaptureException) {
                Log.e("CameraX", "Photo capture failed: ${exception.message}")
            }

            override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                val savedUri = Uri.fromFile(photoFile)
                val resultIntent = Intent().apply {
                    putExtra("image_uri", savedUri.toString())
                }

                // Mengirim hasil kembali ke CameraActivity
                if (context is CameraActivity) {
                    context.setResult(Activity.RESULT_OK, resultIntent)
                    context.finish()  // Menutup CameraActivity dan kembali ke aktivitas sebelumnya
                }
            }

        }
    )
}

@Composable
fun PermissionDeniedScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Camera permission is required to use this feature.")
    }
}