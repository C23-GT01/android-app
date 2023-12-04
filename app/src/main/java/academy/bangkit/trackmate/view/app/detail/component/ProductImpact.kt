package academy.bangkit.trackmate.view.app.detail.component

import academy.bangkit.trackmate.data.remote.response.ImpactItem
import academy.bangkit.trackmate.data.remote.response.ProductImpactOverview
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.TrendingUp
import androidx.compose.material.icons.rounded.Air
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Eco
import androidx.compose.material.icons.rounded.ElectricBolt
import androidx.compose.material.icons.rounded.EmojiPeople
import androidx.compose.material.icons.rounded.Recycling
import androidx.compose.material.icons.rounded.RestoreFromTrash
import androidx.compose.material.icons.rounded.ShareLocation
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ProductImpactAndOverview(productImpact: List<ImpactItem>, contribution: List<Byte>) {
    Title(title = "Product Impact")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(start = 16.dp, top = 16.dp),
    ) {
        productImpact.forEach { item ->
            Card(
                modifier = Modifier
                    .width(190.dp)
                    .padding(end = 16.dp)
            ) {
                Column {
                    AsyncImage(
                        contentScale = ContentScale.Crop,
                        model = item.image,
                        contentDescription = "Translated description of what the image contains",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp)
                            .background(Color.DarkGray)
                    )
                    Column {
                        Text(
                            text = item.title,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(top = 4.dp, start = 6.dp)
                        )
                        Text(
                            text = item.description,
                            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp, start = 6.dp)
                        )
                    }
                }
            }
        }
    }
    Divider()
    Text(
        text = "Dengan membeli produk ini Anda telah mendukung...",
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
    )

    contribution.forEach {
        val productImpactOverview1 =
            when (it) {
                1.toByte() -> ProductImpactOverview(
                    Icons.Rounded.Air,
                    "Minimalisasi Carboon Footprints"
                )

                2.toByte() -> ProductImpactOverview(
                    Icons.Rounded.ElectricBolt,
                    "Efisiensi Energi"
                )

                3.toByte() -> ProductImpactOverview(
                    Icons.Rounded.RestoreFromTrash,
                    "Pengelolaan Limbah"
                )

                4.toByte() -> ProductImpactOverview(
                    Icons.Rounded.ShareLocation,
                    "Penggunaan Bahan Baku Lokal"
                )

                5.toByte() -> ProductImpactOverview(
                    Icons.Rounded.WaterDrop,
                    "Efisiensi Air"
                )

                6.toByte() -> ProductImpactOverview(
                    Icons.Rounded.Recycling,
                    "Daur Ulang Produk"
                )

                7.toByte() -> ProductImpactOverview(
                    Icons.Rounded.EmojiPeople,
                    "Kesejahteraan Pekerja"
                )

                8.toByte() -> ProductImpactOverview(
                    Icons.Rounded.Eco,
                    "Kesehatan dan Keamanan Lingkungan"
                )

                9.toByte() -> ProductImpactOverview(
                    Icons.AutoMirrored.Rounded.TrendingUp,
                    "Kemajuan UMKM Indonesia"
                )

                else -> {
                    ProductImpactOverview(
                        Icons.Rounded.Close,
                        "-"
                    )
                }
            }
        Row(
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        ) {
            Icon(
                productImpactOverview1.icons,
                "Content desc",
                Modifier,
                Color.DarkGray
            )
            Text(
                text = productImpactOverview1.description,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
    Divider()
}

@Preview(showBackground = true, heightDp = 700)
@Composable
fun ProductWellBeingPreview() {
    TrackMateTheme {
        Surface {
            LazyColumn {
                item {
                    ProductImpactAndOverview(
                        mutableListOf(), mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
                    )
                }
            }
        }
    }
}