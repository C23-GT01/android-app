package academy.bangkit.trackmate.view.app.detail.component.umkm

import academy.bangkit.trackmate.R
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter

@Composable
fun UmkmImageAndName(
    title: String,
    image: String,
    logo: String,
    description: String
) {
    Image(
        painter = rememberAsyncImagePainter(
            model = image,
            placeholder = painterResource(id = R.drawable.placeholder_landscape)
        ),
        contentDescription = title,
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.White)
    )
    Row {
        AsyncImage(
            model = logo,
            contentDescription = null,
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
            var isClicked by remember { mutableStateOf(false) }

            Text(
                text = description,
                maxLines = if (isClicked) Int.MAX_VALUE else 4,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(start = 0.dp, end = 0.dp)
                    .clickable { isClicked = !isClicked }
            )
        }
    }
}

@Preview(heightDp = 500)
@Composable
fun UmkmImageAndNamePreview() {
    TrackMateTheme {
        Surface {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn {
                    item {
                        val title = "Joglo Ayu Tenan"
                        val imageUrl = ""
                        val description =
                            "In this collection of the “Kraton” series, we are inspired by the meaning of the heirloom of the Yogyakarta Kraton..."

                        UmkmImageAndName(
                            title = title,
                            image = imageUrl,
                            logo = "",
                            description = description
                        )
                    }
                }
            }
        }
    }
}
