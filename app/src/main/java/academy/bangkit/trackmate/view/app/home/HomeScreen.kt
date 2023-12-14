package academy.bangkit.trackmate.view.app.home

import academy.bangkit.trackmate.R
import academy.bangkit.trackmate.data.remote.response.ProductsResponse
import academy.bangkit.trackmate.navigation.Screen
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import academy.bangkit.trackmate.view.Factory
import academy.bangkit.trackmate.view.app.detail.component.Divider
import academy.bangkit.trackmate.view.app.detail.component.Title
import academy.bangkit.trackmate.view.app.home.component.Banner
import academy.bangkit.trackmate.view.app.home.component.EmptyProducts
import academy.bangkit.trackmate.view.app.home.component.FilterRow
import academy.bangkit.trackmate.view.component.CircularLoading
import academy.bangkit.trackmate.view.component.ErrorScreen
import academy.bangkit.trackmate.view.formatToRupiah
import academy.bangkit.trackmate.view.isLandscape
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel,
    uri: Uri? = null,
) {
    if (uri != null) {

        val productId = uri.lastPathSegment
        if (!productId.isNullOrEmpty()) {
            navController.navigate(Screen.App.Detail.createRoute(productId)){
                popUpTo(Screen.App.route)
            }
        }
    }

    val context = LocalContext.current

    val savedState by rememberSaveable {
        mutableStateOf<SavedStateHandle?>(null)
    }

    val savedResponse = savedState?.getLiveData<ProductsResponse>("response")
    val response by viewModel.products.observeAsState(savedResponse)

    val isLoading by viewModel.isLoading.observeAsState(initial = false)

    var title by rememberSaveable {
        mutableStateOf(savedState?.get("title") ?: context.getString(R.string.category_all))
    }

    var searchKeyword by rememberSaveable {
        mutableStateOf(savedState?.get("search") ?: "")
    }

    LaunchedEffect(Unit) {
        if (response == null) {
            Log.d("Getting data", "running from launched effect")
            viewModel.getAllProducts()
        }
    }

    DisposableEffect(key1 = response) {
        onDispose {
            savedState?.set("response", response)
            savedState?.set("title", title)
            savedState?.set("search", searchKeyword)
        }
    }


    val landscape = isLandscape(LocalConfiguration.current)

    Column {
        val listState = rememberLazyGridState()
        var itemMoreThan9 by mutableStateOf(false)

        val isFirstItemVisible by remember {
            derivedStateOf {
                listState.firstVisibleItemIndex > 0 && itemMoreThan9
            }
        }

        AnimatedVisibility(visible = !isFirstItemVisible) {
            Column {
                Banner(
                    imageVisible = !landscape,
                    onSearch = {
                        viewModel.getAllProducts(keyword = it)
                        title = "Hasil pencarian: \"$it\""
                        searchKeyword = it
                    },
                    onClickScanner = { navController.navigate(Screen.App.Scanner.route) }
                )
            }
        }

        if (!landscape) {
            FilterRow {
                when (it) {
                    0 -> {
                        title = context.getString(R.string.category_all)
                        viewModel.getAllProducts()
                        searchKeyword = ""
                    }

                    1 -> {
                        title = context.getString(R.string.category_agriculture)
                        viewModel.getAllProducts(it)
                        searchKeyword = ""
                    }

                    2 -> {
                        title = context.getString(R.string.category_food)
                        viewModel.getAllProducts(it)
                        searchKeyword = ""
                    }

                    3 -> {
                        title = context.getString(R.string.category_drink)
                        viewModel.getAllProducts(it)
                        searchKeyword = ""
                    }

                    else -> {
                        title = context.getString(R.string.category_unknown)
                        viewModel.getAllProducts()
                        searchKeyword = ""
                    }
                }
            }
        }

        AnimatedVisibility(visible = !isFirstItemVisible) {
            Column {
                if (!landscape) {
                    Divider()
                    Title(title = title)
                }
            }
        }

        if (isLoading) {
            CircularLoading()
        } else {

            if (response != null) {
                val productsResponse = response as ProductsResponse
                if (!productsResponse.isError && productsResponse.products != null) {

                    val products = productsResponse.products.productList
                    if (products.isEmpty()) {
                        EmptyProducts()
                    } else {
                        if (products.size >= 10) {
                            itemMoreThan9 = true
                        }
                        LazyVerticalGrid(
                            state = listState,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 8.dp),
                            columns = GridCells.Adaptive(150.dp),
//                            contentPadding = PaddingValues(bottom = 100.dp)
                        ) {
                            items(products) { product ->
                                Card(
                                    modifier = Modifier
                                        .height(200.dp)
                                        .padding(4.dp)
                                        .clickable {
                                            Log.d("Click", "Item ${product.id} ")
                                            navController.navigate(
                                                Screen.App.Detail.createRoute(
                                                    product.id
                                                )
                                            )
                                        }
                                ) {
                                    AsyncImage(
                                        contentScale = ContentScale.Crop,
                                        model = product.images[0],
                                        contentDescription = "Translated description of what the image contains",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(130.dp)
                                            .background(Color.DarkGray)
                                    )
                                    Column {
                                        Text(
                                            text = product.name,
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
                                            text = formatToRupiah(product.price),
                                            modifier = Modifier.padding(horizontal = 8.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                } else {
                    ErrorScreen(
                        message = productsResponse.message,
                        action = { reloadData(searchKeyword, viewModel, title, context) }
                    )
                }
            }
        }
    }
}

private fun reloadData(
    searchKeyword: String,
    viewModel: HomeViewModel,
    title: String,
    context: Context
) {
    if (searchKeyword != "") {
        viewModel.getAllProducts(keyword = searchKeyword)
    } else {
        when (title) {
            context.getString(R.string.category_all) -> {
                viewModel.getAllProducts()
            }

            context.getString(R.string.category_agriculture) -> {
                viewModel.getAllProducts(categoryId = 1)
            }

            context.getString(R.string.category_food) -> {
                viewModel.getAllProducts(categoryId = 2)
            }

            context.getString(R.string.category_drink) -> {
                viewModel.getAllProducts(categoryId = 3)
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    TrackMateTheme {
        Surface {
            val viewModel = viewModel<HomeViewModel>(factory = Factory())
            HomeScreen(navController = rememberNavController(), viewModel = viewModel)
        }
    }
}