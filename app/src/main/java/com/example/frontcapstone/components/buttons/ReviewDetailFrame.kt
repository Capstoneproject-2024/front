package com.example.frontcapstone.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.frontcapstone.R
import com.example.frontcapstone.api.data.ReviewWithBook
import com.example.frontcapstone.components.items.FixedRatingBar
import com.example.frontcapstone.ui.theme.PrimaryPurpleColor
import java.time.format.DateTimeFormatter

@Composable
fun ReviewDetailFrame(
    reviewWithBook: ReviewWithBook
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            ),
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .height(145.dp),
                verticalAlignment = Alignment.Top
            ) {

                AsyncImage(
                    model = reviewWithBook.image,
                    contentDescription = "Book Cover",
                    modifier = Modifier
                        .width(98.72.dp)
                        .height(145.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom

                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.user_profile), // 책 커버 이미지 리소스
                            contentDescription = "user frame",
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(30.dp),
                            contentScale = ContentScale.Crop
                        )

                        FixedRatingBar(
                            rating = reviewWithBook.rating,
                        )
                    }

                    Text(
                        text = "${reviewWithBook.name} | ${reviewWithBook.year} | ${reviewWithBook.author}",
                        color = Color.White,
                        fontSize = 12.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(65.dp)
                            .padding(top = 8.dp, bottom = 0.dp, start = 8.dp, end = 8.dp)
                            .background(
                                color = PrimaryPurpleColor,
                                shape = RoundedCornerShape(12.dp)
                            )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
//                        modifier = Modifier.fillMaxWidth(),
                        ) {
                            // Left quotation icon
                            Column(
                                modifier = Modifier.fillMaxHeight(),
                                verticalArrangement = Arrangement.Top,
                            ) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxHeight(0.3f)
                                        .padding(2.dp),
                                    text = "“",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                )
                            }


                            Text(
                                text = reviewWithBook.quote,
                                color = Color.Black,
                                modifier = Modifier
                                    .background(
                                        PrimaryPurpleColor,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .height(64.dp)
                                    .padding(4.dp)
                                    .padding(vertical = 4.dp)
                                    .fillMaxWidth(0.92f),
                                textAlign = TextAlign.Start,
                                fontSize = 12.sp
                            )

                            // Right quotation icon
                            Column(
                                modifier = Modifier.fillMaxHeight(),
                                verticalArrangement = Arrangement.Bottom,
                            ) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxHeight(0.3f)
                                        .padding(2.dp),
                                    text = "”",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                )
                            }

                        }
                    }

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = reviewWithBook.reviewDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")),
                        color = Color.White,
                        textAlign = TextAlign.End,
                        fontSize = 8.sp
                    )
                }
            }
        }

        Text(
            text = reviewWithBook.review,
            color = Color.White,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            textAlign = TextAlign.Start,
            fontSize = 12.sp
        )
    }

}
