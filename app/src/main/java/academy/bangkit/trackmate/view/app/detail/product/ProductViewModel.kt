package academy.bangkit.trackmate.view.app.detail.product

import academy.bangkit.trackmate.data.remote.repository.ProductRepository
import academy.bangkit.trackmate.data.remote.response.DetailResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _product = MutableLiveData<DetailResponse>()
    val product: LiveData<DetailResponse> = _product

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getProductDetail(productId: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val productDetail = repository.getProduct(productId)
                _product.value = productDetail
            } catch (e: UnknownHostException) {
                DetailResponse(
                    error = true,
                    status = "fail",
                    message = "Terjadi masalah dengan koneksi jaringan",
                    data = null
                )
                _product.value = productDetailError("Terjadi masalah dengan koneksi jaringan")
            } catch (e: Exception) {
                _product.value = productDetailError(e.message ?: "Terjadi masalah")
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun productDetailError(message: String): DetailResponse {
        return DetailResponse(null, true, message, "fail")
    }
}