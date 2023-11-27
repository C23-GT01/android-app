package academy.bangkit.trackmate.data.remote.repository

import academy.bangkit.trackmate.data.remote.response.ProductDetail
import academy.bangkit.trackmate.data.remote.retrofit.ApiConfig

class ProductRepository {
    suspend fun getProduct(id: String): ProductDetail {
        return ApiConfig.getApiService().getDetailProduct(id)
    }
}