package academy.bangkit.trackmate.view.app.home.component

import academy.bangkit.trackmate.R
import academy.bangkit.trackmate.data.remote.response.RecommendationResponse
import academy.bangkit.trackmate.navigation.Screen
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Banner(
    modifier: Modifier = Modifier,
    imageVisible: Boolean = true,
    onSearch: (String) -> Unit,
    onClickScanner: () -> Unit,
    getRecommendation: () -> Unit,
    recommendation: RecommendationResponse?,
    navController: NavController
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
            navController = navController,
            recommendation = recommendation,
            getRecommendation = { getRecommendation() },
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
    onClickScanner: () -> Unit,
    getRecommendation: () -> Unit,
    recommendation: RecommendationResponse?,
    navController: NavController
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
        LaunchedEffect(Unit) {
            getRecommendation()
        }

        if (recommendation != null) {
            if (!recommendation.error) {
                Text(
                    text = stringResource(id = R.string.product_recommended),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )
                val context = LocalContext.current
                LazyColumn(
                    userScrollEnabled = false,
                ) {
                    if (recommendation.data.recommendedProducts != null) {
                        val listProduct = recommendation.data.recommendedProducts.filterNotNull()
                        items(listProduct) {
                            Text(
                                text = "${it.productName} (⭐ ${it.rating})",
                                modifier = Modifier
                                    .padding(start = 9.dp, bottom = 2.dp, end = 8.dp)
                                    .clickable {
                                        val prefix = "https://web-app-five-beta.vercel.app/product/"
                                        if (it.productLink.startsWith(prefix)) {
                                            val productId = it.productLink.removePrefix(prefix)
                                            navController.navigate(
                                                Screen.App.Detail.createRoute(productId)
                                            )
                                        } else {
                                            val intent = Intent(
                                                Intent.ACTION_VIEW,
                                                Uri.parse(it.productLink)
                                            )
                                            context.startActivity(intent)
                                        }
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}

