package academy.bangkit.trackmate.view.app.account.menu

import academy.bangkit.trackmate.R
import academy.bangkit.trackmate.data.remote.request.EditProfileRequest
import academy.bangkit.trackmate.data.remote.response.EditProfileResponse
import academy.bangkit.trackmate.data.remote.response.ImageUploadResponse
import academy.bangkit.trackmate.data.remote.response.User
import academy.bangkit.trackmate.navigation.Screen
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import academy.bangkit.trackmate.view.Factory
import academy.bangkit.trackmate.view.app.account.UserAccountViewModel
import academy.bangkit.trackmate.view.component.LinearLoading
import academy.bangkit.trackmate.view.showToast
import academy.bangkit.trackmate.view.toMultipartBodyPart
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.runBlocking

@Composable
fun EditProfileScreen(
    user: User,
    navController: NavController,
    viewModel: UserAccountViewModel
) {
    val context = LocalContext.current

    val nullableResponse by viewModel.userEdit.observeAsState()
    val newPPResponse by viewModel.newProfilePicture.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(initial = false)

    var selectedImage by remember { mutableStateOf<Uri?>(null) } //dari storage

    val response: EditProfileResponse

    var fullname by remember { mutableStateOf(user.fullname) }
    var username by remember { mutableStateOf(user.username) }
    var email by remember { mutableStateOf(user.email) }
    var newProfilePicture by remember { mutableStateOf<String?>(null) } //dari backend

    if (newPPResponse != null) {
        val newProfileImage = newPPResponse as ImageUploadResponse
        if (!newProfileImage.error) {
            newProfilePicture = newProfileImage.data?.fileLocation
            Log.d("New PP", newProfilePicture ?: "masih kosong")
        } else {
            selectedImage = null
            showToast(context, "Gagal upload image")
        }
    }
    var isDataChanged by remember { mutableStateOf(false) }
    isDataChanged =
        (fullname != user.fullname || username != user.username || email != user.email || selectedImage != null) && !isLoading

    if (nullableResponse != null) {
        response = nullableResponse as EditProfileResponse
        if (!response.error) {
            navController.navigate(Screen.App.Account.route) {
                popUpTo(Screen.App.Home.route)
            }
        } else {
            //todo Kalo gagal tampilkan pesan
            showToast(LocalContext.current, "Gagal mengedit profil")
        }
    }

//    if (user != null) {
//        fullname = user.fullname
//        username = user.username
//        email = user.email
//    }

    val openImagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        runBlocking {
            selectedImage = uri
            val multipartBody = selectedImage?.toMultipartBodyPart(context)
            selectedImage?.let {
                if (multipartBody != null) {
                    viewModel.uploadImage(multipartBody)
                }
            }
        }
    }

    val scrollState = rememberScrollState()

    Column(
        Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .background(color = Color.White)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoading) {
            LinearLoading(isLoading = true)
        }
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
                AsyncImage(
                    model =
                    if (selectedImage != null) {
                        ImageRequest.Builder(LocalContext.current)
                            .data(selectedImage)
                            .build()
                    } else {
                        user.image ?: R.drawable.profile
                    },
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .clickable { /* Handle image selection */ },
                    contentScale = ContentScale.Crop
                )

                // Icon kamera
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.BottomEnd)
                        .clickable {
                            Log.d("CLick", "Icon Camera")
                            openImagePicker.launch("image/*")
                        }
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
            label = { Text("Nama Lengkap") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = null
                )
            },
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
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(
                colors = ButtonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White,
                    disabledContainerColor = Color.DarkGray,
                    disabledContentColor = Color.White
                ),
                onClick = {
                    navController.navigate(Screen.App.Account.route) {
                        popUpTo(Screen.App.Home.route)
                    }
                },
                modifier = Modifier
                    .height(56.dp)
                    .padding(horizontal = 16.dp), shape = RoundedCornerShape(15)
            ) {
                Icon(imageVector = Icons.Rounded.Cancel, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Batal")
            }
            Button(
                enabled = isDataChanged,
                onClick = {
                    if (selectedImage != null) {
                        viewModel.editProfile(
                            EditProfileRequest(
                                username,
                                email,
                                newProfilePicture,
                                fullname
                            )
                        )
                    } else {
                        viewModel.editProfile(
                            EditProfileRequest(username, email, user.image, fullname)
                        )
                    }
                },
                modifier = Modifier
                    .height(56.dp)
                    .padding(horizontal = 16.dp), shape = RoundedCornerShape(15)
            ) {
                Icon(imageVector = Icons.Rounded.Save, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Simpan")
            }
        }
    }
}

@Preview(showBackground = true, heightDp = 600)
@Composable
fun EditProfilePreview() {
    viewModel<UserAccountViewModel>(factory = Factory())
    TrackMateTheme {
        Surface {
            val viewModel = viewModel<UserAccountViewModel>(factory = Factory())
//            EditProfileScreen(navController = rememberNavController(), viewModel = viewModel)
        }
    }
}
