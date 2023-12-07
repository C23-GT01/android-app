package academy.bangkit.trackmate.data.remote.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("data")
    val data: DataRegister?
)

data class DataRegister(

    @field:SerializedName("userId")
    val userId: String
)
