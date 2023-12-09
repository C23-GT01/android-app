package academy.bangkit.trackmate.view.app.home.component

import academy.bangkit.trackmate.R
import academy.bangkit.trackmate.view.app.detail.component.Title
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun EmptyProducts() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Image(
                painter = painterResource(id = R.drawable.no_data),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Title(title = stringResource(id = R.string.products_is_empty))
        }
    }
}