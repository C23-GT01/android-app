package academy.bangkit.trackmate.view

import academy.bangkit.trackmate.navigation.TrackMateApp
import academy.bangkit.trackmate.navigation.TrackMateAppViewModel
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.action == Intent.ACTION_VIEW) {
            val uri = intent.data
            handleDeepLink(uri)
        }

        setContent {
            TrackMateTheme(darkTheme = false) {

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "detail/{id}") {
                    composable("detail/{id}"){
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
                            Button(onClick = {
                                navController.navigate("detail/{id}")
                            }){
                                Text(text = "To ProductDetailScreen")
                            }
                        }
                    }
                    composable(
                        route = "detail/{id}",
                        deepLinks = listOf(
                            navDeepLink {
                                uriPattern = "https://trackmate.com/{id}"
                                action = Intent.ACTION_VIEW
                            }
                        ),
                        arguments = listOf(
                            navArgument("id"){
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ){ entry ->
                        val id = entry.arguments?.getInt("id")
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
                            Text(text = "The id is $id")
                        }
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = viewModel<TrackMateAppViewModel>(factory = Factory())
                    TrackMateApp(viewModel)
                }
            }
        }
    }

    private fun handleDeepLink(uri: Uri?) {
        uri?.let {
            if (it.scheme == "https" && it.host == "web-app-five-beta.vercel.app") {
                showToast("Selamat Datang di TrackMate")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}