package academy.bangkit.trackmate.view.app.detail.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Divider() {
    HorizontalDivider(
        color = Color(0xFFF3F3F3),
        thickness = 8.dp,
        modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
    )
}