package academy.bangkit.trackmate.view.app.detail.component.umkm

import academy.bangkit.trackmate.data.remote.response.Location
import academy.bangkit.trackmate.view.TrackMateLocation
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun UmkmLocation(logo: String, companyName: String, location: Location) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                TrackMateLocation.sendToMapsActivity(
                    context,
                    Location(location.lng, location.lat, location.name)
                )
            }
    ) {
        // Logo
        AsyncImage(
            model = logo,
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )

        // Spacing between logo and text
        Spacer(modifier = Modifier.width(16.dp))

        // Name and Location
        Column {
            Text(
                text = companyName,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = location.name,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UmkmLocatonPrev() {
//    UmkmLocation(companyName = "Joglo Ayu Tenan", locationName = "Jl. Langsat")
}
