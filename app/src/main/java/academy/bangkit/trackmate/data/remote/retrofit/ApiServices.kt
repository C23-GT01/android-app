package academy.bangkit.trackmate.data.remote.retrofit

import academy.bangkit.trackmate.data.remote.request.LoginRequest
import academy.bangkit.trackmate.data.remote.request.RegisterRequest
import academy.bangkit.trackmate.data.remote.response.DetailResponse
import academy.bangkit.trackmate.data.remote.response.HomeResponse
import academy.bangkit.trackmate.data.remote.response.LoginResponse
import academy.bangkit.trackmate.data.remote.response.RegisterResponse
import academy.bangkit.trackmate.data.remote.response.UMKMResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("products/{productId}")
    suspend fun getDetailProduct(
        @Path("productId") detail: String,
    ): DetailResponse

    @GET("products")
    suspend fun getAllProducts(): HomeResponse

    @GET("products/{categoryId}")
    suspend fun getProductsByCategory(
        @Path("categoryId") category: Int
    ): HomeResponse

    @GET("products/search/{keyword}")
    suspend fun getProductsByKeyword(
        @Path("keyword") keyword: String
    ): HomeResponse

    @GET("umkm/{umkmId}")
    suspend fun getUMKMProfile(
        @Path("umkmId") detail: String,
    ): UMKMResponse

    @POST("authentications")
    suspend fun postLogin(
        @Body request: LoginRequest
    ): LoginResponse

    @POST("users")
    suspend fun postRegister(
        @Body request: RegisterRequest
    ): RegisterResponse
}