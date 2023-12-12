package academy.bangkit.trackmate.data.remote.request

import com.google.gson.annotations.SerializedName

data class EditProfileRequest(
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("image") val image: String? = null,
    @SerializedName("fullname") val fullname: String
)