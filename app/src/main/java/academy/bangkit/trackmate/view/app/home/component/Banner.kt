package academy.bangkit.trackmate.view.app.home.component

import academy.bangkit.trackmate.R
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.unit.dp

@Composable
fun Banner(imageVisible: Boolean = true, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        if (imageVisible) {
            Image(
                painter = painterResource(R.drawable.banner),
                contentDescription = "Banner Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(140.dp)
            )
        }
        Search()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(modifier: Modifier = Modifier) {

    var text by remember { mutableStateOf("") }

    var active by remember { mutableStateOf(false) }

    SearchBar(
        query = text,
        onQueryChange = {
            text = it
        },
        onSearch = {
            active = false
            Log.d("onSearch", it)
        },
        active = active,
        onActiveChange = {
            active = it
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        placeholder = {
            Text("Cari produk UMKM!")
        },
        shape = MaterialTheme.shapes.large,
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier
//            .padding(if (imageVisible) 8.dp else 2.dp)
            .fillMaxWidth()
            .heightIn(min = 48.dp)
    ) {
    }
}

