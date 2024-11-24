package com.example.frontcapstone.components.buttons

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BorderColor
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun WrittingFloatingButton(modifier: Modifier = Modifier, onClicked: () -> Unit) {
    FloatingActionButton(
        onClick = onClicked,
        modifier = modifier
            .clip(shape = CircleShape)
            .size(60.dp),
    ) {
        androidx.compose.material3.Icon(
            modifier = Modifier.size(28.dp),
            imageVector = Icons.Outlined.BorderColor,
            contentDescription = "Edit",
        )
    }
}