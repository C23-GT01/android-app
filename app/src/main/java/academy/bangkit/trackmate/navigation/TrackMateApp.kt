package academy.bangkit.trackmate.navigation

import academy.bangkit.trackmate.view.Factory
import academy.bangkit.trackmate.view.app.account.GuestAccountScreen
import academy.bangkit.trackmate.view.app.account.UserAccountScreen
import academy.bangkit.trackmate.view.app.account.UserAccountViewModel
import academy.bangkit.trackmate.view.app.account.menu.EditProfileScreen
import academy.bangkit.trackmate.view.app.account.menu.MyReviewsScreen
import academy.bangkit.trackmate.view.app.account.menu.PersonalInformationScreen
import academy.bangkit.trackmate.view.app.detail.product.ProductDetailScreen
import academy.bangkit.trackmate.view.app.detail.product.ProductViewModel
import academy.bangkit.trackmate.view.app.detail.umkm.UmkmDetailScreen
import academy.bangkit.trackmate.view.app.detail.umkm.UmkmViewModel
import academy.bangkit.trackmate.view.app.home.HomeScreen
import academy.bangkit.trackmate.view.app.home.HomeViewModel
import academy.bangkit.trackmate.view.app.scanner.ScannerScreen
import academy.bangkit.trackmate.view.auth.forgetpassword.ForgetPasswordScreen
import academy.bangkit.trackmate.view.auth.login.LoginScreen
import academy.bangkit.trackmate.view.auth.login.LoginViewModel
import academy.bangkit.trackmate.view.auth.register.RegisterScreen
import academy.bangkit.trackmate.view.auth.register.RegisterViewModel
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.CameraAlt
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import academy.bangkit.trackmate.R

@Composable
fun TrackMateApp(viewModel: TrackMateAppViewModel) {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    viewModel.getSession().observeAsState().value?.let {
        Log.d("Get Session", "User = ${it.username}")
        Log.d("Get Session", "Refresh Token = ${it.refreshToken}")
        Log.d("Get Session", "Is Login = ${it.isLogin}")
        if (!it.isLogin) {
            // TODO: kalo user belom login arahkan ke authentication screen
            Host(false, navController, Screen.Auth.route)

        } else {
            // TODO: kalo user udah login arahkan ke aplikasi utama langsung
            Scaffold(
                topBar = {
                    val disableTopBar = listOf(
                        Screen.App.Home.route,
                        Screen.App.Scanner.route,
                        Screen.App.Account.route,
                        Screen.App.Account.EditProfile.route,
                    )

                    if (currentRoute !in disableTopBar) {
                        TopBar(navController)
                    }
                },
                bottomBar = {
                    BottomBar(navController)
                }
            ) { innerPadding ->
                val isLoggedInAsGuest = it.refreshToken == "Guest"
                Host(isLoggedInAsGuest, navController, Screen.App.route, innerPadding)
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopBar(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    TopAppBar(
        title = {
            val title = when (currentRoute) {
                Screen.App.Detail.route -> stringResource(id = R.string.screen_product_detail)
                Screen.App.UMKM.route -> stringResource(id = R.string.screen_umkm_detail)
                Screen.App.Account.MyReview.route -> stringResource(id = R.string.screen_my_review)
                Screen.App.Account.PersonalInformation.route -> stringResource(id = R.string.screen_about_me)
                else -> stringResource(id = R.string.app_name)
            }
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBackIosNew,
                    contentDescription = ""
                )
            }
        }
    )
}

@Composable
private fun BottomBar(navController: NavHostController) {
    NavigationBar {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            BottomNavigationItem(
                title = stringResource(id = R.string.home),
                icon = Icons.Rounded.Home,
                screen = Screen.App.Home
            ),
            BottomNavigationItem(
                title = stringResource(id = R.string.scanner),
                icon = Icons.Rounded.CameraAlt,
                screen = Screen.App.Scanner
            ),
            BottomNavigationItem(
                title = stringResource(id = R.string.account),
                icon = Icons.Rounded.Person,
                screen = Screen.App.Account
            ),
        )

        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id)
//                        {
//                            saveState = true
//                        }
//                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }

    }
}

@Composable
fun Host(
    isLoggedInAsGuest: Boolean,
    navController: NavHostController,
    startDestination: String,
    innerPadding: PaddingValues = PaddingValues(0.dp)
) {
    val factory = Factory()
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.padding(innerPadding)
    ) {
        //Auth Screen
        navigation(
            startDestination = Screen.Auth.Login.route,
            route = Screen.Auth.route
        ) {
            composable(route = Screen.Auth.Login.route) {
                val viewModel = viewModel<LoginViewModel>(factory = factory)
                LoginScreen(navController = navController, viewModel)
            }

            composable(route = Screen.Auth.Register.route) {
                val viewModel = viewModel<RegisterViewModel>(factory = factory)
                RegisterScreen(navController = navController, viewModel)
            }

            composable(route = Screen.Auth.ForgetPassword.route) {
                ForgetPasswordScreen(navController = navController)
            }
        }

        //Main App Screen
        navigation(
            startDestination = Screen.App.Home.route,
            route = Screen.App.route
        ) {
            composable(route = Screen.App.Home.route) {
                val viewModel = viewModel<HomeViewModel>(factory = factory)
                HomeScreen(navController, viewModel)
            }

            composable(route = Screen.App.Scanner.route) {
                ScannerScreen(navController)
            }

            composable(route = Screen.App.Account.route) {
                val viewModel = viewModel<UserAccountViewModel>(factory = factory)
                if (!isLoggedInAsGuest) {
                    UserAccountScreen(navController, viewModel)
                } else {
                    GuestAccountScreen(navController, viewModel)
                }
            }

            composable(
                route = Screen.App.Detail.route, arguments = listOf(
                    navArgument("id") { type = NavType.StringType }
                )
            ) {
                val viewModel = viewModel<ProductViewModel>(factory = factory)
                val id = it.arguments?.getString("id") ?: "-"
                ProductDetailScreen(navController = navController, id = id, viewModel = viewModel)
            }

            composable(route = Screen.App.Account.MyReview.route) {
                MyReviewsScreen()
            }

            composable(route = Screen.App.Account.EditProfile.route) {
                EditProfileScreen()
            }

            composable(route = Screen.App.Account.PersonalInformation.route) {
                PersonalInformationScreen(navController = navController)
            }

            composable(
                route = Screen.App.UMKM.route, arguments = listOf(
                    navArgument("id") { type = NavType.StringType }
                )
            ) {
                val viewModel = viewModel<UmkmViewModel>(factory = factory)
                val id = it.arguments?.getString("id") ?: "-"
                UmkmDetailScreen(
                    navController = navController,
                    id = id,
                    viewModel = viewModel
                )
            }
        }
    }
}

private data class BottomNavigationItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)