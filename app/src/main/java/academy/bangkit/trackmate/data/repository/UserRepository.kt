package academy.bangkit.trackmate.data.repository

import academy.bangkit.trackmate.data.pref.UserModel
import academy.bangkit.trackmate.data.pref.UserPreference
import academy.bangkit.trackmate.data.remote.request.LoginRequest
import academy.bangkit.trackmate.data.remote.request.LogoutRequest
import academy.bangkit.trackmate.data.remote.request.RegisterRequest
import academy.bangkit.trackmate.data.remote.response.LoginResponse
import academy.bangkit.trackmate.data.remote.response.LogoutResponse
import academy.bangkit.trackmate.data.remote.response.RegisterResponse
import academy.bangkit.trackmate.data.remote.retrofit.ApiConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class UserRepository private constructor(
    private val userPreference: UserPreference
) {
    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    fun getRefreshToken(): String {
        return runBlocking {
            userPreference.getRefreshToken().first()
        }
    }

    suspend fun clearUserData() {
        userPreference.logout()
    }

    suspend fun logout(refreshToken: String): LogoutResponse {
        val logoutRequest = LogoutRequest(refreshToken)
        return ApiConfig.getApiService().logout(logoutRequest)
    }

    suspend fun login(username: String, password: String): LoginResponse {
        val loginRequest = LoginRequest(username, password)
        return ApiConfig.getApiService().postLogin(loginRequest)
    }

    suspend fun register(
        username: String, email: String, password: String, fullname: String
    ): RegisterResponse {
        val registerRequest = RegisterRequest(username, email, password, fullname)
        return ApiConfig.getApiService().postRegister(registerRequest)
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