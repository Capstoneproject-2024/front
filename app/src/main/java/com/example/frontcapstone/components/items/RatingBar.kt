package com.example.frontcapstone.components.items

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarRate
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frontcapstone.ui.theme.PrimaryPurpleColor

@Composable
fun RatingBar(modifier: Modifier = Modifier, rating: Int) {
    Row(modifier = modifier) {
        repeat(5) { index ->
            Icon(
                imageVector = if (index < rating) Icons.Filled.Star else Icons.Outlined.StarRate,
                contentDescription = null,
                tint = if (index < rating) PrimaryPurpleColor else PrimaryPurpleColor,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}