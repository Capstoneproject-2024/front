package com.example.frontcapstone.components.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.frontcapstone.R
import com.example.frontcapstone.ui.theme.PrimaryPurpleColor

@Composable
fun SimpleBook() {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .width(120.dp)
            .height(170.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = "https://shopping-phinf.pstatic.net/main_3245996/32459963667.20221019105132.jpg",
                contentDescription = "Book Cover",
                modifier = Modifier
                    .width(110.dp)
                    .height(145.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "bookName df sdf waefawefa awefwafe ",
                style = TextStyle(
                    color = PrimaryPurpleColor,
                    fontSize = 12.sp,
//                    fontWeight = FontWeight.Light
                ),
                maxLines = 1, // 최대 1줄로 제한
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis, // 넘치는 부분 생략 부호 표시
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .width(110.dp)
            )
        }
    }
}