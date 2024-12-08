package com.example.frontcapstone.components.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frontcapstone.R
import com.example.frontcapstone.api.data.Comment
import com.example.frontcapstone.ui.theme.PrimaryContainerColor
import java.time.format.DateTimeFormatter

@Composable
fun Comment(
    comment: Comment
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
            .background(PrimaryContainerColor)
            .padding(8.dp),
        verticalAlignment = Alignment.Top,
    ) {
        // Profile Image
        Image(
            painter = painterResource(id = R.drawable.user_profile),
            contentDescription = "Profile Image",
            modifier = Modifier
                .padding(start = 8.dp)
                .size(32.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Comment Text and Date
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = comment.comment,
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = comment.commentDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")),
                color = Color.White,
                fontSize = 10.sp
            )
        }
    }
}
