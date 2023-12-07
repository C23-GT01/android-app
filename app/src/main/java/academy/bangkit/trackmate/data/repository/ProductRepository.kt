package academy.bangkit.trackmate.data.repository

import academy.bangkit.trackmate.data.remote.response.DetailResponse
import academy.bangkit.trackmate.data.remote.response.HomeResponse
import academy.bangkit.trackmate.data.remote.retrofit.ApiConfig

class ProductRepository {
    suspend fun getProduct(id: String): DetailResponse {
        return ApiConfig.getApiService().getDetailProduct(id)
    }

    suspend fun getAllProducts(): HomeResponse {
        return ApiConfig.getApiService().getAllProducts()
    }
}