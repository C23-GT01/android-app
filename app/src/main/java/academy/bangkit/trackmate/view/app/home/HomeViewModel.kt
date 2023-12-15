package academy.bangkit.trackmate.view.app.home

import androidx.lifecycle.ViewModel
import academy.bangkit.trackmate.data.repository.ProductRepository
import academy.bangkit.trackmate.data.remote.response.ProductsResponse
import academy.bangkit.trackmate.data.remote.response.RecommendationResponse
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class HomeViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _products = MutableLiveData<ProductsResponse>()
    val products: LiveData<ProductsResponse> = _products

    private val _productsRecommendation = MutableLiveData<RecommendationResponse>()
    val productsRecommendation: LiveData<RecommendationResponse> = _productsRecommendation

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

    fun getAllRecommendation() {
        viewModelScope.launch {
            try {
                Log.d("getAllRecommendation", "Hitted")
                val productsRecommendation = repository.getProductsRecommendation()
                _productsRecommendation.value = productsRecommendation
            } catch (e: Exception) {
                Log.d("Error", "Error getting recommendation")
            }
        }
    }

    private fun detailError(message: String): ProductsResponse {
        return ProductsResponse(
            isError = true,
            status = "fail",
            message = message,
            count = 0,
            products = null
        )
    }
}