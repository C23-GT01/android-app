package academy.bangkit.trackmate.view.app.detail.component.umkm

import academy.bangkit.trackmate.data.remote.response.ImpactItem
import academy.bangkit.trackmate.view.app.detail.component.Divider
import academy.bangkit.trackmate.view.app.detail.component.Title
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import academy.bangkit.trackmate.R

@Composable
fun UmkmImpact(umkmImpact: List<ImpactItem?>) {
    Title(title = stringResource(id = R.string.umkm_impact))

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp),
    ) {
        items(umkmImpact.filterNotNull()) { item ->
            Card(
                modifier = Modifier
                    .width(190.dp)
                    .padding(end = 16.dp)
            ) {
                Column {
                    AsyncImage(
                        contentScale = ContentScale.Crop,
                        model = item.image,
                        contentDescription = null,
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