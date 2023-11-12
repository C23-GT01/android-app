package academy.bangkit.trackmate.view.auth.forgetpassword

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import academy.bangkit.trackmate.navigation.Screen

@Composable
fun ForgetPasswordScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Text(
                text = "Forget Password Screen",
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Button(
                onClick = {
                    navController.navigate(Screen.Auth.Login.route) {
                        popUpTo(Screen.Auth.Login.route) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Password Changed, Login Now")
            }
        }
    }
}

@Preview
@Composable
fun ForgetPasswordScreenPrev() {
    ForgetPasswordScreen(navController = rememberNavController())
}
