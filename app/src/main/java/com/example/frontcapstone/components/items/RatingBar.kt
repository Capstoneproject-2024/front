package com.example.frontcapstone.components.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frontcapstone.ui.theme.PrimaryPurpleColor

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Float,
    onRatingChanged: (Float) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(5) { index ->
            val starRating = index + 1f
            Icon(
                imageVector = when {
                    starRating <= rating -> Icons.Filled.Star // 꽉 찬 별
                    starRating - 0.5f <= rating -> Icons.AutoMirrored.Filled.StarHalf // 반 별
                    else -> Icons.Outlined.StarOutline // 빈 별
                },
                contentDescription = null,
                tint = PrimaryPurpleColor,
                modifier = modifier
                    .clickable {
                        val newRating = when {
                            rating < starRating - 0.5f -> starRating - 0.5f // 반 별로 설정
                            rating < starRating -> starRating // 꽉 찬 별로 설정
                            else -> starRating - 0.5f // 반 별로 다시 전환
                        }
                        onRatingChanged(newRating)
                    }
                    .size(50.dp)// 클릭 이벤트 추가
            )
        }
    }
}