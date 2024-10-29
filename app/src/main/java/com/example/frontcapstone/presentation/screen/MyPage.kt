package com.example.frontcapstone.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.frontcapstone.components.layout.BottomFiveMenu
import com.example.frontcapstone.components.layout.MyPageTopMenu

@Composable
fun MyPage(
    bottomBaronClickedActions: List<() -> Unit>,
    moveToFindFriendPage: () -> Unit,
    moveToSettingPage: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyPageTopMenu(
                title = "My Page",
                moveToFindFriendPage = moveToFindFriendPage,
                moveToSettingPage = moveToSettingPage
            )
        },
        bottomBar = { BottomFiveMenu(onClickedActions = bottomBaronClickedActions) }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        )
        {
            Text("My Page Screen")
        }
    }
}