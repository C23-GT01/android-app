package academy.bangkit.trackmate.data.remote.response

import com.google.gson.annotations.SerializedName

data class EditProfileResponse(

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)
