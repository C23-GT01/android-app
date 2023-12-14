package academy.bangkit.trackmate.view.app.account.menu

import academy.bangkit.trackmate.R
import academy.bangkit.trackmate.data.remote.response.User
import academy.bangkit.trackmate.navigation.Screen
import academy.bangkit.trackmate.ui.theme.ButtonPrimary
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage

@Composable
fun PersonalInformationScreen(navController: NavController, user: User) {

    val username = user.username
    val email = user.email
    val fullName = user.fullName
    val id = user.id

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
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

        Spacer(modifier = Modifier.height(8.dp))

        PersonalInfoRow(stringResource(id = R.string.user_id), id, Icons.Rounded.Info)
        PersonalInfoRow(stringResource(id = R.string.username), username, Icons.Rounded.Person)
        PersonalInfoRow(
            stringResource(id = R.string.full_name),
            fullName,
            Icons.Rounded.AccountCircle
        )
        PersonalInfoRow(stringResource(id = R.string.email), email, Icons.Rounded.Email)

        Spacer(modifier = Modifier.height(20.dp))

        // Edit Button
        Button(
            onClick = {
                navController.navigate(Screen.App.Account.EditProfile.route)
                {
                    popUpTo(Screen.App.Account.PersonalInformation.route)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(15),
            colors = ButtonPrimary
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Edit", color = Color.White)
        }
    }
}

@Composable
fun PersonalInfoRow(label: String, value: String, icon: ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .padding(end = 8.dp)
        )

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.weight(2f)
        ) {
            Text(
                label,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(bottom = 1.dp)
            )
            Text(
                value,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 1.dp)
            )
        }
    }

    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
}

@Preview(showBackground = true, heightDp = 600)
@Composable
fun PersonalInformationPreview() {
    TrackMateTheme {
        PersonalInformationScreen(
            rememberNavController(),
            User(null, "1234", "Abdullah Fikri", "abdullah.fikri@students.utdi.ac.id", "fikrihandy")
        )
    }
}


