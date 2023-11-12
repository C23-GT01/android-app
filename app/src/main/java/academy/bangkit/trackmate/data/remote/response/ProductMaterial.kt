package academy.bangkit.trackmate.data.remote.response

data class ProductMaterial(
    val material: String,
    val picture: String,
    val location: String,
    val manufacturer: String? = null
)