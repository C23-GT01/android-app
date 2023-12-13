package academy.bangkit.trackmate.view.app.home.component

import academy.bangkit.trackmate.view.isLandscape
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun FilterRow(
    modifier: Modifier = Modifier,
    action: (Int) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier.padding(top = 8.dp)
    ) {
        items(category, key = { it.textCategory }) { category ->
            CategoryItem(
                size = if (isLandscape(LocalConfiguration.current)) 40.dp else 60.dp,
                category = category,
                onClick = {
                    action(category.id)
                }
            )
        }
    }
}