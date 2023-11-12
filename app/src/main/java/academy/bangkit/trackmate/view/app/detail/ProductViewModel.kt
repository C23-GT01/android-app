package academy.bangkit.trackmate.view.app.detail

import android.util.Log
import androidx.lifecycle.ViewModel

class ProductViewModel : ViewModel() {
    fun test(productId: String) {
        Log.d("ProductViewModel", "test, id = $productId")
    }
}