package academy.bangkit.trackmate.data.repository

import academy.bangkit.trackmate.data.pref.UserModel
import academy.bangkit.trackmate.data.pref.UserPreference
import academy.bangkit.trackmate.data.remote.request.EditProfileRequest
import academy.bangkit.trackmate.data.remote.request.LoginRequest
import academy.bangkit.trackmate.data.remote.request.LogoutRequest
import academy.bangkit.trackmate.data.remote.request.RegisterRequest
import academy.bangkit.trackmate.data.remote.request.UpdateAccesTokenRequest
import academy.bangkit.trackmate.data.remote.response.EditProfileResponse
import academy.bangkit.trackmate.data.remote.response.ImageUploadResponse
import academy.bangkit.trackmate.data.remote.response.LoginResponse
import academy.bangkit.trackmate.data.remote.response.LogoutResponse
import academy.bangkit.trackmate.data.remote.response.RegisterResponse
import academy.bangkit.trackmate.data.remote.response.RenewTokenResponse
import academy.bangkit.trackmate.data.remote.response.UserAccountResponse
import academy.bangkit.trackmate.data.remote.retrofit.ApiConfig
import academy.bangkit.trackmate.view.parseErrorMessage
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.MultipartBody
import okio.IOException
import retrofit2.Response
import java.net.UnknownHostException

class UserRepository private constructor(
    private val userPreference: UserPreference
) {
    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    suspend fun saveNewAccessToken(accessToken: String) {
        userPreference.saveNewAccessToken(accessToken)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    fun getRefreshToken(): String {
        return runBlocking {
            userPreference.getRefreshToken().first()
        }
    }

    fun getAccessToken(): String {
        return runBlocking {
            userPreference.getAccessToken().first()
        }
    }

    suspend fun clearUserData() {
        userPreference.logout()
    }

    suspend fun logout(refreshToken: String): LogoutResponse {
        val logoutRequest = LogoutRequest(refreshToken)
        return ApiConfig.getApiService().logout(logoutRequest)
    }

    private suspend fun <T> handleApiRequest(apiCall: suspend () -> Response<T>): T {
        try {
            val response = apiCall.invoke()
            if (response.isSuccessful) {
                return response.body() ?: throw IOException("Response body is null")
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = parseErrorMessage(errorBody)
                throw IOException(errorMessage)
            }
        } catch (e: UnknownHostException) {
            throw IOException("Terjadi masalah dengan koneksi jaringan")
        } catch (e: Exception) {
            throw IOException(e.message ?: "Terjadi masalah")
        }
    }

    suspend fun login(username: String, password: String): LoginResponse {
        val loginRequest = LoginRequest(username, password)

        return handleApiRequest {
            ApiConfig.getApiService().postLogin(loginRequest)
        }
    }

    suspend fun register(
        username: String, email: String, password: String, fullname: String
    ): RegisterResponse {
        val registerRequest = RegisterRequest(username, email, password, fullname)

        return handleApiRequest {
            ApiConfig.getApiService().postRegister(registerRequest)
        }
    }

    suspend fun getUserProfile(accessToken: String): UserAccountResponse {
        return handleApiRequest { ApiConfig.getApiService(accessToken).getUserProfile() }
    }

    suspend fun renewAccessToken(refreshToken: String): RenewTokenResponse {
        val renewRequest = UpdateAccesTokenRequest(refreshToken)
        return ApiConfig.getApiService().renewAccessToken(renewRequest)
    }

    suspend fun editProfile(accessToken: String, request: EditProfileRequest): EditProfileResponse {
        Log.d("EditProfil", "Repository hitted")
        return ApiConfig.getApiService(accessToken).editProfile(request)
    }

    suspend fun uploadImage(accessToken: String, file: MultipartBody.Part): ImageUploadResponse {
        return ApiConfig.getApiService(accessToken).uploadImages(file)
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            userPreference: UserPreference
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference)
            }.also { instance = it }
    }
}