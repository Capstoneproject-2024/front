package com.example.frontcapstone.components.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.frontcapstone.ui.theme.BottomAppbarTextColor
import com.example.frontcapstone.ui.theme.BottomBarBackgroundColor

@Composable
fun BottomFiveMenu(modifier: Modifier = Modifier, onClickedActions: List<() -> Unit>) {
    if (onClickedActions.size != 5) {
        throw IllegalArgumentException("onClickedActions must contain exactly five functions.")
    }
    BottomAppBar(
        actions = {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconWithText(
                    name = "Group",
                    icon = Icons.Default.Warning,
                    onClicked = onClickedActions[0]
                )
                IconWithText(
                    name = "Search",
                    icon = Icons.Default.Search,
                    onClicked = onClickedActions[1]
                )
                IconWithText(
                    name = "Home",
                    icon = Icons.Default.Home,
                    onClicked = onClickedActions[2]
                )
                IconWithText(
                    name = "Notice",
                    icon = Icons.Default.Notifications,
                    onClicked = onClickedActions[3]
                )
                IconWithText(
                    name = "My",
                    icon = Icons.Default.Person,
                    onClicked = onClickedActions[4]
                )
            }
        },
        containerColor = BottomBarBackgroundColor,
        contentColor = BottomAppbarTextColor,
    )
}