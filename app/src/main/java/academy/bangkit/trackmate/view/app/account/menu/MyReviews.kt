package academy.bangkit.trackmate.view.app.account.menu

import academy.bangkit.trackmate.R
import academy.bangkit.trackmate.ui.theme.TrackMateTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ReviewItem(val userName: String, val reviewText: String, val rating: Double)

@Composable
fun MyReviewsScreen() {
    val reviewsList = remember {
        listOf(
            ReviewItem("Ngurah Agung", "more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", 5.0),
            ReviewItem("Fikri", "Good product! I love it.", 4.0),
            ReviewItem("Pak Noel", "Good product! I love it.", 3.0),
        )
    }

    LazyColumn {
        items(reviewsList) { review ->
            ReviewItemCard(review = review)
        }
    }
}

@Composable
fun ReviewItemCard(review: ReviewItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // User avatar and name
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = review.userName, fontWeight = FontWeight.ExtraBold)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Review text
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, MaterialTheme.shapes.medium)
                    .padding(8.dp)
            ) {
                Text(text = review.reviewText)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Rating stars
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Text menampilkan jumlah bintang
                Text(
                    text = review.rating.toString(),
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(4.dp))

                repeat(5) { index ->
                    val starColor = if (index < review.rating.toInt()) Color.Yellow else Color.Gray

                    Icon(
                        painter = painterResource(id = R.drawable.ic_star_outline),
                        contentDescription = null,
                        tint = starColor,
                        modifier = Modifier
                            .size(20.dp)
                            .fillMaxHeight()
                            .padding(1.dp)
                    )
                }
            }

            // Empty button placeholder
            Button(
                onClick = { /* No action */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), shape = RoundedCornerShape(15)
            ) {
                Text(
                    text = "Lihat Produk",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyReviewsPreview() {
    TrackMateTheme {
        MyReviewsScreen()
    }
}
