package academy.bangkit.trackmate.view.app.account

import academy.bangkit.trackmate.data.remote.request.EditProfileRequest
import academy.bangkit.trackmate.data.remote.response.EditProfileResponse
import academy.bangkit.trackmate.data.remote.response.ImageUploadResponse
import academy.bangkit.trackmate.data.remote.response.UserAccountResponse
import academy.bangkit.trackmate.data.repository.UserRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import java.net.UnknownHostException

class UserAccountViewModel(private val repository: UserRepository) : ViewModel() {

    private val _user = MutableLiveData<UserAccountResponse>()
    val user: LiveData<UserAccountResponse> = _user

    private val _userEdit = MutableLiveData<EditProfileResponse>()
    val userEdit: LiveData<EditProfileResponse> = _userEdit

    private val _newProfilePicture = MutableLiveData<ImageUploadResponse?>()
    val newProfilePicture: LiveData<ImageUploadResponse?> = _newProfilePicture

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun logout() {
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
            } catch (e: Exception) {
                if (e.message == "Token maximum age exceeded") { //Renew Access Token
                    val refreshToken = repository.getRefreshToken()
                    val renew = repository.renewAccessToken(refreshToken)
                    if (!renew.error && renew.data != null) {
                        val newAccessToken = renew.data.accessToken
                        repository.saveNewAccessToken(newAccessToken)
                        _user.value = repository.getUserProfile(newAccessToken)
                    } else {
                        //Gagal Update Token LOGOUT!!
                        repository.clearUserData()
                    }
                } else {
                    _user.value = userError(e.message ?: "Terjadi Masalah")
                }
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun editProfile(profile: EditProfileRequest) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val accessToken = repository.getAccessToken()
                val editProfile = repository.editProfile(accessToken, profile)
                _userEdit.value = editProfile
                _errorMessage.value = ""
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Terjadi masalah"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun uploadImage(file: MultipartBody.Part) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val accessToken = repository.getAccessToken()
                val response = repository.uploadImage(accessToken, file)
                _newProfilePicture.value = response
                _errorMessage.value = ""
            } catch (e: UnknownHostException) {
                _errorMessage.value = "Terjadi masalah dengan koneksi jaringan"
                _newProfilePicture.value = ImageUploadResponse(null, true, "fail")
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Terjadi masalah"
                _newProfilePicture.value = ImageUploadResponse(null, true, "fail")
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun userError(message: String): UserAccountResponse {
        return UserAccountResponse(true, null, message, "fail")
    }

    fun handleEditError(message: String) {
        _newProfilePicture.value = null
        _userEdit.value = EditProfileResponse(true, message, "fail")
    }
}