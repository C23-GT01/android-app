package academy.bangkit.trackmate.view.component

import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ErrorScreen(message: String, action: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                text = message,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Box(
                modifier = Modifier
                    .wrapContentSize(Alignment.Center)
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        action()
                    },
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Icon(
                        Icons.Filled.Refresh,
                        contentDescription = "Retry",
                    )
                    Text(
                        text = "Coba Lagi",
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview(heightDp = 200)
@Composable
fun ErrorScreenPreview() {
    TrackMateTheme {
        Surface {
            ErrorScreen(message = "Terjadi Error") {
                Log.d("Error", "Action click triggered")
            }
        }
    }
}