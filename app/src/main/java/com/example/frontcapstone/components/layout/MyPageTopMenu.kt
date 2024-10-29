package com.example.frontcapstone.components.layout

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.frontcapstone.ui.theme.TopAppBarTextColor
import com.example.frontcapstone.ui.theme.TopAppbarBackgroundColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPageTopMenu(
    title: String,
    modifier: Modifier = Modifier,
    moveToFindFriendPage: () -> Unit,
    moveToSettingPage: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = { Text("$title") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = TopAppbarBackgroundColor,
            titleContentColor = TopAppBarTextColor,
        ),
        actions = {
            IconButton(onClick = moveToFindFriendPage) {
                androidx.compose.material3.Icon(
                    tint = Color.White,
                    imageVector = Icons.Filled.Warning, contentDescription = "Add Friend"
                )
            }
            IconButton(onClick = moveToSettingPage) {
                androidx.compose.material3.Icon(
                    tint = Color.White,
                    imageVector = Icons.Filled.Settings, contentDescription = "Setting"
                )
            }
        },
    )
}