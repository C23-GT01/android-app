package academy.bangkit.trackmate.view.app.account

import academy.bangkit.trackmate.R
import academy.bangkit.trackmate.di.Injection
import academy.bangkit.trackmate.navigation.Screen
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import academy.bangkit.trackmate.view.ViewModelFactory
import academy.bangkit.trackmate.view.app.detail.component.Title
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Logout
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun GuestAccountScreen(
    navController: NavController,
    viewModel: UserAccountViewModel
) {

    Box(
        modifier = Modifier.fillMaxSize()
    )
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Image(
                painter = painterResource(id = R.drawable.guest_account),
                contentDescription = "Guest Account",
                modifier = Modifier
                    .size(120.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Title(title = "You're Logged in As Guest")
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Create account and login to get more feature in this App",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                viewModel.logout()
                navController.navigate(Screen.Auth.route)
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.Logout,
                    contentDescription = "Logout",
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(text = "Logout")
            }
        }
    }
}

@Preview
@Composable
fun GuestAccountScreenPreview() {
    TrackMateTheme {
        Surface {
            val viewModel = viewModel<UserAccountViewModel>(
                factory = ViewModelFactory(
                    Injection.provideUserRepository(
                        LocalContext.current
                    )
                )
            )
            GuestAccountScreen(rememberNavController(), viewModel)
        }
    }
}