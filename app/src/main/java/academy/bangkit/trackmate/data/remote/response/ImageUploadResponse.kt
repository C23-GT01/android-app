package academy.bangkit.trackmate.data.remote.response

import com.google.gson.annotations.SerializedName

data class ImageUploadResponse(

    @field:SerializedName("data")
    val data: DataImage?,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("status")
    val status: String
)

data class DataImage(

    @field:SerializedName("fileLocation")
    val fileLocation: String
)
