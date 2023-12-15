package academy.bangkit.trackmate.data.remote.retrofit

import academy.bangkit.trackmate.data.remote.request.EditProfileRequest
import academy.bangkit.trackmate.data.remote.request.LoginRequest
import academy.bangkit.trackmate.data.remote.request.LogoutRequest
import academy.bangkit.trackmate.data.remote.request.RegisterRequest
import academy.bangkit.trackmate.data.remote.request.UpdateAccesTokenRequest
import academy.bangkit.trackmate.data.remote.response.DetailResponse
import academy.bangkit.trackmate.data.remote.response.EditProfileResponse
import academy.bangkit.trackmate.data.remote.response.ImageUploadResponse
import academy.bangkit.trackmate.data.remote.response.ProductsResponse
import academy.bangkit.trackmate.data.remote.response.LoginResponse
import academy.bangkit.trackmate.data.remote.response.LogoutResponse
import academy.bangkit.trackmate.data.remote.response.RecommendationResponse
import academy.bangkit.trackmate.data.remote.response.RegisterResponse
import academy.bangkit.trackmate.data.remote.response.UMKMResponse
import academy.bangkit.trackmate.data.remote.response.RenewTokenResponse
import academy.bangkit.trackmate.data.remote.response.UserAccountResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    @GET("products/{productId}")
    suspend fun getDetailProduct(
        @Path("productId") detail: String,
    ): DetailResponse

    @GET("products")
    suspend fun getAllProducts(): ProductsResponse

    @GET("products/category/{categoryId}")
    suspend fun getProductsByCategory(
        @Path("categoryId") category: Int
    ): ProductsResponse

    @GET("products/search/{keyword}")
    suspend fun getProductsByKeyword(
        @Path("keyword") keyword: String
    ): ProductsResponse

    @GET("products/umkm/{umkmId}")
    suspend fun getProductsByUmkm(
        @Path("umkmId") keyword: String
    ): ProductsResponse

    @GET("umkm/{umkmId}")
    suspend fun getUMKMProfile(
        @Path("umkmId") detail: String,
    ): UMKMResponse

    @POST("authentications")
    suspend fun postLogin(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @POST("users")
    suspend fun postRegister(
        @Body request: RegisterRequest
    ): Response<RegisterResponse>

    @HTTP(method = "DELETE", path = "authentications", hasBody = true)
    suspend fun logout(
        @Body request: LogoutRequest
    ): LogoutResponse

    @GET("users/profile")
    suspend fun getUserProfile(): Response<UserAccountResponse>

    @PUT("authentications")
    suspend fun renewAccessToken(
        @Body request: UpdateAccesTokenRequest
    ): RenewTokenResponse

    @PUT("users")
    suspend fun editProfile(
        @Body request: EditProfileRequest
    ): Response<EditProfileResponse>

    @Multipart
    @POST("upload/images")
    suspend fun uploadImages(
        @Part file: MultipartBody.Part,
    ): ImageUploadResponse

    @GET("recomendation")
    suspend fun getProductsRecommendation(): RecommendationResponse
}