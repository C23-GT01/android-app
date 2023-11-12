package academy.bangkit.trackmate.data.remote.response

import androidx.compose.ui.graphics.vector.ImageVector

data class ProductImpact(
    val picture: String,
    val description: String
)

data class ProductImpactOverview(
    val icons: ImageVector,
    val description: String
)