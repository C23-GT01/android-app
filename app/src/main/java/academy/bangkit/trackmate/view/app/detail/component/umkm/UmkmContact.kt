package academy.bangkit.trackmate.view.app.detail.component.umkm

import academy.bangkit.trackmate.R
import academy.bangkit.trackmate.data.remote.response.Phone
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import academy.bangkit.trackmate.view.openDialerWithPhoneNumber
import academy.bangkit.trackmate.view.sendWhatsAppMessage
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun UmkmContact(email: String, phone: Phone) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.contact_umkm),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Rounded.Call,
                contentDescription = "Phone Icon",
                modifier = Modifier.size(20.dp),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = if (!phone.isWhatsApp) {
                    "Telp: ${phone.phoneNumber}"
                } else {
                    "Telp / WhatsApp: ${phone.phoneNumber}"
                },
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .clickable {
                        if (phone.isWhatsApp) {
                            sendWhatsAppMessage(context, phone.phoneNumber, "")
                        } else {
                            openDialerWithPhoneNumber(context, phone.phoneNumber)
                        }
                    }
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Rounded.Email,
                contentDescription = "Email Icon",
                modifier = Modifier.size(20.dp),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Email: $email",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview
@Composable
fun UmkmContactPreview() {
    TrackMateTheme {
        Surface {
//            UmkmContact()
        }
    }
}