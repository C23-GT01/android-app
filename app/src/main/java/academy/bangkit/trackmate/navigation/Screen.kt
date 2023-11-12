package academy.bangkit.trackmate.navigation

sealed class Screen(val route: String) {
    object Auth : Screen(route = "auth") {
        object Login : Screen(route = "login")
        object Register : Screen(route = "register")
        object ForgetPassword : Screen(route = "forgetPassword")
    }

    object App : Screen(route = "app") {
        object Home : Screen(route = "home")
        object Scanner : Screen(route = "scanner")
        object Account : Screen(route = "account")
        object A : Screen(route = "a")
        object B : Screen(route = "b")
        object Detail : Screen(route = "detail/{id}") {
            fun createRoute(id: String) = "detail/$id"
        }
    }
}