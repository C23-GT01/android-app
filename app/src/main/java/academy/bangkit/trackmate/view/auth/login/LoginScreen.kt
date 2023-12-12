package academy.bangkit.trackmate.view.auth.login

import academy.bangkit.trackmate.R
import academy.bangkit.trackmate.data.pref.UserModel
import academy.bangkit.trackmate.data.remote.response.LoginResponse
import academy.bangkit.trackmate.navigation.Screen
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import academy.bangkit.trackmate.view.Factory
import academy.bangkit.trackmate.view.LockScreenOrientation
import academy.bangkit.trackmate.view.auth.components.ButtonComponent
import academy.bangkit.trackmate.view.auth.components.ClickableLoginTextComponent
import academy.bangkit.trackmate.view.auth.components.ErrorMessage
import academy.bangkit.trackmate.view.auth.components.HeadingTextComponent
import academy.bangkit.trackmate.view.auth.components.NormalTextComponent
import academy.bangkit.trackmate.view.auth.components.PasswordTextFieldComponent
import academy.bangkit.trackmate.view.auth.components.TextFieldComponent
import academy.bangkit.trackmate.view.auth.components.UnderlinedTextComponent
import academy.bangkit.trackmate.view.component.LinearLoading
import academy.bangkit.trackmate.view.sendWhatsAppMessage
import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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

    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    val nullableResponse by viewModel.loginResponse.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(initial = false)

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    val response: LoginResponse
    if (nullableResponse != null) {
        response = nullableResponse as LoginResponse

        if (response.error) {
            isError = true
            errorMessage = response.message
        } else {
            viewModel.saveSession(
                UserModel(
                    accessToken = response.token?.access ?: "",
                    refreshToken = response.token?.refresh ?: "",
                    isLogin = true
                )
            )
            navController.navigate(Screen.App.route)
        }
    }

    Column {
        LinearLoading(isLoading)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp)
        ) {
            NormalTextComponent(value = stringResource(id = R.string.app_name))
            HeadingTextComponent(value = stringResource(id = R.string.login))
            Spacer(modifier = Modifier.height(38.dp))
            TextFieldComponent(
                labelValue = stringResource(id = R.string.username),
                painterResource = painterResource(id = R.drawable.baseline_person_outline_24),
                onTextValueChanged = {
                    username = it
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource = painterResource(id = R.drawable.baseline_lock_24),
                onTextValueChanged = {
                    password = it
                }
            )
            Spacer(modifier = Modifier.height(2.dp))
            val context = LocalContext.current
            UnderlinedTextComponent(value = stringResource(id = R.string.forgot_your_password)) {
                sendWhatsAppMessage(
                    context,
                    "6289692703057",
                    "Reset password TrackMate\n" + "Nama = \n" + "Email = \n" + "Catatan lain = "
                )
            }

            Spacer(modifier = Modifier.height(15.dp))
            ErrorMessage(isError, errorMessage)

            Spacer(modifier = Modifier.height(15.dp))
            ButtonComponent(
                value = stringResource(id = R.string.login),
                action = {
                    if (username.isEmpty() or password.isEmpty()) {
                        isError = true
                        errorMessage = "Username & Password wajib diisi"
                    } else {
                        viewModel.login(username, password)
                    }
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            ButtonComponent(
                value = stringResource(id = R.string.guest),
                action = {
                    viewModel.saveSession(
                        UserModel(
                            accessToken = "",
                            refreshToken = "Guest",
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

            Spacer(modifier = Modifier.height(20.dp))
            ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                navController.navigate(Screen.Auth.Register.route)
            })
        }
//    }
    }
}

@Preview(heightDp = 530)
@Composable
fun LoginScreenPrev() {
    TrackMateTheme {
        Surface {
            LoginScreen(
                navController = rememberNavController(),
                viewModel(factory = Factory())
            )
        }
    }
}