package academy.bangkit.trackmate.view.app.scanner

import android.graphics.ImageFormat
import android.os.Build
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.zxing.BarcodeFormat
import com.google.zxing.BinaryBitmap
import com.google.zxing.DecodeHintType
import com.google.zxing.MultiFormatReader
import com.google.zxing.PlanarYUVLuminanceSource
import com.google.zxing.common.HybridBinarizer
import java.nio.ByteBuffer

class QrCodeAnalyzer(
    private val onQrCodeScanned: (String) -> Unit
) : ImageAnalysis.Analyzer {

    private val supportedImageFormats = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        listOf(
            ImageFormat.YUV_420_888,
            ImageFormat.YUV_422_888,
            ImageFormat.YUV_444_888,
        )
    } else {
        listOf(
            ImageFormat.YUV_420_888,
        )
    }

    override fun analyze(image: ImageProxy) {
        if (image.format in supportedImageFormats) {
            val bytes = image.planes.first().buffer.toByteArray()
            val source = PlanarYUVLuminanceSource(
                /* yuvData = */ bytes,
                /* dataWidth = */ image.width,
                /* dataHeight = */ image.height,
                /* left = */ 0,
                /* top = */ 0,
                /* width = */ image.width,
                /* height = */ image.height,
                /* reverseHorizontal = */ false
            )
            val binaryBmp = BinaryBitmap(HybridBinarizer(source))
            try {
                val result = MultiFormatReader().apply {
                    setHints(
                        mapOf(
                            DecodeHintType.POSSIBLE_FORMATS to arrayListOf(
                                BarcodeFormat.QR_CODE
                            )
                        )
                    )
                }.decode(binaryBmp)
                onQrCodeScanned(result.text)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                image.close()
            }
        }
    }

    private fun ByteBuffer.toByteArray(): ByteArray {
        rewind()
        return ByteArray(remaining()).also {
            get(it)
        }
    }
}