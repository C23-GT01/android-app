package academy.bangkit.trackmate.view.app.detail.component

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
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Build
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
fun ProductImpactAndOverview() {
    Title(title = "Impact")

    val productImpactList = listOf(
        academy.bangkit.trackmate.data.remote.response.ProductImpact(
            picture = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEh1ugwyLH-fzqmxoV8z6knguIiIv2_9DOS21pYXtsQw6NwXHILoIrZPp0XiYVBkcEY-NxqkZxSpZ2EpHuVTOtXTVW2IxQ8CLMvCax5doVB0N7Bm_YOrknlJykBD3gDTK9J44v5BgSMEy9Y2ZvFblPbY-wbsouJNRCowVNnJUULA7ML09-9cUii6js-_vfY/s1600/Banner.png",
            description = "All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary"
        ),
        academy.bangkit.trackmate.data.remote.response.ProductImpact(
            picture = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhh9-AUwPP1In39KxBwxF0__z3l68oOKncV8QOKtugv3H9HiNWa9ZHWUtY3a_BYD3HJl-2MQIeIwekf2TRfgfD46dBHEjzkIxLaX3_yHTQ4YW9wKL8xUxulIY6A7u9hV5W7EE7HvwI2mm8AACBtTLQ65VqyTDb79gemZhZ2YkwyGV-_UkCsVZqvaioYfqQ/s1600/Rectangle%2011%285%29.png",
            description = "Making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable."
        ),
        academy.bangkit.trackmate.data.remote.response.ProductImpact(
            picture = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgEN5Haa4bgF8YFRMBINc47wJHHmQKwLe3xrq4HKMVd_V2TRmngOZNlSkTy7L4saZA598XuiKdEEEDSnpruZTJEUUvXh-v1RF6acxyhn-D6JvJ_Z6pV-EfiA9CN-p3bQZ4njPAIDZlVQ_4w1O2EgfXZIX6-DqaDqYOEJ8ZdwQc_Phzdcq0lzguqzpMLgp8/s1600/Rectangle%2011%286%29.png",
            description = "The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc"
        ),
        academy.bangkit.trackmate.data.remote.response.ProductImpact(
            picture = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSYZrJAWoX8O2kJ_G5ZMa285VciX8rXIcGSJg5Tkssn&s",
            description = "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested"
        ),
        academy.bangkit.trackmate.data.remote.response.ProductImpact(
            picture = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSYZrJAWoX8O2kJ_G5ZMa285VciX8rXIcGSJg5Tkssn&s",
            description = "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested"
        )
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(start = 16.dp, top = 16.dp),
    ) {
        productImpactList.forEach { item ->
            Card(
                modifier = Modifier
                    .width(190.dp)
                    .padding(end = 16.dp)
            ) {
                Column {
                    AsyncImage(
                        contentScale = ContentScale.Crop,
                        model = item.picture,
                        contentDescription = "Translated description of what the image contains",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp)
                            .background(Color.DarkGray)
                    )
                    Text(
                        text = item.description,
                        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp, start = 6.dp)
                    )
                }
            }
        }
    }
    Divider()
    Text(
        text = "Dengan membeli produk ini Anda telah...",
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
    )

    val productImpactOverview = mutableListOf(
        ProductImpactOverview(
            Icons.Rounded.AccountBox,
            "Telah menguntungkan produsen sebagai penjual produk ini"
        ),
        ProductImpactOverview(
            Icons.Rounded.Build,
            "Telah menguntungkan penjuial sebagai pembeli"
        ),
        ProductImpactOverview(
            Icons.Rounded.Warning,
            "Telah menguntungkan kedua belah pihak"
        ),
    )

    productImpactOverview.forEach {
        Row(
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        ) {
            Icon(it.icons, "Content desc")
            Text(text = it.description, modifier = Modifier.padding(start = 8.dp))
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
                    ProductImpactAndOverview()
                }
            }
        }
    }
}