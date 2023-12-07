package academy.bangkit.trackmate.view.app.home.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import academy.bangkit.trackmate.R

data class Category(
    val id: Int,
    @DrawableRes val imageCategory: Int,
    @StringRes val textCategory: Int
)

val category = listOf(
    R.drawable.icon_category_all to R.string.category_all,
    R.drawable.icon_category_agriculture to R.string.category_agriculture,
    R.drawable.icon_category_food to R.string.category_food,
    R.drawable.icon_category_drink to R.string.category_drink,
).mapIndexed { index, pair ->
    Category(index, pair.first, pair.second)
}