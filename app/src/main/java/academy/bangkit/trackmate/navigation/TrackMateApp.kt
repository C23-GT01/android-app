package academy.bangkit.trackmate.navigation

import academy.bangkit.trackmate.data.remote.response.ImpactItem
import academy.bangkit.trackmate.data.remote.response.Location
import academy.bangkit.trackmate.data.remote.response.ProductItem
import academy.bangkit.trackmate.data.remote.response.ProductMaterial
import academy.bangkit.trackmate.data.remote.response.ProductionItem
import academy.bangkit.trackmate.data.remote.response.UMKM
import academy.bangkit.trackmate.di.Injection
import academy.bangkit.trackmate.view.ViewModelFactory
import academy.bangkit.trackmate.view.app.ScreenB
import academy.bangkit.trackmate.view.app.account.UserAccountScreen
import academy.bangkit.trackmate.view.app.account.menu.EditProfileScreen
import academy.bangkit.trackmate.view.app.account.menu.MyReviewsScreen
import academy.bangkit.trackmate.view.app.account.menu.PersonalInformationScreen
import academy.bangkit.trackmate.view.app.account.menu.UserAccountViewModel
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
import androidx.compose.ui.platform.LocalContext
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
import okhttp3.internal.immutableListOf

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
                Screen.App.Detail.route -> "Detail Produk"
                Screen.App.UMKM.route -> "Detail UMKM"
                Screen.App.Account.MyReview.route -> "Review Saya"
                Screen.App.Account.PersonalInformation.route -> "Tentang Saya"
                else -> "TrackMate"
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
                title = "Home",
                icon = Icons.Rounded.Home,
                screen = Screen.App.Home
            ),
            BottomNavigationItem(
                title = "Scan",
                icon = Icons.Rounded.CameraAlt,
                screen = Screen.App.Scanner
            ),
            BottomNavigationItem(
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
                val viewModel = viewModel<UserAccountViewModel>(
                    factory = ViewModelFactory(
                        Injection.provideUserRepository(
                            LocalContext.current
                        )
                    )
                )
                UserAccountScreen(navController, viewModel)
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

            composable(
                route = Screen.App.UMKM.route, arguments = listOf(
                    navArgument("id") { type = NavType.StringType }
                )
            ) {
                val viewModel = viewModel<UmkmViewModel>(
                    factory = ViewModelFactory(
                        Injection.provideUserRepository(
                            LocalContext.current
                        )
                    )
                )
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

private object Sample {
    val sampleProduct = ProductItem(
        image = "https://picsum.photos/200",
        contribution = immutableListOf(1, 2, 3),
        production = immutableListOf(
            ProductionItem("https://picsum.photos/100", "Title 1", "Description 1"),
            ProductionItem("https://picsum.photos/200", "Title 2", "Description 2"),
            ProductionItem("https://picsum.photos/300", "Title 3", "Description 3"),
            ProductionItem("https://picsum.photos/400", "Title 4", "Description 4"),
        ),
        price = 100000,
        impact = immutableListOf(
            ImpactItem(
                "https://picsum.photos/500",
                "Impact Title 1",
                "Description of Impact 1"
            ),
            ImpactItem(
                "https://picsum.photos/600",
                "Impact Title 2",
                "Description of Impact 2"
            ),
            ImpactItem(
                "https://picsum.photos/700",
                "Impact Title 3",
                "Description of Impact 3"
            ),
            ImpactItem(
                "https://picsum.photos/800",
                "Impact Title 4",
                "Description of Impact 4"
            ),
            ImpactItem(
                "https://picsum.photos/900",
                "Impact Title 5",
                "Description of Impact 5"
            ),
        ),
        name = "Product Name",
        description = "Description of Product",
        resources = immutableListOf(
            ProductMaterial(
                "https://picsum.photos/201",
                "Material 1",
                Location(0.0, 0.0, "Wonogiri, Jawa Tengah"),
                "Description of this resource."
            ),
            ProductMaterial(
                "https://picsum.photos/202",
                "Material 1",
                Location(0.0, 0.0, "Wonogiri, Jawa Tengah"),
                "Description of this resource."
            ),
            ProductMaterial(
                "https://picsum.photos/203",
                "Material 1",
                Location(0.0, 0.0, "Wonogiri, Jawa Tengah"),
                "Description of this resource."
            ),
            ProductMaterial(
                "https://picsum.photos/204",
                "Material 1",
                Location(0.0, 0.0, "Wonogiri, Jawa Tengah"),
                "Description of this resource."
            ),
        ),
        id = "sampleIdOfProduct",
        productBy = UMKM(
            "sampleIdOfUMKM",
            "https://picsum.photos/200",
            21,
            "Nama UMKM",
            Location(0.0, 0.0, "Wonogiri, Jawa Tengah")
        )
    )
}