package academy.bangkit.trackmate.view.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import academy.bangkit.trackmate.data.pref.UserRepository
import academy.bangkit.trackmate.data.pref.UserModel
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }
}