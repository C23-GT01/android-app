package academy.bangkit.trackmate.view.app.account.menu

import academy.bangkit.trackmate.R
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PersonalInformationScreen() {
    var username by remember { mutableStateOf("Ngurah Agung") }
    var email by remember { mutableStateOf("ngurahagung543@gmail.com") }
    var fullname by remember { mutableStateOf("I Gusti Ngurah Agung Kade Dwi Arsana")}
    var id by remember { mutableStateOf("186718013")}

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .clip(MaterialTheme.shapes.medium)
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        PersonalInfoRow("ID", id, Icons.Default.Info)
        PersonalInfoRow("Username", username, Icons.Default.Person)
        PersonalInfoRow("Fullname", fullname, Icons.Default.AccountCircle)
        PersonalInfoRow("E-Mail", email, Icons.Default.Email)

        Spacer(modifier = Modifier.height(40.dp))

        // Edit Button
        Button(
            onClick = { /* Handle edit button click */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(15)
        ) {
            Icon(imageVector = Icons.Default.Person, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Edit")
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

@Preview(showBackground = true)
@Composable
fun PersonalInformationPreview() {
    TrackMateTheme {
        PersonalInformationScreen()
    }
}


