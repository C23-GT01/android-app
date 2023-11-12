package academy.bangkit.trackmate.view.app.detail

import academy.bangkit.trackmate.view.app.detail.component.ManufacturerSample
import academy.bangkit.trackmate.view.app.detail.component.ProductBy
import academy.bangkit.trackmate.view.app.detail.component.ProductImageAndOverview
import academy.bangkit.trackmate.view.app.detail.component.ProductImpactAndOverview
import academy.bangkit.trackmate.view.app.detail.component.ProductMaterialDetail
import academy.bangkit.trackmate.view.app.detail.component.ProductSample
import academy.bangkit.trackmate.view.app.detail.component.productionprocess.ProductionProcess
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun ProductDetailScreen(
    navController: NavController,
    id: String,
    viewModel: ProductViewModel
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn {
            item {

                viewModel.test(id)

                // TODO: Replace with data get from response (viewModel)
                ProductSample.apply {
                    ProductImageAndOverview(
                        title = title,
                        imageUrl = imageUrl,
                        price = price,
                        productOverview = productOverview
                    )
                    ProductMaterialDetail(productMaterials)
                }
                ProductionProcess()
                ProductImpactAndOverview()

                ManufacturerSample.apply {
                    ProductBy(companyName, logoUrl, employeeCount, location)
                }
            }
        }
    }
}

