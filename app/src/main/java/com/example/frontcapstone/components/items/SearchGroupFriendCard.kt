package com.example.frontcapstone.components.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frontcapstone.R
import com.example.frontcapstone.api.data.UserData
import com.example.frontcapstone.ui.theme.PrimaryContainerColor
import com.example.frontcapstone.ui.theme.PrimaryPurpleColor
import com.example.frontcapstone.ui.theme.UserTextPrupleColor

@Composable
fun SearchGroupFriendCard(
    onAddToGroupClicked: () -> Unit,
    user: UserData,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                color = PrimaryContainerColor,
                shape = RoundedCornerShape(12.dp)
            ) // 배경색과 둥근 모서리
            .padding(horizontal = 16.dp, vertical = 12.dp), // 내부 패딩
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // 프로필 이미지와 사용자 이름
        Row(verticalAlignment = Alignment.CenterVertically) {
            // 프로필 이미지
            Image(
                painter = painterResource(id = R.drawable.user_profile), // 실제 프로필 이미지 리소스를 넣으세요
                contentDescription = "User Profile",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            // 사용자 이름
            Text(
                text = user.nickname,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = UserTextPrupleColor
            )
        }

        // 전송 요청 버튼
        Button(
            onClick = onAddToGroupClicked,
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryPurpleColor,
                contentColor = Color.Black
            ),
            modifier = Modifier
                .height(24.dp)
                .width(90.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "add to group",
                fontSize = 12.sp
            )

        }
    }
}
