package academy.bangkit.trackmate.view.app.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import academy.bangkit.trackmate.data.UserRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: UserRepository) : ViewModel() {
    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}