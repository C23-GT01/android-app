package academy.bangkit.trackmate.data.remote.response

import com.google.gson.annotations.SerializedName

data class HomeResponse(

    @field:SerializedName("data")
    val data: DataHome?,

    @field:SerializedName("count")
    val count: Int,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class ProductsItemHome(

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("price")
    val price: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("category")
    val category: Int
)

data class DataHome(

    @field:SerializedName("products")
    val products: List<ProductsItemHome>
)