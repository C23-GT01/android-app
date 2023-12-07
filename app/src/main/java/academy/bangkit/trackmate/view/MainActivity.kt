package academy.bangkit.trackmate.view

import academy.bangkit.trackmate.navigation.TrackMateApp
import academy.bangkit.trackmate.navigation.TrackMateAppViewModel
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrackMateTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
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
}