package academy.bangkit.trackmate.view

import academy.bangkit.trackmate.data.remote.response.Location
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import academy.bangkit.trackmate.view.app.ShowMap
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.LatLng

class MapsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val defaultValue = 0.0

        val lat: Double = intent.getDoubleExtra(TrackMateLocation.LAT, defaultValue)
        val lon: Double = intent.getDoubleExtra(TrackMateLocation.LON, defaultValue)
        val locationName: String? = intent.getStringExtra(TrackMateLocation.LOC_NAME)

        setContent {
            TrackMateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (locationName != null) {
                        ShowMap(LatLng(lat, lon), locationName)
                    }
                }
            }
        }
    }
}

object TrackMateLocation {
    const val LOC_NAME = "location_name"
    const val LAT = "lat"
    const val LON = "lon"
    fun sendToMapsActivity(context: Context, location: Location) {
        val intentToMapsActivity = Intent(context, MapsActivity::class.java)
        intentToMapsActivity
            .putExtra(LAT, location.lat)
            .putExtra(LON, location.lng)
            .putExtra(LOC_NAME, location.name)
        context.startActivity(intentToMapsActivity)
    }
}
