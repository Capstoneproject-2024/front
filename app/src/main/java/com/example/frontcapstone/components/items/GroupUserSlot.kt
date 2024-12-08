package com.example.frontcapstone.components.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frontcapstone.R
import com.example.frontcapstone.api.data.UserData
import com.example.frontcapstone.ui.theme.CancelPinkColor

@Composable
fun GroupUserSlot(
    user: UserData,
    onDeleteClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // User Image
        Image(
            painter = painterResource(id = R.drawable.user_profile), // 실제 프로필 이미지 리소스를 넣으세요
            contentDescription = "User Profile",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        // User Name
        Text(
            text = user.nickname,
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Remove Button
        IconButton(
            onClick = onDeleteClicked,
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                modifier = Modifier.fillMaxSize(),
                imageVector = Icons.Outlined.Close,
                contentDescription = "Cancel",
                tint = CancelPinkColor
            )
        }

    }
}