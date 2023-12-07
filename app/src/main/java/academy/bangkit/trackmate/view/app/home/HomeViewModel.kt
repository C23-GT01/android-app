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

    private val _product = MutableLiveData<HomeResponse>()
    val product: LiveData<HomeResponse> = _product

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getAllProducts(categoryId: Int? = null, keyword: String? = null) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val productDetail = repository.getAllProducts(categoryId, keyword)
                _product.value = productDetail
            } catch (e: UnknownHostException) {
                _product.value = detailError("Terjadi masalah dengan koneksi jaringan")
            } catch (e: Exception) {
                _product.value = detailError(e.message ?: "Terjadi masalah")
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