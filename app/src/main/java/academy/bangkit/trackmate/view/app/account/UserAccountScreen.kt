package academy.bangkit.trackmate.view.app.account

import academy.bangkit.trackmate.R
import academy.bangkit.trackmate.data.remote.response.User
import academy.bangkit.trackmate.data.remote.response.UserAccountResponse
import academy.bangkit.trackmate.navigation.Screen
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import academy.bangkit.trackmate.view.Factory
import academy.bangkit.trackmate.view.LockScreenOrientation
import academy.bangkit.trackmate.view.component.CircularLoading
import academy.bangkit.trackmate.view.component.ErrorScreen
import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage

@Composable
fun UserAccountScreen(
    navController: NavController,
    viewModel: UserAccountViewModel
) {

    val response by viewModel.user.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(initial = false)

    LaunchedEffect(Unit) {
        viewModel.getProfile()
    }

    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize()) { CircularLoading() }
    } else {

        val userResponse: UserAccountResponse
        if (response != null) {
            userResponse = response as UserAccountResponse
            if (!userResponse.error && userResponse.data != null) {
                ShowProfile(navController, viewModel, userResponse.data.user)
            } else {
                ErrorScreen(message = userResponse.message) {
                    viewModel.getProfile()
                }
            }
        }
    }
}

@Composable
private fun ShowProfile(
    navController: NavController,
    viewModel: UserAccountViewModel,
    user: User
) {
    var showDialog1 by remember { mutableStateOf(false) }

    Box {
        Column {
            ConstraintLayout {
                val (topImg, profile) = createRefs()
                Image(painterResource(id = R.drawable.top_background), null,
                    Modifier
                        .fillMaxWidth()
                        .constrainAs(topImg) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        })
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .padding(16.dp)
                        .constrainAs(profile) {
                            top.linkTo(topImg.bottom)
                            bottom.linkTo(topImg.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                ) {
                    // Gambar
                    AsyncImage(
                        model = user.image ?: R.drawable.profile,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(
                        user.fullname,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        user.email,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                    ButtonInUserAccount(
                        stringResource(id = R.string.profile_edit),
                        R.drawable.ic_2
                    ) {
                        UserAccount.user = user
                        navController.navigate(Screen.App.Account.EditProfile.route)
                        {
                            popUpTo(Screen.App.Account.route)
                        }
                    }
                    ButtonInUserAccount(
                        stringResource(id = R.string.my_data),
                        R.drawable.ic_3
                    ) {
                        UserAccount.user = user
                        navController.navigate(Screen.App.Account.PersonalInformation.route)
                        {
                            popUpTo(Screen.App.Account.route)
                        }
                    }
                    ButtonInUserAccount(
                        stringResource(id = R.string.logout),
                        R.drawable.ic_1
                    ) {
                        showDialog1 = true
                    }
                }
            }
        }
        if (showDialog1) {
            AlertDialog(
                onDismissRequest = {
                    showDialog1 = false
                },
                title = {
                    Text(text = stringResource(id = R.string.logout))
                },
                text = {
                    Text(text = stringResource(id = R.string.logout_confirmation))
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showDialog1 = false
                            viewModel.logout()
                            navController.navigate(Screen.Auth.route)
                        }
                    ) {
                        Text(stringResource(id = R.string.yes))
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            showDialog1 = false
                        }
                    ) {
                        Text(stringResource(id = R.string.no))
                    }
                }
            )
        }
    }
}

@Composable
private fun ButtonInUserAccount(
    text: String,
    icon: Int,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp, top = 10.dp, bottom = 10.dp)
            .height(38.dp),
        shape = RoundedCornerShape(15)
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "",
                modifier = Modifier
                    .padding(end = 5.dp)
                    .clickable { }
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = text,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

object UserAccount {
    var user: User? = null
}


@Preview(showBackground = true, heightDp = 600)
@Composable
fun AccountPreview() {
    TrackMateTheme {
        Surface {
            val viewModel = viewModel<UserAccountViewModel>(factory = Factory())
            UserAccountScreen(navController = rememberNavController(), viewModel = viewModel)
            ShowProfile(
                navController = rememberNavController(),
                viewModel = viewModel,
                user = User(
                    "",
                    "1234",
                    "Abdullah",
                    "abdullah.fikri@students.utdi.ac.id",
                    "fikrihandy"
                )
            )
        }
    }
}