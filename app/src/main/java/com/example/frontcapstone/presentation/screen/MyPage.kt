package com.example.frontcapstone.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frontcapstone.components.buttons.FriendRequestButton
import com.example.frontcapstone.components.items.UserProfileCard
import com.example.frontcapstone.components.layout.BottomFiveMenu
import com.example.frontcapstone.components.layout.MyPageTopMenu
import com.example.frontcapstone.viemodel.MainViewModel

@Composable
fun MyPage(
    bottomBaronClickedActions: List<() -> Unit>,
    moveToFindFriendPage: () -> Unit,
    moveToSettingPage: () -> Unit,
    moveToFriendRequestPage: () -> Unit,
    mainViewModel: MainViewModel
) {
    val userState by mainViewModel.userData.collectAsState()

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
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(innerPadding)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            UserProfileCard(nickname = userState.nickname)
            FriendRequestButton(moveToFriendRequestPage = moveToFriendRequestPage)
        }
    }
}