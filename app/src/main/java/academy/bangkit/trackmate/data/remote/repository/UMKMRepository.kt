package academy.bangkit.trackmate.data.remote.repository

import academy.bangkit.trackmate.data.remote.response.UMKMResponse
import academy.bangkit.trackmate.data.remote.retrofit.ApiConfig

class UMKMRepository {
    suspend fun getUMKM(id: String): UMKMResponse {
        return ApiConfig.getApiService().getUMKMProfile(id)
    }
}