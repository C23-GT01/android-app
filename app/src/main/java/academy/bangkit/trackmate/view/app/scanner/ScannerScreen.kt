package academy.bangkit.trackmate.view.app.scanner

import academy.bangkit.trackmate.R
import academy.bangkit.trackmate.navigation.Screen
import academy.bangkit.trackmate.view.showToast
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
    val context = LocalContext.current

    LaunchedEffect(qrCodeToString) {
        if (qrCodeToString != null) {

            // TODO: sebelum navigasi ke detail screen lakukan validasi

            navController.navigate(Screen.App.Detail.createRoute(qrCodeToString as String)) {
                popUpTo(Screen.App.Scanner.route) {
                    inclusive = true
                }
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
                .addOnFailureListener { e ->
                    navController.navigate(Screen.App.Home.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                    }
                    val errorMessage = e.message ?: context.getString(R.string.unknown_error)
                    showToast(context, context.getString(R.string.error_message, errorMessage))
                }
        }
    }
}