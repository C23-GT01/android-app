package academy.bangkit.trackmate.view.auth.login

import academy.bangkit.trackmate.R
import academy.bangkit.trackmate.data.pref.UserModel
import academy.bangkit.trackmate.di.Injection
import academy.bangkit.trackmate.navigation.Screen
import academy.bangkit.trackmate.view.ViewModelFactory
import academy.bangkit.trackmate.view.auth.components.ButtonComponent
import academy.bangkit.trackmate.view.auth.components.ClickableLoginTextComponent
import academy.bangkit.trackmate.view.auth.components.HeadingTextComponent
import academy.bangkit.trackmate.view.auth.components.NormalTextComponent
import academy.bangkit.trackmate.view.auth.components.PasswordTextFieldComponent
import academy.bangkit.trackmate.view.auth.components.TextFieldComponent
import academy.bangkit.trackmate.view.auth.components.UnderlinedTextComponent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            NormalTextComponent(value = stringResource(id = R.string.trackmate))
            HeadingTextComponent(value = stringResource(id = R.string.login))
            Spacer(modifier = Modifier.height(38.dp))
            TextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource = painterResource(id = R.drawable.baseline_email_24)
            )
            Spacer(modifier = Modifier.height(10.dp))
            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource = painterResource(id = R.drawable.baseline_lock_24)
            )
            Spacer(modifier = Modifier.height(2.dp))
            UnderlinedTextComponent(value = stringResource(id = R.string.forgot_your_password))

            Spacer(modifier = Modifier.height(50.dp))
            ButtonComponent(
                value = stringResource(id = R.string.login),
                action = {
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
                }
            )

            Spacer(modifier = Modifier.height(300.dp))
            ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                navController.navigate(Screen.Auth.Register.route)
            })
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