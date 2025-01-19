package academy.bangkit.trackmate.data.remote.response

import androidx.compose.ui.graphics.vector.ImageVector
import com.google.gson.annotations.SerializedName

// https://github.com/C23-GT01/backend-api#Get-Detail-Product

data class DetailResponse(

    @field:SerializedName("data")
    val data: ProductItem?,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("message")
    val message: String
)

data class ProductItem(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("slug")
    val slug: String,

    @field:SerializedName("images")
    val images: List<String>,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("price")
    val price: Int,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("umkm")
    val umkm: UMKMInDetail,

    @SerializedName("production")
    val production: List<ProductionItem>?,

    @SerializedName("resource")
    val resource: List<ProductMaterial>?,

    @SerializedName("impact")
    val impact: List<ImpactItem?>,

    @SerializedName("contribution")
    val contribution: List<Byte>,

    @field:SerializedName("category")
    val category: ProductCategory,

    @field:SerializedName("updatedAt")
    val updatedAt: String
)

data class UMKMInDetail(
    @SerializedName("name")
    val name: String,

    @SerializedName("slug")
    val slug: String,

    @SerializedName("logo")
    val logo: String,

    @SerializedName("employe")
    val employe: Int,

    @SerializedName("location")
    val location: Location
)

data class ImpactItem(

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("name")
    val title: String,

    @field:SerializedName("description")
    val description: String
)

data class ProductionItem(

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("name")
    val title: String,

    @field:SerializedName("description")
    val description: String
)

data class Location(

    @field:SerializedName("lng")
    val lng: Double,

    @field:SerializedName("lat")
    val lat: Double,

    @field:SerializedName("name")
    val name: String
)

data class ProductImpactOverview(
    val icons: ImageVector,
    val description: String
)

data class ProductMaterial(

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("location")
    val location: Location,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("umkm")
    val productBy: String? = null
)

data class ProductCategory(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("slug")
    val slug: String
)