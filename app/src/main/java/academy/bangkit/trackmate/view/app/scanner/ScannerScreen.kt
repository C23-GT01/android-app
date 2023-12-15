package academy.bangkit.trackmate.view.app.scanner

import academy.bangkit.trackmate.R
import academy.bangkit.trackmate.navigation.Screen
import academy.bangkit.trackmate.view.showToast
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

@Composable
fun ScannerScreen(navController: NavController) {

    var qrCodeToString: String? by remember { mutableStateOf(null) }
    var gmsScannerFailed: Boolean by remember { mutableStateOf(false) }
    val context = LocalContext.current
    if (gmsScannerFailed) {
        QRCodeCamera {
            qrCodeToString = it
        }
    }

    LaunchedEffect(qrCodeToString) {
        if (qrCodeToString != null) {
            try {
                val webUrl = qrCodeToString as String
                val prefix = "web-app-five-beta.vercel.app/product/"
                if (webUrl.startsWith(prefix)) {
                    val productId = webUrl.removePrefix(prefix)
                    navController.navigate(Screen.App.Detail.createRoute(productId)) {
                        popUpTo(Screen.App.Scanner.route) {
                            inclusive = true
                        }
                    }
                } else {
                    scannerError(context, navController)
                }
            } catch (e: Exception) {
                scannerError(context, navController)
            }
        } else {
            val options = GmsBarcodeScannerOptions.Builder()
                .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
                .enableAutoZoom()
                .build()

            val scanner = GmsBarcodeScanning.getClient(context, options)
            scanner.startScan()
                .addOnSuccessListener { barcode ->
                    barcode.rawValue?.let {
                        qrCodeToString = it
                    }
                }
                .addOnCanceledListener {
                    navController.navigate(Screen.App.Home.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                    }
                    showToast(context, context.getString(R.string.scan_canceled))
                }
                .addOnFailureListener {
//                    navController.navigate(Screen.App.Home.route) {
//                        popUpTo(navController.graph.findStartDestination().id) {
//                            inclusive = true
//                        }
//                    }
                    gmsScannerFailed = true
//                    val errorMessage = e.message ?: context.getString(R.string.unknown_error)
//                    showToast(context, context.getString(R.string.error_message, errorMessage))
                }
        }
    }
}

private fun scannerError(
    context: Context,
    navController: NavController
) {
    showToast(context, "QR code tidak diketahui")
    navController.navigateUp()
}