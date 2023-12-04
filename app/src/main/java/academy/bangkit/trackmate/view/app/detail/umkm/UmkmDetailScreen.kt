package academy.bangkit.trackmate.view.app.detail.umkm

import academy.bangkit.trackmate.data.remote.repository.ProductRepository
import academy.bangkit.trackmate.data.remote.response.ImpactItem
import academy.bangkit.trackmate.data.remote.response.Location
import academy.bangkit.trackmate.data.remote.response.ProductItem
import academy.bangkit.trackmate.data.remote.response.ProductMaterial
import academy.bangkit.trackmate.data.remote.response.ProductionItem
import academy.bangkit.trackmate.data.remote.response.UMKM
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import academy.bangkit.trackmate.view.app.detail.component.Divider
import academy.bangkit.trackmate.view.app.detail.component.umkm.UmkmContact
import academy.bangkit.trackmate.view.app.detail.component.umkm.UmkmImageAndName
import academy.bangkit.trackmate.view.app.detail.component.umkm.UmkmImpactAndOverview
import academy.bangkit.trackmate.view.app.detail.component.umkm.UmkmLocation
import academy.bangkit.trackmate.view.app.detail.component.umkm.UmkmProduct
import academy.bangkit.trackmate.view.app.detail.product.ProductViewModel
import android.content.ClipData.Item
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import okhttp3.internal.immutableListOf
import okhttp3.internal.wait

@Composable
fun UmkmDetailScreen(
    navController: NavController,
    id: String,
    viewModel: ProductViewModel,
    umkm: ProductItem
) {
    LaunchedEffect(Unit) {
        viewModel.getProductDetail("xxx")
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item {
            UmkmImageAndName(title = title, imageUrl = imageUrl, description = description)
            Divider()
            UmkmProduct()
            UmkmImpactAndOverview(umkmImpact = umkm.impact, contribution = umkm.contribution)
            UmkmLocation(companyName = "Nama UMKM", locationName = "Alamat UMKM")
            Divider()
            UmkmContact()
        }
    }
}

@Preview
@Composable
fun ProductDetailPreview() {
    TrackMateTheme {
        val navController = rememberNavController()
        val productId = "yourProductId"
        val repository = ProductRepository()
        val viewModel = ProductViewModel(repository = repository)
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

        UmkmDetailScreen(navController = navController, id = productId, viewModel = viewModel, umkm = sampleProduct)
    }
}

const val title = "Joglo Ayu Tenan"

const val imageUrl =
    "https://joglo-ayutenan.com/wp-content/uploads/2017/08/02.Dirrana-Necklace-1-600x600.jpg"

const val description =
    "In this collection of the “Kraton” series, we are inspired by the meaning of the heirloom of the Yogyakarta Kraton..."
