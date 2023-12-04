package academy.bangkit.trackmate.view.app.home

import androidx.lifecycle.ViewModel
import academy.bangkit.trackmate.data.remote.repository.ProductRepository
import academy.bangkit.trackmate.data.remote.response.HomeResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class HomeViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _product = MutableLiveData<HomeResponse>()
    val product: LiveData<HomeResponse> = _product

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getAllProducts() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val productDetail = repository.getAllProducts()
                _product.value = productDetail
            } catch (e: UnknownHostException) {
                HomeResponse(
                    null,
                    0,
                    true,
                    "Terjadi masalah dengan koneksi jaringan",
                    "fail"
                )
                _product.value = productDetailError("Terjadi masalah dengan koneksi jaringan")
            } catch (e: Exception) {
                _product.value = productDetailError(e.message ?: "Terjadi masalah")
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun productDetailError(message: String): HomeResponse {
        return HomeResponse(null, 0, true, message, "fail")
    }

//    UserRepository
//    fun logout() {
//        viewModelScope.launch {
//            repository.logout()
//        }
//    }
}