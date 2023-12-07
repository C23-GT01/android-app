package academy.bangkit.trackmate.view.app.detail.umkm

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

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getUMKMDetail(umkmId: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val detail = repository.getUMKM(umkmId)
                _umkm.value = detail
            } catch (e: UnknownHostException) {
                _umkm.value = umkmDetailError("Terjadi masalah dengan koneksi jaringan")
            } catch (e: Exception) {
                _umkm.value = umkmDetailError(e.message ?: "Terjadi masalah")
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
}