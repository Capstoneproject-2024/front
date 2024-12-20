package com.example.frontcapstone.components.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


@Composable
fun IconWithText(
    modifier: Modifier = Modifier,
    name: String,
    icon: ImageVector,
    onClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClicked)
            .fillMaxHeight()
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = { onClicked() }) {
            androidx.compose.material3.Icon(imageVector = icon, contentDescription = name)
        }
        Text(
            text = name
        )
    }
}