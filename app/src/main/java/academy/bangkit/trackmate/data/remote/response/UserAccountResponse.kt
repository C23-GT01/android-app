package academy.bangkit.trackmate.data.remote.response

import com.google.gson.annotations.SerializedName

data class UserAccountResponse(

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("data")
    val data: UserData?,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class User(

    @field:SerializedName("image")
    val image: String?,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("fullname")
    val fullname: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("username")
    val username: String
)

data class UserData(

    @field:SerializedName("user")
    val user: User
)
