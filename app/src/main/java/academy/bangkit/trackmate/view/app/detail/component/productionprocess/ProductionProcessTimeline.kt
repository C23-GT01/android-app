package academy.bangkit.trackmate.view.app.detail.component.productionprocess

import academy.bangkit.trackmate.ui.theme.Brown
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun TimelineNode(
    lineParameters: LineParameters? = null,
    circleParameters: CircleParameters = CircleParameters(5.dp, Brown),
    position: TimelineNodePosition,
    contentStartOffset: Dp = 16.dp,
    spacerBetweenNodes: Dp = 32.dp,
    content: @Composable BoxScope.(modifier: Modifier) -> Unit,
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .drawBehind {
                val circleRadiusInPx = circleParameters.radius.toPx()
                drawCircle(
                    color = circleParameters.backgroundColor,
                    radius = circleRadiusInPx,
                    center = Offset(circleRadiusInPx, circleRadiusInPx)
                )
                lineParameters?.let {
                    drawLine(
                        brush = Brush.horizontalGradient(listOf(Brown, Color.Gray)),
                        start = Offset(x = circleRadiusInPx, y = circleRadiusInPx * 2),
                        end = Offset(x = circleRadiusInPx, y = this.size.height),
                        strokeWidth = lineParameters.strokeWidth.toPx()
                    )
                }
            }

    ) {
        content(
            Modifier
                .padding(
                    start = contentStartOffset,
                    bottom = if (position != TimelineNodePosition.LAST) spacerBetweenNodes else 0.dp
                )
        )
    }
}

@Composable
fun CardItem(title: String, desc: String, imageUrl: String, modifier: Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp)
    ) {
        Row {
            AsyncImage(
                contentScale = ContentScale.Crop,
                model = imageUrl,
                contentDescription = title,
                modifier = Modifier
                    .width(130.dp)
                    .height(130.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.DarkGray)
            )
            Column {
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                )
                Text(
                    text = desc,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                )
            }
        }
    }
}

enum class TimelineNodePosition {
    FIRST,
    MIDDLE,
    LAST
}

data class CircleParameters(
    val radius: Dp,
    val backgroundColor: Color
)

data class LineParameters(
    val strokeWidth: Dp,
)