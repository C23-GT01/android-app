package academy.bangkit.trackmate.data.remote.retrofit

import academy.bangkit.trackmate.data.remote.response.DetailResponse
import academy.bangkit.trackmate.data.remote.response.HomeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("products/{productId}")
    suspend fun getDetailProduct(
        @Path("productId") detail: String,
    ): DetailResponse

    @GET("products")
    suspend fun getAllProducts(): HomeResponse
}