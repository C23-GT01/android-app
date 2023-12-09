package academy.bangkit.trackmate.view.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun ShowMap(
    location: LatLng,
    locationName: String
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(location, 10f)
        }

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = location),
                title = locationName,
//                snippet = "Marker in Singapore"
            )
        }
    }
}