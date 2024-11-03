package com.example.frontcapstone.components.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.frontcapstone.components.items.IconWithText
import com.example.frontcapstone.ui.theme.BottomAppbarTextColor
import com.example.frontcapstone.ui.theme.BottomBarBackgroundColor

@Composable
fun BottomThreeMenu(modifier: Modifier = Modifier, onClickedActions: List<() -> Unit>) {
    if (onClickedActions.size != 3) {
        throw IllegalArgumentException("onClickedActions must contain exactly five functions.")
    }
    BottomAppBar(
        modifier = Modifier.fillMaxWidth(),
        actions = {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconWithText(
                    name = "Quote",
                    icon = Icons.Default.ChatBubbleOutline,
                    onClicked = onClickedActions[0]
                )
                IconWithText(
                    name = "Member",
                    icon = Icons.Outlined.Group,
                    onClicked = onClickedActions[1]
                )
                IconWithText(
                    name = "Archive",
                    icon = Icons.Outlined.Folder,
                    onClicked = onClickedActions[2]
                )
            }
        },
        containerColor = BottomBarBackgroundColor,
        contentColor = BottomAppbarTextColor,
    )
}