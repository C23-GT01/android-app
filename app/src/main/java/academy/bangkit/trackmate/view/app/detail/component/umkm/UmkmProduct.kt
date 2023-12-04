package academy.bangkit.trackmate.view.app.detail.component.umkm

import academy.bangkit.trackmate.R
import academy.bangkit.trackmate.view.app.detail.component.Title
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Product(val name: String, val price: String, val image: Int)

val productList = listOf(
    Product("Product 1", "$19.99", R.drawable.necklace),
    Product("Product 2", "$29.99", R.drawable.headband),
    Product("Product 3", "$39.99", R.drawable.ic_launcher_foreground),
    Product("Product 4", "$49.99", R.drawable.ic_launcher_foreground),
    // Add more products as needed
)

@Composable
fun ProductCard(product: Product, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .width(150.dp)
            .height(200.dp)
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = product.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(shape = MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = product.price,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
fun UmkmProduct() {
    Title(title = "Product")

    LazyRow(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(productList) { product ->
            ProductCard(
                product = product,
                modifier = Modifier
                    .padding(end = 16.dp)
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewMyApp() {
    UmkmProduct()
}
