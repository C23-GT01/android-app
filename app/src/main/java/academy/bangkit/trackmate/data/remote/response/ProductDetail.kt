package academy.bangkit.trackmate.data.remote.response

import com.google.gson.annotations.SerializedName

data class ProductDetail(

//    @field:SerializedName("product")
//    val product: List<ProductItem>,

    @field:SerializedName("data")
    val data: Data,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class Data(

    @field:SerializedName("product")
    val product: ProductItem
)

data class ProductItem(

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("contribution")
    val contribution: List<Int>,

    @field:SerializedName("production")
    val production: List<ProductionItem>,

    @field:SerializedName("price")
    val price: Int,

    @field:SerializedName("impact")
    val impact: List<ImpactItem>,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("resources")
    val resources: List<ProductMaterial>,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("umkm")
    val productBy: UMKM
)

data class UMKM(

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("logo")
    val logo: String,

    @field:SerializedName("employe")
    val employeeCount: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("location")
    val location: Location
)

data class ImpactItem(

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("name")
    val title: String,

    @field:SerializedName("deskripsi")
    val description: String
)

data class ProductionItem(

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("name")
    val title: String,

    @field:SerializedName("deskripsi")
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
