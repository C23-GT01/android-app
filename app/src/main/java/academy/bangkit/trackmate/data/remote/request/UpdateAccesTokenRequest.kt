package academy.bangkit.trackmate.data.remote.request

import com.google.gson.annotations.SerializedName

data class UpdateAccesTokenRequest(
    @SerializedName("refreshToken") val refreshToken: String
)