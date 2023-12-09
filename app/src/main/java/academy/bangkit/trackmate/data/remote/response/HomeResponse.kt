package academy.bangkit.trackmate.data.remote.response

import com.google.gson.annotations.SerializedName

// https://github.com/C23-GT01/backend-api#get-all-product

data class HomeResponse(

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("count")
    val count: Int,

    @field:SerializedName("data")
    val data: DataHome?
)

data class ProductsItemHome(

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("image")
    val image: List<String>,

    @field:SerializedName("price")
    val price: Int,

    @field:SerializedName("category")
    val category: Int
)

data class DataHome(

    @field:SerializedName("products")
    val products: List<ProductsItemHome>
)