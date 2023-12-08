package academy.bangkit.trackmate.view.app.detail.product

import academy.bangkit.trackmate.data.remote.response.DetailResponse
import academy.bangkit.trackmate.data.remote.response.ImpactItem
import academy.bangkit.trackmate.data.remote.response.Location
import academy.bangkit.trackmate.data.remote.response.ProductItem
import academy.bangkit.trackmate.data.remote.response.ProductMaterial
import academy.bangkit.trackmate.data.remote.response.ProductionItem
import academy.bangkit.trackmate.data.remote.response.UMKMInDetail
import academy.bangkit.trackmate.navigation.Screen
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import academy.bangkit.trackmate.view.app.detail.component.product.ProductBy
import academy.bangkit.trackmate.view.app.detail.component.product.ProductImageAndOverview
import academy.bangkit.trackmate.view.app.detail.component.product.ProductImpactAndOverview
import academy.bangkit.trackmate.view.app.detail.component.product.ProductMaterialDetail
import academy.bangkit.trackmate.view.app.detail.component.productionprocess.ProductionProcess
import academy.bangkit.trackmate.view.component.CircularLoading
import academy.bangkit.trackmate.view.component.ErrorScreen
import academy.bangkit.trackmate.view.formatToRupiah
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.model.LatLng
import okhttp3.internal.immutableListOf

@Composable
fun ProductDetailScreen(
    navController: NavController,
    id: String,
    viewModel: ProductViewModel
) {

    val response by viewModel.product.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(initial = false)

    LaunchedEffect(Unit) {
        Log.d("Check ID", "Diterima $id")
        viewModel.getProductDetail("xxx")
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize()) { CircularLoading() }
    } else {
        val detailResponse: DetailResponse
        if (response != null) {

            detailResponse = response as DetailResponse

            if (!detailResponse.error && detailResponse.data != null) {

                ShowProduct(detailResponse.data.product, navController)
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
private fun ShowProduct(product: ProductItem, navController: NavController) {
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
                ) {
                    Log.d("Click", "ProductBy")
                    navController.navigate(Screen.App.UMKM.createRoute("idUmkm"))
                }
            }
        }
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
            productBy = UMKMInDetail(
                "sampleIdOfUMKM",
                "https://picsum.photos/200",
                21,
                "Nama UMKM",
                Location(0.0, 0.0, "Wonogiri, Jawa Tengah")
            )
        )
        Surface {
            ShowProduct(product = sampleProduct, rememberNavController())
        }
    }
}