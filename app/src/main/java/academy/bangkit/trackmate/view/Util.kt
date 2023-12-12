package academy.bangkit.trackmate.view

import academy.bangkit.trackmate.di.Injection
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONException
import org.json.JSONObject
import java.io.File
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

@Composable
fun LockScreenOrientation(orientation: Int) {
    val context = LocalContext.current
    DisposableEffect(orientation) {
        val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = orientation
        onDispose {
            activity.requestedOrientation = originalOrientation
        }
    }
}

fun isLandscape(configuration: Configuration): Boolean {
    return configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

fun formatToRupiah(amount: Int): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
    if (formatter is DecimalFormat) {
        formatter.applyPattern("'Rp'#,##0")
    }
    return formatter.format(amount.toLong())
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Composable
fun Factory(): ViewModelFactory {
    return ViewModelFactory(Injection.provideUserRepository(LocalContext.current))
}

fun sendWhatsAppMessage(context: Context, phoneNumber: String, message: String) {

    val installed = isWAInstalled(context)
    if (installed) {
        context.startActivity(
            Intent(Intent.ACTION_VIEW)
                .setData(Uri.parse("https://api.whatsapp.com/send?phone=$phoneNumber&text=$message"))
        )
    } else {
        Toast.makeText(context, "WhatsApp tidak ditemukan", Toast.LENGTH_SHORT).show()
    }
}

private fun isWAInstalled(context: Context): Boolean {
    val packageManager = context.packageManager
    return try {
        packageManager.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}

fun parseErrorMessage(errorBody: String?): String {
    return try {
        val errorJson = JSONObject(errorBody!!)
        errorJson.optString("message", "Unknown error")
    } catch (e: JSONException) {
        "Error parsing JSON response"
    }
}

fun Uri.toMultipartBodyPart(context: Context): MultipartBody.Part {
    val inputStream = context.contentResolver.openInputStream(this)
    val file = File(context.cacheDir, "pp_from_mobile")
    inputStream?.use { input ->
        file.outputStream().use { output ->
            input.copyTo(output)
        }
    }
    val requestFile = file.asRequestBody("image/jpeg".toMediaType())
    return MultipartBody.Part.createFormData("data", file.name, requestFile)
}