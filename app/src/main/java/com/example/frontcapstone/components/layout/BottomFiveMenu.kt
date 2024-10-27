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
import androidx.compose.ui.tooling.preview.Preview
import com.example.frontcapstone.ui.theme.BottomAppbarTextColor
import com.example.frontcapstone.ui.theme.BottomBarBackgroundColor

@Preview(showBackground = true)
@Composable
fun BottomFiveMenu(modifier: Modifier = Modifier) {
    BottomAppBar(
        actions = {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconWithText(name = "Group", icon = Icons.Default.Warning, onClicked = {/*TODO*/ })
                IconWithText(name = "Search", icon = Icons.Default.Search, onClicked = {/*TODO*/ })
                IconWithText(name = "Home", icon = Icons.Default.Home, onClicked = {/*TODO*/ })
                IconWithText(
                    name = "Notice",
                    icon = Icons.Default.Notifications,
                    onClicked = {/*TODO*/ })
                IconWithText(name = "My", icon = Icons.Default.Person, onClicked = {/*TODO*/ })
            }
        },
        containerColor = BottomBarBackgroundColor,
        contentColor = BottomAppbarTextColor,
    )
}