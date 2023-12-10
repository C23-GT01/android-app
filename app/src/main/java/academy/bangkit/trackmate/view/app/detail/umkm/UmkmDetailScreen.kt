package academy.bangkit.trackmate.view.app.detail.umkm

import academy.bangkit.trackmate.data.remote.response.ProductsResponse
import academy.bangkit.trackmate.data.remote.response.ProductItem
import academy.bangkit.trackmate.data.remote.response.UMKMResponse
import academy.bangkit.trackmate.data.remote.response.Umkm
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import academy.bangkit.trackmate.view.app.detail.component.Divider
import academy.bangkit.trackmate.view.app.detail.component.umkm.UmkmContact
import academy.bangkit.trackmate.view.app.detail.component.umkm.UmkmImageAndName
import academy.bangkit.trackmate.view.app.detail.component.umkm.UmkmImpact
import academy.bangkit.trackmate.view.app.detail.component.umkm.UmkmLocation
import academy.bangkit.trackmate.view.app.detail.component.umkm.UmkmProduct
import academy.bangkit.trackmate.view.component.CircularLoading
import academy.bangkit.trackmate.view.component.ErrorScreen
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun UmkmDetailScreen(
    navController: NavController,
    id: String,
    viewModel: UmkmViewModel
) {

    val umkmResponse by viewModel.umkm.observeAsState()
    val productsResponse by viewModel.products.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(initial = false)

    LaunchedEffect(Unit) {
        viewModel.getUMKMDetailAndProducts(id)
        Log.d("Check ID", "Diterima $id")
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize()) { CircularLoading() }
    } else {
        val umkmDetailResponse: UMKMResponse
        val productResponse: ProductsResponse

        if (umkmResponse != null && productsResponse != null) {

            umkmDetailResponse = umkmResponse as UMKMResponse
            productResponse = productsResponse as ProductsResponse

            if (!umkmDetailResponse.error && umkmDetailResponse.data != null && !productResponse.isError && productResponse.products != null) {
                Log.d("Product Count", productResponse.count.toString())
                ShowUMKM(
                    umkmDetailResponse.data.umkm,
                    if (productResponse.count <= 0) null else productResponse.products.productList,
                    navController
                )
            } else {
                ErrorScreen(message = umkmDetailResponse.status) {
                    viewModel.getUMKMDetailAndProducts(id)
                }
            }
        }
    }
}

@Composable
private fun ShowUMKM(
    umkm: Umkm,
    products: List<ProductItem>? = null,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
//        item {
            UmkmImageAndName(
                title = umkm.name,
                image = umkm.image,
                logo = umkm.logo,
                description = umkm.description
            )
            Divider()
            UmkmProduct(products, navController)
            UmkmImpact(umkmImpact = umkm.impact)
            UmkmLocation(
                companyName = umkm.name,
                locationName = umkm.location.name
            )
            Divider()
            UmkmContact()
//        }
    }
}

@Preview(showBackground = true)
@Composable
fun UmkmDetailPrev() {
    TrackMateTheme {
        Surface {
//            val umkm = Umkm()
//            ShowUMKM(umkm = )
        }
    }
}