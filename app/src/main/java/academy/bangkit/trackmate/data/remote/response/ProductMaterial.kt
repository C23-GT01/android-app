package academy.bangkit.trackmate.data.remote.response

import com.google.gson.annotations.SerializedName

data class ProductMaterial(

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("location")
    val location: Location,

    @field:SerializedName("deskripsi")
    val description: String,

    @field:SerializedName("umkm")
    val productBy: String? = null
)