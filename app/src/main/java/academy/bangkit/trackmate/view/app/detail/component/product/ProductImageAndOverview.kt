package academy.bangkit.trackmate.view.app.detail.component.product

import academy.bangkit.trackmate.R
import academy.bangkit.trackmate.data.remote.response.Location
import academy.bangkit.trackmate.data.remote.response.ProductMaterial
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import academy.bangkit.trackmate.view.app.detail.component.Divider
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ProductImageAndOverview(
    title: String,
    imageUrl: String,
    price: String,
    productOverview: String
) {
    Image(
        painter = rememberAsyncImagePainter(
            model = imageUrl,
            placeholder = painterResource(id = R.drawable.placeholder_landscape) // for preview only
        ),
        contentDescription = title,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.White)
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, bottom = 8.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp),
            textAlign = TextAlign.Left,
            modifier = Modifier.fillMaxWidth(0.6f)
        )
        Text(
            text = price,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xff886345) // brown color
        )
    }

    var isClicked by remember { mutableStateOf(false) }

    Text(
        text = productOverview,
        maxLines = if (isClicked) Int.MAX_VALUE else 4,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .clickable { isClicked = !isClicked }
    )
    Divider()
}

@Preview(showBackground = true, heightDp = 680)
@Composable
fun OverviewAndMaterialPreview() {
    TrackMateTheme {
        Surface {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                LazyColumn {
                    item {
                        ProductSample.apply {
                            ProductImageAndOverview(
                                title = title,
                                imageUrl = imageUrl,
                                price = price,
                                productOverview = productOverview
                            )
                            ProductMaterialDetail(productMaterials)

                        }
                    }
                }
            }
        }
    }
}

//data sample for preview only

object ProductSample {
    const val title = "Dirrana Necklace"
    const val price = "Rp350.000"

    const val imageUrl =
        "https://joglo-ayutenan.com/wp-content/uploads/2017/08/02.Dirrana-Necklace-1-600x600.jpg"

    const val productOverview =
        "In this collection of the “Kraton” series, we are inspired by the meaning of the heirloom of the Yogyakarta Kraton or in Javanese culture it is called “Ampilan Dalem”, which consists of eight, one of which is the manifestation of Sawung Dhalang (Kijang)."

    val productMaterials = listOf(
        ProductMaterial(
            "",
            "Batu Alam",
            Location(0.0, 0.0, "Location"),
            ""
        ),
        ProductMaterial(
            "",
            "Mutiara",
            Location(0.0, 0.0, "Location"),
            ""
        ),
        ProductMaterial(
            "",
            "Manik-manik",
            Location(0.0, 0.0, "Location"),
            ""
        ),
        ProductMaterial(
            "",
            "Lorem Ipsum",
            Location(0.0, 0.0, "Location"),
            ""
        ),
    )
}
