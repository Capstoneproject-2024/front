package com.example.frontcapstone.components.items

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frontcapstone.ui.theme.PrimaryPurpleColor


@Composable
fun FixedRatingBar(modifier: Modifier = Modifier, rating: Float) {
    Row(modifier = modifier) {
        repeat(5) { index ->
            val icon = when {
                index < rating.toInt() -> Icons.Filled.Star // 완전한 별
                index < rating -> Icons.AutoMirrored.Filled.StarHalf // 반 별
                else -> Icons.Outlined.StarOutline // 빈 별
            }

            Icon(
                imageVector = icon,
                contentDescription = "rating star",
                tint = PrimaryPurpleColor,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}