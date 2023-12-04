package academy.bangkit.trackmate.navigation

import academy.bangkit.trackmate.R
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import academy.bangkit.trackmate.di.Injection
import academy.bangkit.trackmate.view.ViewModelFactory
import academy.bangkit.trackmate.view.auth.forgetpassword.ForgetPasswordScreen
import academy.bangkit.trackmate.view.auth.login.LoginScreen
import academy.bangkit.trackmate.view.auth.register.RegisterScreen
import academy.bangkit.trackmate.view.app.home.HomeScreen
import academy.bangkit.trackmate.view.app.ScreenB
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import academy.bangkit.trackmate.view.app.account.UserAccountScreen
import academy.bangkit.trackmate.view.app.account.menu.EditProfileScreen
import academy.bangkit.trackmate.view.app.account.menu.MyReviewsScreen
import academy.bangkit.trackmate.view.app.account.menu.PersonalInformationScreen
import academy.bangkit.trackmate.view.app.detail.ProductDetailScreen
import academy.bangkit.trackmate.view.app.detail.ProductViewModel
import academy.bangkit.trackmate.view.app.home.HomeViewModel
import academy.bangkit.trackmate.view.app.scanner.ScannerScreen
import academy.bangkit.trackmate.view.auth.login.LoginViewModel
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource

@Composable
fun TrackMateApp(viewModel: TrackMateAppViewModel) {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    viewModel.getSession().observeAsState().value?.let {
        if (!it.isLogin) {

            // TODO: kalo user belom login arahkan ke authentication screen
            Host(navController, Screen.Auth.route)

        } else {
            // TODO: kalo user udah login arahkan ke aplikasi utama langsung
            Scaffold(
                topBar = {
                    if (currentRoute !in listOf(
                            Screen.App.Home.route,
                            Screen.App.Account.route,
                            Screen.App.Account.MyReview.route,
                            Screen.App.Account.EditProfile.route,
                            Screen.App.Account.PersonalInformation.route
                        )
                    ) {
                        TopBar(navController)
                    }
                },
                bottomBar = {
                    BottomBar(navController)
                }
            ) { innerPadding ->
                Host(navController, Screen.App.route, innerPadding)
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
                Screen.App.Home.route -> "Halo Tracker!"
                Screen.App.Scanner.route -> "Scan"
                Screen.App.Account.route -> "Akun"
                Screen.App.Detail.route -> "Detail Produk"
                else -> "TrackMate"
            }
            Text(text = title)
        }
    )
}

@Composable
private fun BottomBar(navController: NavHostController) {
    NavigationBar {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = "Home",
                icon = Icons.Rounded.Home,
                screen = Screen.App.Home
            ),
            NavigationItem(
                title = "Scan",
                icon = ImageVector.vectorResource(id = R.drawable.scan),
                screen = Screen.App.Scanner
            ),
            NavigationItem(
                title = "Akun",
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
    navController: NavHostController,
    startDestination: String,
    innerPadding: PaddingValues = PaddingValues(0.dp)
) {
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
                val viewModel = viewModel<LoginViewModel>(
                    factory = ViewModelFactory(
                        Injection.provideUserRepository(
                            LocalContext.current
                        )
                    )
                )
                LoginScreen(navController = navController, viewModel)
            }

            composable(route = Screen.Auth.Register.route) {
                RegisterScreen(navController = navController)
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
                val viewModel = viewModel<HomeViewModel>(
                    factory = ViewModelFactory(
                        Injection.provideUserRepository(
                            LocalContext.current
                        )
                    )
                )
                HomeScreen(navController, viewModel)
            }

            composable(route = Screen.App.B.route) {
                ScreenB(navController)
            }

            composable(route = Screen.App.Scanner.route) {
                ScannerScreen(navController)
            }

            composable(route = Screen.App.Account.route) {
                UserAccountScreen(navController)
            }

            composable(
                route = Screen.App.Detail.route, arguments = listOf(
                    navArgument("id") { type = NavType.StringType }
                )
            ) {
                val viewModel = viewModel<ProductViewModel>(
                    factory = ViewModelFactory(
                        Injection.provideUserRepository(
                            LocalContext.current
                        )
                    )
                )
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
        }
    }
}