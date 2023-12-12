package academy.bangkit.trackmate.view.auth.register

import academy.bangkit.trackmate.R
import academy.bangkit.trackmate.data.remote.response.RegisterResponse
import academy.bangkit.trackmate.view.Factory
import academy.bangkit.trackmate.view.LockScreenOrientation
import academy.bangkit.trackmate.view.auth.components.ButtonComponent
import academy.bangkit.trackmate.view.auth.components.ClickableLoginTextComponent
import academy.bangkit.trackmate.view.auth.components.ErrorMessage
import academy.bangkit.trackmate.view.auth.components.HeadingTextComponent
import academy.bangkit.trackmate.view.auth.components.NormalTextComponent
import academy.bangkit.trackmate.view.auth.components.PasswordTextFieldComponent
import academy.bangkit.trackmate.view.auth.components.TextFieldComponent
import academy.bangkit.trackmate.view.component.LinearLoading
import android.content.pm.ActivityInfo
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel
) {

    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    val nullableResponse by viewModel.registerResponse.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(initial = false)

    var username by remember { mutableStateOf("") }
    var fullname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var successRegister by remember { mutableStateOf(false) }

    if (nullableResponse != null) {
        val response = nullableResponse as RegisterResponse
        if (response.error) {
            isError = true
            errorMessage = response.message
        } else {
            successRegister = true
//            navController.navigateUp()
        }
    }

    if (successRegister) {
        SuccessDialog {
            navController.navigateUp()
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
            HeadingTextComponent(value = stringResource(id = R.string.create_account))
            Spacer(modifier = Modifier.height(38.dp))
            TextFieldComponent(
                labelValue = stringResource(id = R.string.full_name),
                painterResource = painterResource(R.drawable.baseline_person_outline_24),
                onTextValueChanged = { fullname = it }
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextFieldComponent(
                labelValue = stringResource(id = R.string.username),
                painterResource = painterResource(R.drawable.baseline_person_outline_24),
                onTextValueChanged = { username = it }
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource = painterResource(R.drawable.baseline_email_24),
                onTextValueChanged = { email = it }
            )
            Spacer(modifier = Modifier.height(10.dp))
            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource = painterResource(id = R.drawable.baseline_lock_24),
                onTextValueChanged = { password = it }
            )
            Spacer(modifier = Modifier.height(10.dp))
            ErrorMessage(isError, errorMessage)
            Spacer(modifier = Modifier.height(10.dp))
            ButtonComponent(
                value = stringResource(id = R.string.register),
                action = {
                    Log.d("Register", "Your Data = $fullname, $username, $email, $password")
                    viewModel.register(username, email, password, fullname)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))
            ClickableLoginTextComponent(
                onTextSelected =
                {
                    navController.navigateUp()
                })
        }
    }
}

@Composable
private fun SuccessDialog(action: () -> Unit) {
    Dialog(onDismissRequest = { action() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Box(Modifier.fillMaxSize()) {
                Column(
                    Modifier.align(Alignment.Center)
                ) {
                    Text(
                        text = "Berhasil Mendaftar",
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .wrapContentSize(Alignment.Center),
                        textAlign = TextAlign.Center,
                    )
                    Button(onClick = { action() }) {
                        Text(text = "Login Sekarang")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPrev() {
    val viewModel = viewModel<RegisterViewModel>(factory = Factory())
    RegisterScreen(rememberNavController(), viewModel)
}

@Preview(showBackground = true)
@Composable
fun SuccessDialogPrev() {
    SuccessDialog {
        Log.d("Dialog", "CLicked")
    }
}