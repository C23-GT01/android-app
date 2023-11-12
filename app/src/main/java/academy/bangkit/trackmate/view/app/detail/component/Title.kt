package academy.bangkit.trackmate.view.app.detail.component

import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Title(title: String) {
    Text(
        text = title,
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
        textAlign = TextAlign.Left,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    )
}

@Preview
@Composable
fun TitlePreview() {
    TrackMateTheme {
        Surface {
            Title(title = "Title Text Preview")
        }
    }
}