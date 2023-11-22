package academy.bangkit.trackmate.data.remote.retrofit

import academy.bangkit.trackmate.data.remote.response.ProductDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("products/{productId}")
    suspend fun getDetailProduct(
        @Path("productId") detail: String,
    ): ProductDetail
}