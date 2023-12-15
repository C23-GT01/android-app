package academy.bangkit.trackmate.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecommendationResponse(

	@field:SerializedName("data")
	val data: DataRecommendation,

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class DataRecommendation(

	@field:SerializedName("recommended_products")
	val recommendedProducts: List<RecommendedProductsItem?>?
)

data class RecommendedProductsItem(

	@field:SerializedName("product_id")
	val productId: String,

	@field:SerializedName("rating_x")
	val rating: String,

	@field:SerializedName("product_name")
	val productName: String,

	@field:SerializedName("product_link")
	val productLink: String
)
