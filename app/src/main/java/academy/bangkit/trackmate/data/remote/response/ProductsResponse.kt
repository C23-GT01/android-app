package academy.bangkit.trackmate.data.remote.response

import com.google.gson.annotations.SerializedName

// https://github.com/C23-GT01/backend-api#get-all-product

data class ProductsResponse(

    @field:SerializedName("error")
    val isError: Boolean,

    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("count")
    val count: Int,

    @field:SerializedName("data")
    val products: Products?
)

data class Products(

    @field:SerializedName("products")
    val productList: List<ProductItem>
)