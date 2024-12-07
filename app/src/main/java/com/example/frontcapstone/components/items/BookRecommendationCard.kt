package com.example.frontcapstone.components.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.frontcapstone.R
import com.example.frontcapstone.api.data.BookData
import com.example.frontcapstone.ui.theme.PrimaryPurpleColor

@Composable
fun BookRecommendationCard(
    bookList: List<BookData>,
    onRecommendBookClicked: (BookData) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF535466) // 어두운 회색 배경
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // 상단 영역 (라벤더 배경색)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PrimaryPurpleColor), // 라벤더 색상
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "recommendation",
                        fontSize = 24.sp,
                        color = Color.Black,
                    )

                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "User Avatar",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            // 하단 영역 (회색 배경색)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                bookList.forEach { book ->
                    BookItem(
                        book = book,
                        onRecommendBookClicked = onRecommendBookClicked,
                    )
                }
            }
        }
    }
}

@Composable
fun BookItem(
    book: BookData,
    onRecommendBookClicked: (BookData) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(vertical = 16.dp)
            .clickable { onRecommendBookClicked(book) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = book.image,
            contentDescription = "Book Cover",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "${book.name} | ${book.year} | ${book.author}",
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
