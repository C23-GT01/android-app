package academy.bangkit.trackmate.view.app.account

import academy.bangkit.trackmate.data.repository.UserRepository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserAccountViewModel(private val repository: UserRepository) : ViewModel() {

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
}