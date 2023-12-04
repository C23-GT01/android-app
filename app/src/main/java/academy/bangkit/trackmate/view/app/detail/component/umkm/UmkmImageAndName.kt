package academy.bangkit.trackmate.view.app.detail.component.umkm

import academy.bangkit.trackmate.R
import academy.bangkit.trackmate.data.remote.response.Location
import academy.bangkit.trackmate.data.remote.response.ProductMaterial
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun UmkmImageAndName(
    title: String,
    imageUrl: String,
    description: String
) {
    Image(
        painter = rememberAsyncImagePainter(
            model = imageUrl,
            placeholder = painterResource(id = R.drawable.umkm) // for preview only
        ),
        contentDescription = title,
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.White)
    )
    Row {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logoumkm",
            modifier = Modifier
                .height(140.dp)
                .width(120.dp)
                .padding(top = 16.dp, start = 16.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, bottom = 8.dp, end = 10.dp)
        ) {
            Text(
                text = title,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp),
                textAlign = TextAlign.Left,
                modifier = Modifier
            )
            Text(
                text = description,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier
            )
        }

    }

}
@Preview
@Composable
fun UmkmImageAndNamePreview(){
    TrackMateTheme {
        Surface {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn {
                    item {
                        ProductSample.apply {
                            UmkmImageAndName(title = title, imageUrl = imageUrl, description = description)
                        }
                    }
                }
            }
        }
    }
}

object ProductSample {
    const val title = "Joglo Ayu Tenan"

    const val imageUrl =
        "https://joglo-ayutenan.com/wp-content/uploads/2017/08/02.Dirrana-Necklace-1-600x600.jpg"

    const val description =
        "In this collection of the “Kraton” series, we are inspired by the meaning of the heirloom of the Yogyakarta Kraton..."

    val productMaterials = listOf(
        ProductMaterial(
            "Batu Alam",
            "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEimU-cI7ieeLXk0I90RhV_DIZ1TUb5BhFW_0Z2F2bbdtLtw_tLAIFX2l8-J1HhQZTAm6MbprbG0rVKR7vv8puEL-tcIl3_dKx438-JSRl_MVvSsRbY-K9YFujBwIAz3W6pMWLQdgkD1ehLbfdwe5wJfoV0lRspJu1Ukwrz-kCZ58x2vWkFV1GzBuHolnUM/s1600/Rectangle%2011%281%29.png",
            Location(0.0,0.0,""),
            ""
        ),
        ProductMaterial(
            "Mutiara",
            "https://lzd-img-global.slatic.net/g/p/f69e3cec597835b6e3d08e0abff7cfa5.jpg_720x720q80.jpg",
            Location(0.0,0.0,""),
            ""
        ),
        ProductMaterial(
            "Manik-manik",
            "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEjbm6ROwsg-1uy1sPiFrdWk9kN9mhIcORdGYBAmczx41kJIIULnOUIOgWGV6NPou33K8nI6QGlAXzG_MhwqpU-AkHCRCFWMx_kluytZM9yKtkh7clx_ZU-jRKZZtQE1MFLrGXTr8dIIhH_ujPT1gbPpOsOVuKhIsPaSJmGYov_azZQKinCo2hid3Sk0jjo/s840/Zarla-bead-logos-3999x2999-20230523.jpeg",
            Location(0.0,0.0,""),
            ""
        ),
        ProductMaterial(
            "Lorem Ipsum",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSYZrJAWoX8O2kJ_G5ZMa285VciX8rXIcGSJg5Tkssn&s",
            Location(0.0,0.0,""),
            ""
        ),
    )
}
