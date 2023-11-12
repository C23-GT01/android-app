package academy.bangkit.trackmate.view.auth.login

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import academy.bangkit.trackmate.view.ViewModelFactory
import academy.bangkit.trackmate.navigation.Screen
import androidx.lifecycle.viewmodel.compose.viewModel
import academy.bangkit.trackmate.data.pref.UserModel
import academy.bangkit.trackmate.di.Injection

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel
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
                text = "Login Screen",
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Button(
                onClick = {
                    viewModel.saveSession(
                        UserModel(
                            email = "abdullahfikrihandi@gmail.com",
                            token = "tokenSampleYeah",
                            isLogin = true
                        )
                    )

                    navController.navigate(Screen.App.route) {
                        popUpTo(Screen.Auth.route) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Login Now")
            }
            Button(
                onClick = {
                    navController.navigate(Screen.Auth.Register.route)
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Register")
            }
            Button(
                onClick = {
                    navController.navigate(Screen.Auth.ForgetPassword.route)
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Forget Password")
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPrev() {
    LoginScreen(
        navController = rememberNavController(),
        viewModel(
            factory = ViewModelFactory(
                Injection.provideUserRepository(
                    LocalContext.current
                )
            )
        )
    )
}