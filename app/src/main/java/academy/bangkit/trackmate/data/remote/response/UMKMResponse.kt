package academy.bangkit.trackmate.data.remote.response

import com.google.gson.annotations.SerializedName

// https://github.com/C23-GT01/backend-api#get-detail-umkm

data class UMKMResponse(

	@field:SerializedName("data")
	val data: DataUMKM?,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("status")
	val status: String
)

data class DataUMKM(

	@field:SerializedName("umkm")
	val umkm: Umkm
)

data class ContactItem(

	@field:SerializedName("phone")
	val phone: Phone,

	@field:SerializedName("email")
	val email: String
)

data class Phone(

	@field:SerializedName("phoneNumber")
	val phoneNumber: String,

	@field:SerializedName("isWhatsApp")
	val isWhatsApp: Boolean,

	@field:SerializedName("waNumber")
	val waNumber: String
)

data class History(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("text")
	val text: String
)

data class Umkm(

	@field:SerializedName("owner")
	val owner: String,

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("impact")
	val impact: List<ImpactItem>,

	@field:SerializedName("contact")
	val contact: List<ContactItem>?,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("logo")
	val logo: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("location")
	val location: Location,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("history")
	val history: History,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
