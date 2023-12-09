package academy.bangkit.trackmate.data.remote.response

import com.google.gson.annotations.SerializedName

// https://github.com/C23-GT01/backend-api#login

data class LoginResponse(

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("data")
    val token: Token?
)

data class Token(

    @field:SerializedName("accessToken")
    val access: String,

    @field:SerializedName("refreshToken")
    val refresh: String
)
