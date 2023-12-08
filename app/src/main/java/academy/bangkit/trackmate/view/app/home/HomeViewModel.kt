package academy.bangkit.trackmate.view.app.home

import androidx.lifecycle.ViewModel
import academy.bangkit.trackmate.data.repository.ProductRepository
import academy.bangkit.trackmate.data.remote.response.HomeResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class HomeViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _products = MutableLiveData<HomeResponse>()
    val products: LiveData<HomeResponse> = _products

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getAllProducts(categoryId: Int? = null, keyword: String? = null) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val products = repository.getAllProducts(categoryId, keyword)
                _products.value = products
            } catch (e: UnknownHostException) {
                _products.value = detailError("Terjadi masalah dengan koneksi jaringan")
            } catch (e: Exception) {
                _products.value = detailError(e.message ?: "Terjadi masalah")
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun detailError(message: String): HomeResponse {
        return HomeResponse(
            error = true,
            status = "fail",
            message = message,
            count = 0,
            data = null
        )
    }
}