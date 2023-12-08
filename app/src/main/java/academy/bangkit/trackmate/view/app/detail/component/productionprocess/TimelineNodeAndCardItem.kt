package academy.bangkit.trackmate.view.app.detail.component.productionprocess

import academy.bangkit.trackmate.data.remote.response.ProductionItem
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import academy.bangkit.trackmate.view.app.detail.component.Divider
import academy.bangkit.trackmate.view.app.detail.component.Title
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import academy.bangkit.trackmate.R

@Composable
fun ProductionProcess(productionItem: List<ProductionItem>) {
    Title(title = stringResource(id = R.string.production_process))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        productionItem.forEachIndexed { index, item ->
            val position = when (index) {
                0 -> TimelineNodePosition.FIRST
                productionItem.lastIndex -> TimelineNodePosition.LAST
                else -> TimelineNodePosition.MIDDLE
            }
            val line = if (index == productionItem.lastIndex) null else LineParameters(4.dp)
            TimelineNode(
                position = position,
                lineParameters = line
            ) { modifier ->
                CardItem(item.title, item.description, item.image, modifier)
            }
        }
    }
    Divider()
}

@Preview(showBackground = true, heightDp = 1150)
@Composable
fun ProductionProcessPreview() {
    TrackMateTheme {
        Surface {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn {
                    item {
//                        ProductionProcess()
                    }
                }
            }
        }
    }
}