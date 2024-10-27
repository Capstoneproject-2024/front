package com.example.frontcapstone.components.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector


@Composable
fun IconWithText(name: String, icon: ImageVector, onClicked: () -> Unit) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(onClick = onClicked) {
            androidx.compose.material3.Icon(imageVector = icon, contentDescription = name)
        }
        Text(
            text = name
        )
    }
}