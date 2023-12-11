package academy.bangkit.trackmate.view.app.account

import academy.bangkit.trackmate.data.remote.response.UserAccountResponse
import academy.bangkit.trackmate.data.repository.UserRepository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class UserAccountViewModel(private val repository: UserRepository) : ViewModel() {

    private val _user = MutableLiveData<UserAccountResponse>()
    val user: LiveData<UserAccountResponse> = _user

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun logout() {
        Log.d("Logout", "Running logout... clearing...")
        viewModelScope.launch {
            _isLoading.value = true
            try {
                //clearing token from server
                val refreshToken = repository.getRefreshToken()
                repository.logout(refreshToken)
            } finally {
                //clearing token from device
                _isLoading.value = false
                repository.clearUserData()
            }
        }
    }

    fun clearLocalData() {
        viewModelScope.launch {
            repository.clearUserData()
        }
    }

    fun getProfile() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val accessToken = repository.getAccessToken()
                _user.value = repository.getUserProfile(accessToken)
            } catch (e: UnknownHostException) {
                _user.value = userError("Terjadi masalah dengan koneksi jaringan")
            } catch (e: Exception) {
                _user.value = userError("Terjadi masalah")
            }
            finally {
                _isLoading.value = false
            }
        }
    }

    private fun userError(message: String): UserAccountResponse {
        return UserAccountResponse(true, null, message)
    }

}