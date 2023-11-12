package academy.bangkit.trackmate.view.app.detail.component

import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Person
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
fun ProductBy(
    companyName: String,
    logoUrl: String,
    employeeCount: Int,
    location: String
) {
    Title("Produsen")
    Row(
        modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
    ) {
        AsyncImage(
            contentScale = ContentScale.Crop,
            model = logoUrl,
            contentDescription = "Translated description of what the image contains",
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .background(Color.DarkGray)
        )
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(text = companyName, fontWeight = FontWeight.ExtraBold)
            Row {
                Icon(Icons.Rounded.Person, "Karyawan")
                Text(text = "$employeeCount Karyawan")
            }
            Row {
                Icon(Icons.Rounded.LocationOn, "Karyawan")
                Text(text = location)
            }
        }
    }
}

@Preview(showBackground = true, heightDp = 200, widthDp = 320)
@Composable
fun UMKMProfile() {
    TrackMateTheme {
        Surface {
            LazyColumn {
                item {
                    ProductBy(
                        companyName = "Joglo Ayu Tenan",
                        logoUrl = "https://joglo-ayutenan.com/wp-content/uploads/2021/05/logo-Joglo-Ayu-Tenan-MakerSpace.png",
                        employeeCount = 50,
                        location = "Sleman, Yogyakarta"
                    )
                }
            }
        }
    }
}