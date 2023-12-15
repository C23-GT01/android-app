package academy.bangkit.trackmate.view.app.detail.component.umkm

import academy.bangkit.trackmate.data.remote.response.History
import academy.bangkit.trackmate.view.app.detail.component.Divider
import academy.bangkit.trackmate.view.app.detail.component.Title
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import academy.bangkit.trackmate.R

@Composable
fun UmkmHistory(history: History) {
    Divider()
    Title(title = stringResource(id = R.string.history_umkm))
    AsyncImage(
        model = history.image,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
    Text(text = history.text, modifier = Modifier.padding(horizontal = 16.dp))
    Divider()
}