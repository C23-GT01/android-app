package academy.bangkit.trackmate.view.auth.register

import academy.bangkit.trackmate.data.remote.response.RegisterResponse
import academy.bangkit.trackmate.data.repository.UserRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {

    private val _registerResponse = MutableLiveData<RegisterResponse>()
    val registerResponse: LiveData<RegisterResponse> = _registerResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun register(username: String, email: String, password: String, fullname: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.register(username, email, password, fullname)
                _registerResponse.value = response
            } catch (e: UnknownHostException) {
                _registerResponse.value = loginError("Terjadi masalah dengan koneksi jaringan")
            } catch (e: Exception) {
                _registerResponse.value = loginError(e.message ?: "Terjadi masalah")
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun loginError(message: String): RegisterResponse {
        return RegisterResponse(true, "fail", message, null)
    }

    fun clearErrorMessage() {
        _registerResponse.value = RegisterResponse(true, "", "", null)
    }

}