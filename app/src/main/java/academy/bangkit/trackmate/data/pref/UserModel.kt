package academy.bangkit.trackmate.data.pref

data class UserModel(
    val username: String,
    val refreshToken: String,
    val isLogin: Boolean = false
)