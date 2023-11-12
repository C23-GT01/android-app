package academy.bangkit.trackmate.view.app.home

import academy.bangkit.trackmate.di.Injection
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import academy.bangkit.trackmate.navigation.Screen
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import academy.bangkit.trackmate.view.ViewModelFactory
import androidx.compose.material3.Surface
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Text(
                text = "You're in Home",
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Button(
                onClick = {
                    viewModel.logout()
                    navController.navigate(Screen.Auth.route) {
                        popUpTo(Screen.App.Home.route) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Logout")
            }
            Button(
                onClick = {
                    navController.navigate(Screen.App.Detail.createRoute("id")) {
                        popUpTo(Screen.App.Scanner.route) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Product Detail (Sample)")
            }
            Button(
                onClick = {
                    navController.navigate(Screen.App.A.route)
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Screen A")
            }
            Button(
                onClick = {
                    navController.navigate(Screen.App.B.route)
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Screen B")
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    TrackMateTheme {
        Surface {
            val viewModel = viewModel<HomeViewModel>(
                factory = ViewModelFactory(
                    Injection.provideUserRepository(
                        LocalContext.current
                    )
                )
            )
            HomeScreen(navController = rememberNavController(), viewModel = viewModel)
        }
    }
}