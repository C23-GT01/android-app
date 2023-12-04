package academy.bangkit.trackmate.view.app.detail.component

import academy.bangkit.trackmate.data.remote.response.ImpactItem
import academy.bangkit.trackmate.data.remote.response.ProductImpactOverview
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import android.util.Log
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
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Warning
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
fun ProductImpactAndOverview(productImpact: List<ImpactItem>, contribution: List<Int>) {
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

    //overview
    //contribution

    Log.d("Product Contribution", contribution.toString())

    Text(
        text = "Dengan membeli produk ini Anda telah...",
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
    )

    contribution.forEach {
        val productImpactOverview1 =
            when (it) {
                1 -> ProductImpactOverview(
                    Icons.Rounded.AccountBox,
                    "Telah menguntungkan produsen sebagai penjual produk ini"
                )

                2 -> ProductImpactOverview(
                    Icons.Rounded.Build,
                    "Telah menguntungkan penjuial sebagai pembeli"
                )

                3 -> ProductImpactOverview(
                    Icons.Rounded.Warning,
                    "Telah menguntungkan kedua belah pihak"
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
            Icon(productImpactOverview1.icons, "Content desc")
            Text(
                text = productImpactOverview1.description,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }

//    productImpactOverview.forEach {
//        Row(
//            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
//        ) {
//            Icon(it.icons, "Content desc")
//            Text(text = it.description, modifier = Modifier.padding(start = 8.dp))
//        }
//    }
    Divider()
}

@Preview(showBackground = true, heightDp = 700)
@Composable
fun ProductWellBeingPreview() {
    TrackMateTheme {
        Surface {
            LazyColumn {
                item {
//                    ProductImpactAndOverview()
                }
            }
        }
    }
}