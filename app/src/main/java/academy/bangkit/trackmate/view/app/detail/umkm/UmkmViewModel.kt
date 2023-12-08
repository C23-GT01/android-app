package academy.bangkit.trackmate.view.app.detail.umkm

import academy.bangkit.trackmate.data.remote.response.HomeResponse
import academy.bangkit.trackmate.data.repository.UMKMRepository
import academy.bangkit.trackmate.data.remote.response.UMKMResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class UmkmViewModel(private val repository: UMKMRepository) : ViewModel() {

    private val _umkm = MutableLiveData<UMKMResponse>()
    val umkm: LiveData<UMKMResponse> = _umkm

    private val _products = MutableLiveData<HomeResponse>()
    val products: LiveData<HomeResponse> = _products

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getUMKMDetailAndProducts(umkmId: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val umkmResponse = repository.getUMKM(umkmId)
                val productResponse = repository.getProductsByUmkm(umkmId)
                _umkm.value = umkmResponse
                _products.value = productResponse
            } catch (e: UnknownHostException) {
                val errorMessage = "Terjadi masalah dengan koneksi jaringan"
                _umkm.value = umkmDetailError(errorMessage)
                _products.value = productError(errorMessage)
            } catch (e: Exception) {
                val errorMessage = e.message ?: "Terjadi masalah"
                _umkm.value = umkmDetailError(errorMessage)
                _products.value = productError(errorMessage)
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun umkmDetailError(message: String): UMKMResponse {
        return UMKMResponse(
            data = null,
            error = true,
            status = message
        )
    }

    private fun productError(message: String): HomeResponse {
        return HomeResponse(
            error = true,
            status = "fail",
            message = message,
            count = 0,
            data = null
        )
    }
}