package academy.bangkit.trackmate.view.app.detail.umkm

import academy.bangkit.trackmate.data.remote.response.UMKMResponse
import academy.bangkit.trackmate.data.remote.response.Umkm
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import academy.bangkit.trackmate.view.app.detail.component.Divider
import academy.bangkit.trackmate.view.app.detail.component.umkm.UmkmContact
import academy.bangkit.trackmate.view.app.detail.component.umkm.UmkmImageAndName
import academy.bangkit.trackmate.view.app.detail.component.umkm.UmkmImpact
import academy.bangkit.trackmate.view.app.detail.component.umkm.UmkmLocation
import academy.bangkit.trackmate.view.app.detail.component.umkm.UmkmProduct
import academy.bangkit.trackmate.view.app.detail.product.ErrorScreen
import academy.bangkit.trackmate.view.component.CircularLoading
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun UmkmDetailScreen(
    navController: NavController,
    id: String,
    viewModel: UmkmViewModel
) {

    val response by viewModel.umkm.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(initial = false)

    LaunchedEffect(Unit) {
        viewModel.getUMKMDetail("mamo")
        Log.d("Check ID", "Diterima $id")
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize()) { CircularLoading() }
    } else {
        val detailResponse: UMKMResponse
        if (response != null) {

            detailResponse = response as UMKMResponse

            if (!detailResponse.error && detailResponse.data != null) {
                Log.d("Found", response?.data?.umkm.toString())
                ShowUMKM(detailResponse.data.umkm)
            } else {
                ErrorScreen(message = detailResponse.status) {
                    viewModel.getUMKMDetail("mamo")
                }
            }
        }
    }
}

@Composable
private fun ShowUMKM(umkm: Umkm) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item {
            UmkmImageAndName(
                title = umkm.name,
                imageUrl = umkm.image,
                description = umkm.description
            )
            Divider()
            UmkmProduct()
            UmkmImpact(umkmImpact = umkm.impact)
            UmkmLocation(
                companyName = umkm.name,
                locationName = umkm.location.name
            )
            Divider()
            UmkmContact()
        }
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