package academy.bangkit.trackmate.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import academy.bangkit.trackmate.view.app.home.HomeViewModel
import academy.bangkit.trackmate.view.auth.login.LoginViewModel
import academy.bangkit.trackmate.data.pref.UserRepository
import academy.bangkit.trackmate.data.remote.repository.ProductRepository
import academy.bangkit.trackmate.navigation.TrackMateAppViewModel
import academy.bangkit.trackmate.view.app.account.menu.UserAccountViewModel
import academy.bangkit.trackmate.view.app.detail.product.ProductViewModel

class ViewModelFactory(private val repository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository = ProductRepository()) as T
        } else if (modelClass.isAssignableFrom(TrackMateAppViewModel::class.java)) {
            return TrackMateAppViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            return ProductViewModel(repository = ProductRepository()) as T
        } else if (modelClass.isAssignableFrom(UserAccountViewModel::class.java)) {
            return UserAccountViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}