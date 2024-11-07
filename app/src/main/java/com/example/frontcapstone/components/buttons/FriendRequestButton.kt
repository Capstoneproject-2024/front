package com.example.frontcapstone.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frontcapstone.ui.theme.PrimaryContainerColor
import com.example.frontcapstone.ui.theme.PrimaryPurpleColor
import com.example.frontcapstone.ui.theme.TopAppbarBackgroundColor
import com.example.frontcapstone.ui.theme.UserTextPrupleColor

@Composable
fun FriendRequestButton(
    moveToFriendRequestPage: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { moveToFriendRequestPage() }
            .fillMaxWidth()
            .height(80.dp)
            .padding(8.dp)
            .background(
                color = PrimaryContainerColor,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = "Friend Request",
            fontSize = 24.sp,
            color = UserTextPrupleColor
        )

        // Notification badge
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(color = PrimaryPurpleColor, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "3",
                fontSize = 12.sp,
                color = TopAppbarBackgroundColor,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
