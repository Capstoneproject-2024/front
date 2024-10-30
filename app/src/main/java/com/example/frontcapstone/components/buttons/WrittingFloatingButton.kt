package com.example.frontcapstone.components.buttons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BorderColor
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun WrittingFloatingButton(modifier: Modifier = Modifier, onClicked: () -> Unit) {
    FloatingActionButton(onClick = onClicked) {
        androidx.compose.material3.Icon(
            imageVector = Icons.Default.BorderColor,
            contentDescription = "Edit"
        )
    }
}