package academy.bangkit.trackmate.view.app.detail

import academy.bangkit.trackmate.data.remote.response.ProductDetail
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import academy.bangkit.trackmate.view.app.detail.component.ProductBy
import academy.bangkit.trackmate.view.app.detail.component.ProductImageAndOverview
import academy.bangkit.trackmate.view.app.detail.component.ProductImpactAndOverview
import academy.bangkit.trackmate.view.app.detail.component.ProductMaterialDetail
import academy.bangkit.trackmate.view.app.detail.component.productionprocess.ProductionProcess
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.LatLng
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ProductDetailScreen(
    navController: NavController,
    id: String,
    viewModel: ProductViewModel
) {

    val productDetailNullable by viewModel.product.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(initial = false)

    LaunchedEffect(Unit) {
        viewModel.getProductDetail("xxx")
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLoading) {
            Loading()
        } else {
            val productDetail: ProductDetail
            if (productDetailNullable != null) {

                productDetail = productDetailNullable as ProductDetail

                if (!productDetail.error) {

                    //product success to display
                    LazyColumn {
                        item {
                            val product = productDetail.product[0]

                            Log.d(
                                "Location",
                                "Lat = ${product.productBy.location.lat}\nLon = ${product.productBy.location.lng}\nLocation = ${product.productBy.location}"
                            )

                            ProductImageAndOverview(
                                title = product.name,
                                imageUrl = product.image,
                                price = formatToRupiah(product.price),
                                productOverview = product.description
                            )
                            ProductMaterialDetail(product.resources)
                            ProductionProcess(product.production)
                            ProductImpactAndOverview(product.impact)
                            ProductBy(
                                companyName = product.productBy.name,
                                logoUrl = "https://joglo-ayutenan.com/wp-content/uploads/2021/05/logo-Joglo-Ayu-Tenan-MakerSpace.png",
                                employeeCount = product.productBy.employeeCount,
                                locationName = product.productBy.location.name,
                                latLng = LatLng(
                                    product.productBy.location.lat,
                                    product.productBy.location.lng
                                )
                            )
                        }
                    }
                } else {
                    //product failed to display
                    ErrorScreen(productDetail.message) {
                        viewModel.getProductDetail("xxx")
                    }
                }
            }
        }
    }
}

@Composable
private fun ErrorScreen(message: String, action: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                text = message,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Box(
                modifier = Modifier
                    .wrapContentSize(Alignment.Center)
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        action()
                    },
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Icon(
                        Icons.Filled.Refresh,
                        contentDescription = "Retry",
                    )
                    Text(
                        text = "Coba Lagi",
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)
                    )
                }
            }
        }
    }
}

private fun formatToRupiah(amount: Int): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
    if (formatter is DecimalFormat) {
        formatter.applyPattern("'Rp'#,##0")
    }
    return formatter.format(amount.toLong())
}

@Composable
fun Loading() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(75.dp)
                .padding(16.dp)
                .align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun LoadingPreview() {
    TrackMateTheme {
        Surface {
            Loading()
        }
    }
}

@Preview
@Composable
fun ErrorScreenPreview() {
    TrackMateTheme {
        Surface {
            ErrorScreen(message = "Terjadi Error") {
                Log.d("Error", "Action click triggered")
            }
        }
    }
}