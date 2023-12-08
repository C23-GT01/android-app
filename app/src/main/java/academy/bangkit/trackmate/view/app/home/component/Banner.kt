package academy.bangkit.trackmate.view.app.home.component

import academy.bangkit.trackmate.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.QrCodeScanner
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun Banner(
    modifier: Modifier = Modifier,
    imageVisible: Boolean = true,
    onSearch: (String) -> Unit,
    onClickScanner: () -> Unit
) {
    Box(modifier = modifier) {
        if (imageVisible) {
            Image(
                painter = painterResource(R.drawable.banner),
                contentDescription = "Banner Image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            )
        }
        Search(
            onSearch = {
                onSearch(it)
            },
            onClickScanner = { onClickScanner() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit,
    onClickScanner: () -> Unit
) {

    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    SearchBar(
        query = text,
        onQueryChange = { text = it },
        onSearch = {
            active = false
            onSearch(it)
            text = ""
        },
        active = active,
        onActiveChange = { active = it },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        trailingIcon = {
            if (active) {
                Icon(
                    modifier = Modifier.clickable {
                        if (text.isNotEmpty()) {
                            text = ""
                        } else {
                            active = false
                        }
                    },
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Empty Search Text"
                )
            } else {
                if (text.isNotEmpty())
                    Icon(
                        modifier = Modifier.clickable {
                            onSearch(text)
                            text = ""
                        },
                        imageVector = Icons.AutoMirrored.Rounded.ArrowForwardIos,
                        contentDescription = null
                    ) else {
                    Icon(
                        modifier = Modifier.clickable { onClickScanner() },
                        imageVector = Icons.Rounded.QrCodeScanner,
                        contentDescription = null
                    )
                }
            }
        },
        placeholder = { Text(stringResource(id = R.string.search)) },
        shape = MaterialTheme.shapes.large,
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .padding(
                start = if (active) 0.dp else 6.dp,
                end = if (active) 0.dp else 6.dp
            )
    ) {
    }
}

