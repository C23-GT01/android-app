package academy.bangkit.trackmate.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import academy.bangkit.trackmate.data.pref.UserRepository
import academy.bangkit.trackmate.data.pref.UserModel
import androidx.lifecycle.asLiveData

class TrackMateAppViewModel(private val repository: UserRepository) : ViewModel() {
    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }
}