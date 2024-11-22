package com.example.frontcapstone.components.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.frontcapstone.ui.theme.CancelPinkColor
import com.example.frontcapstone.ui.theme.PrimaryContainerColor
import com.example.frontcapstone.ui.theme.PrimaryPurpleColor
import com.example.frontcapstone.ui.theme.UserTextPrupleColor

@Composable
fun FriendRequestCard(
    requestSender: UserData,
    addButtonClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                color = PrimaryContainerColor,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp), // 내부 패딩
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // 프로필 이미지
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background), // 실제 프로필 이미지 리소스를 넣으세요
            contentDescription = "User Profile",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        // 사용자 이름
        Text(
            text = requestSender.nickname,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = UserTextPrupleColor,
            modifier = Modifier.fillMaxWidth(0.55f)
        )

        Spacer(modifier = Modifier.width(8.dp))

        // 친구 추가 버튼
        Button(
            onClick = addButtonClicked,
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
                text = "add friend",
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        IconButton(
            onClick = {/* TODO: Send request action */ },
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                modifier = Modifier.fillMaxSize(),
                imageVector = Icons.Outlined.Cancel,
                contentDescription = "Cancel",
                tint = CancelPinkColor
            )
        }
    }
}
