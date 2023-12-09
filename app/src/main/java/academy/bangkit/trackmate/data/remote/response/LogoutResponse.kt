package academy.bangkit.trackmate.data.remote.response

import com.google.gson.annotations.SerializedName

// https://github.com/C23-GT01/backend-api#Logout

data class LogoutResponse(

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("status")
	val status: String,

	@field:SerializedName("message")
	val message: String

)
