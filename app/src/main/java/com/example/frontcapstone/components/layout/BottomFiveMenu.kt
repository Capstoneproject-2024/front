package com.example.frontcapstone.components.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.frontcapstone.components.items.IconWithText
import com.example.frontcapstone.ui.theme.BottomAppbarTextColor
import com.example.frontcapstone.ui.theme.BottomBarBackgroundColor

@Composable
fun BottomFiveMenu(onClickedActions: List<() -> Unit>) {
    if (onClickedActions.size != 5) {
        throw IllegalArgumentException("onClickedActions must contain exactly five functions.")
    }
    BottomAppBar(
        modifier = Modifier.fillMaxWidth(),
        actions = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconWithText(
                    name = "Group",
                    icon = Icons.Outlined.Group,
                    onClicked = onClickedActions[0]
                )
                IconWithText(
                    name = "Search",
                    icon = Icons.Default.Search,
                    onClicked = onClickedActions[1]
                )
                IconWithText(
                    name = "Home",
                    icon = Icons.Outlined.Home,
                    onClicked = onClickedActions[2]
                )
                IconWithText(
                    name = "Notice",
                    icon = Icons.Outlined.Notifications,
                    onClicked = onClickedActions[3]
                )
                IconWithText(
                    name = "My",
                    icon = Icons.Outlined.Person,
                    onClicked = onClickedActions[4]
                )
            }
        },
        containerColor = BottomBarBackgroundColor,
        contentColor = BottomAppbarTextColor,
    )
}