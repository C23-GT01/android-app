package academy.bangkit.trackmate.data.repository

import academy.bangkit.trackmate.data.remote.response.ProductsResponse
import academy.bangkit.trackmate.data.remote.response.UMKMResponse
import academy.bangkit.trackmate.data.remote.retrofit.ApiConfig

class UMKMRepository {
    suspend fun getUMKM(id: String): UMKMResponse {
        return ApiConfig.getApiService().getUMKMProfile(id)
    }

    suspend fun getProductsByUmkm(id: String): ProductsResponse {
        return ApiConfig.getApiService().getProductsByUmkm(id)
    }
}