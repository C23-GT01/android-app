package academy.bangkit.trackmate.view.app.detail

import academy.bangkit.trackmate.data.remote.response.ImpactItem
import academy.bangkit.trackmate.data.remote.response.Location
import academy.bangkit.trackmate.data.remote.response.DetailResponse
import academy.bangkit.trackmate.data.remote.response.ProductItem
import academy.bangkit.trackmate.data.remote.response.ProductMaterial
import academy.bangkit.trackmate.data.remote.response.ProductionItem
import academy.bangkit.trackmate.data.remote.response.UMKM
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
import okhttp3.internal.immutableListOf
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ProductDetailScreen(
    navController: NavController,
    id: String,
    viewModel: ProductViewModel
) {

    val response by viewModel.product.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(initial = false)

    LaunchedEffect(Unit) {
        viewModel.getProductDetail("xxx")
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize()) { Loading() }
    } else {
        val detailResponse: DetailResponse
        if (response != null) {

            detailResponse = response as DetailResponse

            if (!detailResponse.error && detailResponse.data != null) {

                ShowProduct(detailResponse.data.product)
            } else {
                //product failed to display
                ErrorScreen(detailResponse.message) {
                    viewModel.getProductDetail("xxx")
                }
            }
        }
    }
//    }
}

@Composable
private fun ShowProduct(product: ProductItem) {
    Box(modifier = Modifier.fillMaxSize()) {
        //product success to display
        LazyColumn {
            item {

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
                ProductImpactAndOverview(product.impact, product.contribution)
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
    }
}

@Composable
fun ErrorScreen(message: String, action: () -> Unit) {
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

fun formatToRupiah(amount: Int): String {
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

@Preview(heightDp = 1950)
@Composable
fun ProductDetailScreenPrev() {
    TrackMateTheme {
        val sampleProduct = ProductItem(
            image = "https://picsum.photos/200",
            contribution = immutableListOf(1, 2, 3),
            production = immutableListOf(
                ProductionItem("https://picsum.photos/100", "Title 1", "Description 1"),
                ProductionItem("https://picsum.photos/200", "Title 2", "Description 2"),
                ProductionItem("https://picsum.photos/300", "Title 3", "Description 3"),
                ProductionItem("https://picsum.photos/400", "Title 4", "Description 4"),
            ),
            price = 100000,
            impact = immutableListOf(
                ImpactItem(
                    "https://picsum.photos/500",
                    "Impact Title 1",
                    "Description of Impact 1"
                ),
                ImpactItem(
                    "https://picsum.photos/600",
                    "Impact Title 2",
                    "Description of Impact 2"
                ),
                ImpactItem(
                    "https://picsum.photos/700",
                    "Impact Title 3",
                    "Description of Impact 3"
                ),
                ImpactItem(
                    "https://picsum.photos/800",
                    "Impact Title 4",
                    "Description of Impact 4"
                ),
                ImpactItem(
                    "https://picsum.photos/900",
                    "Impact Title 5",
                    "Description of Impact 5"
                ),
            ),
            name = "Product Name",
            description = "Description of Product",
            resources = immutableListOf(
                ProductMaterial(
                    "https://picsum.photos/201",
                    "Material 1",
                    Location(0.0, 0.0, "Wonogiri, Jawa Tengah"),
                    "Description of this resource."
                ),
                ProductMaterial(
                    "https://picsum.photos/202",
                    "Material 1",
                    Location(0.0, 0.0, "Wonogiri, Jawa Tengah"),
                    "Description of this resource."
                ),
                ProductMaterial(
                    "https://picsum.photos/203",
                    "Material 1",
                    Location(0.0, 0.0, "Wonogiri, Jawa Tengah"),
                    "Description of this resource."
                ),
                ProductMaterial(
                    "https://picsum.photos/204",
                    "Material 1",
                    Location(0.0, 0.0, "Wonogiri, Jawa Tengah"),
                    "Description of this resource."
                ),
            ),
            id = "sampleIdOfProduct",
            productBy = UMKM(
                "sampleIdOfUMKM",
                "https://picsum.photos/200",
                21,
                "Nama UMKM",
                Location(0.0, 0.0, "Wonogiri, Jawa Tengah")
            )

        )
        Surface {
            ShowProduct(product = sampleProduct)
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