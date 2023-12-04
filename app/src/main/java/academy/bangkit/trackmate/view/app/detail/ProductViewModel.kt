package academy.bangkit.trackmate.view.app.detail

import academy.bangkit.trackmate.data.remote.repository.ProductRepository
import academy.bangkit.trackmate.data.remote.response.Data
import academy.bangkit.trackmate.data.remote.response.ProductDetail
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _product = MutableLiveData<ProductDetail>()
    val product: LiveData<ProductDetail> = _product

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    fun getProductDetail(productId: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val productDetail = repository.getProduct(productId)
                _product.value = productDetail
            } catch (e: UnknownHostException) {
                ProductDetail(
                    Data(ProductError.error),
                    true,
                    "Terjadi masalah dengan koneksi jaringan",
                    "unsuccess"
                )
                _product.value = productDetailError("Terjadi masalah dengan koneksi jaringan")
            } catch (e: Exception) {
                _product.value = productDetailError(e.message ?: "Terjadi masalah")
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun productDetailError(message: String): ProductDetail {
        return ProductDetail(Data(ProductError.error), true, message, "unsuccess")
    }
}