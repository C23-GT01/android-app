package academy.bangkit.trackmate.view.component

import academy.bangkit.trackmate.ui.theme.Brown
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CircularLoading() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            color = Brown,
            modifier = Modifier
                .size(75.dp)
                .padding(16.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun LinearLoading(isLoading: Boolean) {
    if (isLoading) {
        LinearProgressIndicator(
            color = Brown,
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
        )
    } else {
        Spacer(modifier = Modifier.height(4.dp))
    }
}