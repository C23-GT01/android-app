package academy.bangkit.trackmate.view.app.account

import academy.bangkit.trackmate.R
import academy.bangkit.trackmate.di.Injection
import academy.bangkit.trackmate.navigation.Screen
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import academy.bangkit.trackmate.view.LockScreenOrientation
import academy.bangkit.trackmate.view.ViewModelFactory
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun UserAccountScreen(
    navController: NavController,
    viewModel: UserAccountViewModel
) {

    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    var showDialog by remember { mutableStateOf(false) }

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
                    Image(
                        painterResource(id = R.drawable.profile),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(MaterialTheme.shapes.medium)
                            .clickable { /* Handle image selection */ },
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
                        "Ngurah Agung",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        "ngurahagung@gmail.com",
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                    ButtonInUserAccount("Edit Profile", R.drawable.ic_2) {
                        navController.navigate(Screen.App.Account.EditProfile.route)
                        {
                            popUpTo(Screen.App.Account.route)
                        }
                    }
                    ButtonInUserAccount("Data Saya", R.drawable.ic_3) {
                        navController.navigate(Screen.App.Account.PersonalInformation.route)
                        {
                            popUpTo(Screen.App.Account.route)
                        }
                    }
                    ButtonInUserAccount("Logout", R.drawable.ic_1) {
                        showDialog = true
                    }
                }
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                },
                title = {
                    Text(text = "Logout")
                },
                text = {
                    Text(text = "Yakin mau keluar?")
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showDialog = false
                            viewModel.logout()
                            navController.navigate(Screen.Auth.route)
                        }
                    ) {
                        Text("Yes")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            showDialog = false
                        }
                    ) {
                        Text("No")
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

@Preview(showBackground = true, heightDp = 600)
@Composable
fun AccountPreview() {
    TrackMateTheme {
        Surface {
            val viewModel = viewModel<UserAccountViewModel>(
                factory = ViewModelFactory(
                    Injection.provideUserRepository(
                        LocalContext.current
                    )
                )
            )
            UserAccountScreen(navController = rememberNavController(), viewModel = viewModel)
        }
    }
}