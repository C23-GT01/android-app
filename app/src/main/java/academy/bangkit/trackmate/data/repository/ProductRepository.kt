package academy.bangkit.trackmate.data.repository

import academy.bangkit.trackmate.data.remote.response.DetailResponse
import academy.bangkit.trackmate.data.remote.response.ProductsResponse
import academy.bangkit.trackmate.data.remote.retrofit.ApiConfig

class ProductRepository {
    suspend fun getProduct(id: String): DetailResponse {
        return ApiConfig.getApiService().getDetailProduct(id)
    }

    suspend fun getAllProducts(categoryId: Int? = null, keyword: String? = null): ProductsResponse {
        return if (keyword != null) {
            ApiConfig.getApiService().getProductsByKeyword(keyword)
        } else if (categoryId != null) {
            ApiConfig.getApiService().getProductsByCategory(categoryId)
        } else {
            ApiConfig.getApiService().getAllProducts()
        }
    }
}