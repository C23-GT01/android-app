package academy.bangkit.trackmate.view.app.account.menu

import academy.bangkit.trackmate.R
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun EditProfileScreen() {
    var fullname by remember { mutableStateOf("I Gusti Ngurah Agung ") }
    var username by remember { mutableStateOf("Ngurah Agung") }
    val selectedImage by remember { mutableStateOf<ImageBitmap?>(null) }

    val scrollState = rememberScrollState()

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
                    painter = selectedImage?.let { painterResource(id = R.drawable.profile) } ?: painterResource(id = R.drawable.profile),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(MaterialTheme.shapes.medium)
                        .clickable { /* Handle image selection */ },
                    contentScale = ContentScale.Crop
                )

                // Icon kamera
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.BottomEnd)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_photo_camera_24), // Mengambil gambar dari drawable
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(8.dp)
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = fullname,
            onValueChange = { fullname = it },
            label = { Text("Fullname") },
            leadingIcon = { Icon(imageVector = Icons.Default.AccountBox, contentDescription = null) },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            ),
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )


        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            leadingIcon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 16.dp)
            ,shape = RoundedCornerShape(15)
        ) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Save")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditProfilePreview() {
    TrackMateTheme {
        EditProfileScreen()
    }
}
