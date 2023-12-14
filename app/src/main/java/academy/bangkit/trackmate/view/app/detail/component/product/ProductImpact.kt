package academy.bangkit.trackmate.view.app.detail.component.product

import academy.bangkit.trackmate.data.remote.response.ImpactItem
import academy.bangkit.trackmate.data.remote.response.ProductImpactOverview
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import academy.bangkit.trackmate.view.app.detail.component.Divider
import academy.bangkit.trackmate.view.app.detail.component.Title
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
import academy.bangkit.trackmate.R
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource

@Composable
fun ProductImpactAndOverview(productImpact: List<ImpactItem?>, contribution: List<Byte>) {

    val context = LocalContext.current
    val filteredList: List<ImpactItem> = productImpact.filterNotNull()

    if (filteredList.isNotEmpty()) {
        Title(title = stringResource(id = R.string.product_impact))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(start = 16.dp, top = 16.dp),
        ) {
            filteredList.forEach { item ->
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
                                modifier = Modifier.padding(
                                    top = 4.dp,
                                    bottom = 4.dp,
                                    start = 6.dp
                                )
                            )
                        }
                    }
                }
            }
        }
    }


    if (contribution.isNotEmpty()) {
        Divider()
        Text(
            text = stringResource(id = R.string.product_impact_overview),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
        )

        contribution.forEach {
            val productImpactOverview =
                when (it) {
                    1.toByte() -> ProductImpactOverview(
                        Icons.Rounded.Air, context.getString(R.string.minimalisasi_jejak_carbon)
                    )

                    2.toByte() -> ProductImpactOverview(
                        Icons.Rounded.ElectricBolt,
                        context.getString(R.string.efisiensi_energi)
                    )

                    3.toByte() -> ProductImpactOverview(
                        Icons.Rounded.RestoreFromTrash,
                        context.getString(R.string.pengelolaan_limbah)
                    )

                    4.toByte() -> ProductImpactOverview(
                        Icons.Rounded.ShareLocation,
                        context.getString(R.string.penggunaan_bahan_baku_lokal)
                    )

                    5.toByte() -> ProductImpactOverview(
                        Icons.Rounded.WaterDrop,
                        context.getString(R.string.efisiensi_air)
                    )

                    6.toByte() -> ProductImpactOverview(
                        Icons.Rounded.Recycling,
                        context.getString(R.string.daur_ulang_produk)
                    )

                    7.toByte() -> ProductImpactOverview(
                        Icons.Rounded.EmojiPeople,
                        context.getString(R.string.kesejahteraan_pekerja)
                    )

                    8.toByte() -> ProductImpactOverview(
                        Icons.Rounded.Eco,
                        context.getString(R.string.kesehatan_dan_keamanan_lingkungan)
                    )

                    9.toByte() -> ProductImpactOverview(
                        Icons.AutoMirrored.Rounded.TrendingUp,
                        context.getString(R.string.kemajuan_umkm_indonesia)
                    )

                    else -> {
                        ProductImpactOverview(Icons.Rounded.Close, "-")
                    }
                }
            Row(
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            ) {
                Icon(
                    productImpactOverview.icons,
                    null,
                    Modifier,
                    Color.DarkGray
                )
                Text(
                    text = productImpactOverview.description,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        Divider()
    }
}

@Preview(showBackground = true, heightDp = 420)
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