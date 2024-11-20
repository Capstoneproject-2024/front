package com.example.frontcapstone.components.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.frontcapstone.ui.theme.PrimaryPurpleColor

@Composable
fun BookDetail(
    name: String,
    year: String,
    author: String,
    image: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top
    ) {
        AsyncImage(
            model = image,
            contentDescription = "Book Cover",
            modifier = Modifier
                .width(98.72.dp)
                .height(145.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        // 텍스트 섹션
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${name} | ${year} | ${author}",
                color = PrimaryPurpleColor,
                fontSize = 16.sp,
            )

//            Text(
//                text = "Lorem ipsum dolor sit amet consectetur. Porta nibh tortor etiam adipiscing sed enim ut. Diam lacinia convallis parturient faucibus pulvinar elit amet. Purus orci adipiscing in enim placerat eros mauris neque praesent. Lorem ipsum dolor sit amet consectetur. Lo..",
//                color = Color.White,
//                fontSize = 12.sp,
//                lineHeight = 16.sp
//            )
        }
    }
}