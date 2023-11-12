package academy.bangkit.trackmate.view.app.detail.component.productionprocess

import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import academy.bangkit.trackmate.view.app.detail.component.Divider
import academy.bangkit.trackmate.view.app.detail.component.Title
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProductionProcess() {
    Title(title = "Proses Produksi")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TimelineNode(position = TimelineNodePosition.FIRST) { modifier ->
            CardItem(
                "Pengumpulan Bahan",
                "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEimU-cI7ieeLXk0I90RhV_DIZ1TUb5BhFW_0Z2F2bbdtLtw_tLAIFX2l8-J1HhQZTAm6MbprbG0rVKR7vv8puEL-tcIl3_dKx438-JSRl_MVvSsRbY-K9YFujBwIAz3W6pMWLQdgkD1ehLbfdwe5wJfoV0lRspJu1Ukwrz-kCZ58x2vWkFV1GzBuHolnUM/s1600/Rectangle%2011%281%29.png",
                modifier
            )
        }
        TimelineNode(position = TimelineNodePosition.MIDDLE) { modifier ->
            CardItem(
                "Pengerjaan Produk",
                "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEjGZqDe4kiLD3LMCIXAR-Kkjmzv-HH1Ph0QqO_odXO1aXfHOyUOABEL-51NRPVtts3UkdFAYz0ixjsHCWEM8Ilr_G1NrJS6PavIcc6vyM5rZUDusRrklGMHkmyhu0UorKugFMAjEUY0fASBay66mcGnaJbUM71RW68BM99cNG237UG-7OwZ4fFi2BN_EY0/s1600/Rectangle%2011%282%29.png",
                modifier
            )
        }
        TimelineNode(position = TimelineNodePosition.MIDDLE) { modifier ->
            CardItem(
                "Pengemasan Produk",
                "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgkNbn_Dy6CdoSup8iSvl4dX8nDV3woojjP-ORFNp5BKt0AZZYuEdyOG9QGZy74y9fwuTLRGwm86e_QKxu2DFvTKJkqUfPzCi2_0FWfu_aH1lSSixiIm7LTaydjqbpuIyE5zVBgfHzI510kkQ2NYCjfQq4_cRCil0nQXSrZ_fdB4IraoTo9yFDYGXT-1ds/s1600/Rectangle%2011%283%29.png",
                modifier
            )
        }
        TimelineNode(
            lineParameters = null, //item terakhir wajib null
            position = TimelineNodePosition.LAST
        ) { modifier ->
            CardItem(
                "Produk Dijual",
                "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhWN9zCUTFYyjpU2xo4X2u_NDuSqBJp11f_qmD27g-d5ztCHgWDChaIKnWbij73FKzLtbKnPpdMUSkGsmWJgxrcL2YrlPV5C5w8X6RSrhTjHGeLxxvEJ0HlxkXfHSgJIXvjVUPTZhRrllU2sUCj6n982KYVBJPs9xxArdfMsOwlofNtmI3W4ifvVUjDIbo/s1600/Rectangle%2011%284%29.png",
                modifier
            )
        }
    }
    Divider()
}

@Preview(showBackground = true, heightDp = 1150)
@Composable
fun ProductionProcessPreview() {
    TrackMateTheme {
        Surface {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn {
                    item {
                        ProductionProcess()
                    }
                }
            }
        }
    }
}