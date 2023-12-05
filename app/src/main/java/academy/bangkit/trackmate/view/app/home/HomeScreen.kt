package academy.bangkit.trackmate.view.app.home

import academy.bangkit.trackmate.data.remote.response.HomeResponse
import academy.bangkit.trackmate.di.Injection
import academy.bangkit.trackmate.navigation.Screen
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import academy.bangkit.trackmate.view.ViewModelFactory
import academy.bangkit.trackmate.view.app.detail.component.Divider
import academy.bangkit.trackmate.view.app.detail.component.Title
import academy.bangkit.trackmate.view.app.detail.product.ErrorScreen
import academy.bangkit.trackmate.view.app.detail.product.Loading
import academy.bangkit.trackmate.view.app.detail.product.formatToRupiah
import academy.bangkit.trackmate.view.app.home.component.Banner
import academy.bangkit.trackmate.view.app.home.component.CategoryItem
import academy.bangkit.trackmate.view.app.home.component.category
import android.content.res.Configuration
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel
) {

    val response by viewModel.product.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(initial = false)

    LaunchedEffect(Unit) {
        viewModel.getAllProducts()
    }

    val landscape = isLandscape(LocalConfiguration.current)

    Column {
        val listState = rememberLazyGridState()

        val showButton by remember {
            derivedStateOf {
                listState.firstVisibleItemIndex > 0
            }
        }

        AnimatedVisibility(visible = !showButton) {
            Column {
                Banner(imageVisible = !landscape)
            }
        }
        if (!landscape) {
            FilterRow()
        }

        AnimatedVisibility(visible = !showButton) {
            Column {
                if (!landscape) {
                    Divider()
                    Title(title = "Rekomendasi Produk")
                }
            }
        }

        if (isLoading) {
            Loading()
        } else {

            if (response != null) {
                val homeResponse = response as HomeResponse
                if (!homeResponse.error && homeResponse.data != null) {

                    Log.d("Home", homeResponse.data.products.toString())

                    LazyVerticalGrid(
                        state = listState,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 8.dp),
                        columns = GridCells.Adaptive(150.dp)
                    ) {

                        val products = homeResponse.data.products

                        items(products) { index ->
                            Card(
                                modifier = Modifier
                                    .height(200.dp)
                                    .padding(4.dp)
                                    .clickable {
                                        Log.d("Click", "Item ${index.id} ")
                                        navController.navigate(Screen.App.Detail.createRoute("xxx"))
                                    }
                            ) {
                                AsyncImage(
                                    contentScale = ContentScale.Crop,
                                    model = index.image,
                                    contentDescription = "Translated description of what the image contains",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(130.dp)
                                        .background(Color.DarkGray)
                                )
                                Column {
                                    Text(
                                        text = index.name,
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 16.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier.padding(
                                            horizontal = 8.dp,
                                            vertical = 2.dp
                                        )
                                    )
                                    Text(
                                        text = formatToRupiah(index.price),
                                        modifier = Modifier.padding(horizontal = 8.dp)
                                    )
                                }
                            }
                        }
                    }
                } else {
                    ErrorScreen(
                        message = homeResponse.message,
                        action = {viewModel.getAllProducts()}
                    )
                }
            }
        }
    }
}

fun isLandscape(configuration: Configuration): Boolean {
    return configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
}

@Composable
fun FilterRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier.padding(top = 16.dp)
    ) {
        items(category, key = { it.textCategory }) { category ->
            CategoryItem(
                size = if (isLandscape(LocalConfiguration.current)) 40.dp else 60.dp,
                category = category,
                onClick = {Log.d("Tes Cick Icon","Item ${category.id} clicked")}
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    TrackMateTheme {
        Surface {
            val viewModel = viewModel<HomeViewModel>(
                factory = ViewModelFactory(
                    Injection.provideUserRepository(
                        LocalContext.current
                    )
                )
            )
            HomeScreen(navController = rememberNavController(), viewModel = viewModel)
        }
    }
}