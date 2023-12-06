package academy.bangkit.trackmate.view.app.detail.component.umkm

import academy.bangkit.trackmate.data.remote.response.ImpactItem
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun UmkmImpact(umkmImpact: List<ImpactItem>) {
    Title(title = "UMKM Impact")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(start = 16.dp, top = 16.dp),
    ) {
        umkmImpact.forEach { item ->
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
}