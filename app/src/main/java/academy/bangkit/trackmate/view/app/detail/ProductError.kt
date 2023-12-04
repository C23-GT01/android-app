package academy.bangkit.trackmate.view.app.detail

import academy.bangkit.trackmate.data.remote.response.Location
import academy.bangkit.trackmate.data.remote.response.ProductItem
import academy.bangkit.trackmate.data.remote.response.UMKM

object ProductError {
    val error = ProductItem(
        "",
        mutableListOf(),
        mutableListOf(),

        0,
        mutableListOf(),
        "",
        "",
        mutableListOf(),
        "",
        UMKM("", "", 0, "", Location(0.0, 0.0, ""))
    )
}