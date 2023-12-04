package academy.bangkit.trackmate.view.app.account

import academy.bangkit.trackmate.R
import academy.bangkit.trackmate.navigation.Screen
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun UserAccountScreen(navController: NavController) {

    val scrollState = rememberScrollState()
    var showDialog by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .background(color = Color.White)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                    .clickable { /* Handle image selection */ }
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

        Text(
            "Ngurah Agung",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp),
            color = Color.Black
        )
        Text(
            "ngurahagung@gmail.com",
            fontSize = 20.sp,
            color = Color.Black
        )
        Button(
            onClick = {
                navController.navigate(Screen.App.Account.MyReview.route)
                {
                    popUpTo(Screen.App.Account.route)
                }
            },
            Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 10.dp, bottom = 10.dp)
                .height(55.dp), shape = RoundedCornerShape(15)
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_1),
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
                    text = "My Reviews",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Button(
            onClick = {
                navController.navigate(Screen.App.Account.EditProfile.route)
                {
                    popUpTo(Screen.App.Account.route)
                }
            },
            Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 10.dp, bottom = 10.dp)
                .height(55.dp),
            shape = RoundedCornerShape(15)
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_2),
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
                    text = "Edit Profile",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Button(
            onClick = {
                navController.navigate(Screen.App.Account.PersonalInformation.route)
                {
                    popUpTo(Screen.App.Account.route)
                }
            },
            Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 10.dp, bottom = 10.dp)
                .height(55.dp), shape = RoundedCornerShape(15)
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_3),
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
                    text = "Personal Information",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Button(
            onClick = {
                showDialog = true
            },
            Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 100.dp, bottom = 10.dp)
                .height(55.dp),
            shape = RoundedCornerShape(15)
        ) {
            Text(
                text = "Log Out",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                },
                title = {
                    Text(text = "Log Out")
                },
                text = {
                    Text(text = "Yakin mau keluar?")
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showDialog = false
                            // Lakukan fungsi keluar di sini
                            navController.navigate(Screen.Auth.route)
                            {
                                popUpTo(Screen.App.Home.route) {
                                    inclusive = true
                                }
                            }
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
//    BackHandler(true) {
//        navController.navigate(Screen.App.Home.route)
//    }
}

@Preview(showBackground = true)
@Composable
fun AccountPreview() {
    TrackMateTheme {
        Surface {
            UserAccountScreen(navController = rememberNavController())
        }
    }
}