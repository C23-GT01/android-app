package academy.bangkit.trackmate.data.remote.response

import com.google.gson.annotations.SerializedName

data class RenewTokenResponse(

    @field:SerializedName("data")
    val data: DataAccessToken?,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class DataAccessToken(

    @field:SerializedName("accessToken")
    val accessToken: String
)
