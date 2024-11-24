package com.example.frontcapstone.components.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.frontcapstone.R
import com.example.frontcapstone.ui.theme.PrimaryContainerColor
import com.example.frontcapstone.ui.theme.PrimaryPurpleColor
import com.example.frontcapstone.ui.theme.UserTextPrupleColor

@Composable
fun UserProfileCard(
    nickname: String,
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(160.dp)
            .background(color = PrimaryContainerColor, shape = RoundedCornerShape(12.dp))
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            // Profile image
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            ) {
                // 실제 이미지가 있
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background), // 여기에 프로필 이미지 리소스를 넣으세요
                    contentDescription = "User Profile",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // User name and friend count
            Column(
                modifier = Modifier.fillMaxWidth(0.75f)
            ) {
                Text(
                    text = nickname,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = UserTextPrupleColor
                )
                Text(
                    text = "friends 13",
                    fontSize = 12.sp,
                    color = UserTextPrupleColor
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Edit button
            Column(
                modifier = Modifier
                    .height(50.dp)
                    .padding(top = 4.dp, end = 4.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Button(
                    onClick = { /* TODO: Edit action */ },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryPurpleColor,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .height(24.dp)
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "edit",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }


        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            StatItem(title = "reviews", count = "84")
            StatItem(title = "bookmarks", count = "84")
            StatItem(title = "recommends", count = "84")
        }
    }
}

@Composable
fun StatItem(title: String, count: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = count, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.White)
        Text(text = title, fontSize = 12.sp, color = Color.White, textAlign = TextAlign.Center)
    }
}
