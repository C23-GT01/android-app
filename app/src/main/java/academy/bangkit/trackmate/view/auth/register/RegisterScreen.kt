package academy.bangkit.trackmate.view.auth.register

import academy.bangkit.trackmate.R
import academy.bangkit.trackmate.view.LockScreenOrientation
import academy.bangkit.trackmate.view.auth.components.ButtonComponent
import academy.bangkit.trackmate.view.auth.components.ClickableLoginTextComponent
import academy.bangkit.trackmate.view.auth.components.HeadingTextComponent
import academy.bangkit.trackmate.view.auth.components.NormalTextComponent
import academy.bangkit.trackmate.view.auth.components.PasswordTextFieldComponent
import academy.bangkit.trackmate.view.auth.components.TextFieldComponent
import android.content.pm.ActivityInfo
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {
//    Surface(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)
//            .padding(28.dp)
//    ) {
    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
    ) {
        NormalTextComponent(value = stringResource(id = R.string.trackmate))
        HeadingTextComponent(value = stringResource(id = R.string.create_account))
        Spacer(modifier = Modifier.height(38.dp))
        TextFieldComponent(
            labelValue = stringResource(id = R.string.first_name),
            painterResource = painterResource(R.drawable.baseline_person_outline_24)
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextFieldComponent(
            labelValue = stringResource(id = R.string.last_name),
            painterResource = painterResource(R.drawable.baseline_person_outline_24)
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextFieldComponent(
            labelValue = stringResource(id = R.string.email),
            painterResource = painterResource(R.drawable.baseline_email_24)
        )
        Spacer(modifier = Modifier.height(10.dp))
        PasswordTextFieldComponent(
            labelValue = stringResource(id = R.string.password),
            painterResource = painterResource(id = R.drawable.baseline_lock_24)
        )
        Spacer(modifier = Modifier.height(40.dp))
        ButtonComponent(
            value = stringResource(id = R.string.register),
            action = {
                Log.d("Register", "Clicked")
            }
        )

        Spacer(modifier = Modifier.height(20.dp))
        ClickableLoginTextComponent(
            onTextSelected =
            {
                navController.navigateUp()
            })
    }
//    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPrev() {
    RegisterScreen(navController = rememberNavController())
}