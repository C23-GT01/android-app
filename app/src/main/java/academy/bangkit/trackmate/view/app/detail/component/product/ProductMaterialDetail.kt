package academy.bangkit.trackmate.view.app.detail.component.product

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import academy.bangkit.trackmate.data.remote.response.ProductMaterial
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import academy.bangkit.trackmate.view.TrackMateLocation
import academy.bangkit.trackmate.view.app.detail.component.Divider
import academy.bangkit.trackmate.view.app.detail.component.Title
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import academy.bangkit.trackmate.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ProductMaterialDetail(productMaterial: List<ProductMaterial>) {
    Title(stringResource(id = R.string.product_material))
    LazyRow(
        contentPadding = PaddingValues(
            start = 16.dp,
            top = 8.dp,
            bottom = 8.dp,
            end = 16.dp
        )
    ) {
        itemsIndexed(productMaterial) { _, item ->
            var isCardClicked by remember { mutableStateOf(false) }
            Card(
                modifier = Modifier
                    .width(150.dp)
                    .padding(end = 16.dp)
                    .clickable {
                        isCardClicked = !isCardClicked
                    }
            ) {
                if (isCardClicked) {
                    ModalBottomSheet(onDismissRequest = { isCardClicked = !isCardClicked }) {
                        Column {
                            Text(
                                text = item.name,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                            ) {
                                if (item.productBy != null) {
                                    Icon(
                                        imageVector = Icons.Default.ShoppingCart,
                                        contentDescription = "Location",
                                        modifier = Modifier
                                            .height(16.dp)
                                            .width(16.dp)
                                    )
                                    Text(
                                        text = item.productBy,
                                        modifier = Modifier.padding(start = 1.dp)
                                    )
                                }
                            }
                            val context = LocalContext.current
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp, bottom = 8.dp)
                                    .clickable {
                                        TrackMateLocation.sendToMapsActivity(context, item.location)
                                    },
                            ) {
                                Icon(
                                    imageVector = Icons.Default.LocationOn,
                                    contentDescription = "Location",
                                    modifier = Modifier
                                        .height(16.dp)
                                        .width(16.dp)
                                        .align(Alignment.CenterVertically)
                                )
                                Text(
                                    text = item.location.name,
                                    modifier = Modifier
                                        .padding(start = 1.dp)
                                        .align(Alignment.CenterVertically)
                                )
                            }
                            AsyncImage(
                                contentScale = ContentScale.FillBounds,
                                model = item.image,
                                contentDescription = "Translated description of what the image contains",
                                modifier = Modifier
                                    .width(150.dp)
                                    .height(150.dp)
                                    .background(Color.Blue)
                                    .align(Alignment.CenterHorizontally)
                            )
                            Text(
                                text = item.description,
                                modifier = Modifier.padding(
                                    start = 16.dp,
                                    end = 16.dp,
                                    bottom = 12.dp,
                                    top = 16.dp
                                )
                            )

                            Spacer(modifier = Modifier.padding(bottom = 50.dp))
                        }

                    }
                }
                AsyncImage(
                    contentScale = ContentScale.Crop,
                    model = item.image,
                    contentDescription = "Translated description of what the image contains",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .background(Color.DarkGray)
                )
                Column(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 8.dp,
                        bottom = 4.dp,
                        top = 4.dp
                    )
                ) {
                    Text(
                        text = item.name,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        if (item.productBy != null) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "Location",
                                modifier = Modifier
                                    .height(8.dp)
                                    .width(8.dp)
                            )
                            Text(
                                text = item.productBy,
                                fontSize = 10.sp,
                                lineHeight = 10.sp,
                                modifier = Modifier.padding(start = 1.dp)
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Location",
                            modifier = Modifier
                                .height(8.dp)
                                .width(8.dp)
                        )
                        Text(
                            text = item.location.name,
                            fontSize = 10.sp,
                            lineHeight = 10.sp,
                            modifier = Modifier.padding(start = 1.dp)
                        )
                    }
                }
            }
        }
    }
    Divider()
}

@Preview(showBackground = true, heightDp = 260)
@Composable
fun ProductMaterialDetailPreview() {
    TrackMateTheme {
        Surface {
            LazyColumn {
                item {
                    ProductMaterialDetail(ProductSample.productMaterials)
                }
            }
        }
    }
}