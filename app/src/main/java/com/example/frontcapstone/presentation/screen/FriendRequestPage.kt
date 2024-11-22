package com.example.frontcapstone.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frontcapstone.api.data.UserData
import com.example.frontcapstone.components.items.FriendRequestCard
import com.example.frontcapstone.components.layout.TopMenuWithBack
import com.example.frontcapstone.viemodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun FriendRequestPage(
    navigationBack: () -> Unit,
    mainViewModel: MainViewModel
) {
    val requestSenderList by mainViewModel.requestSenderList.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopMenuWithBack(title = "Friend Request", navigationBack = navigationBack) },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            items(requestSenderList) { requestSender: UserData ->
                FriendRequestCard(
                    requestSender = requestSender,
                    addButtonClicked = {
                        coroutineScope.launch {
                            mainViewModel.createFriendAndAutoDelete(requestSenderID = requestSender.id)
                        }
                    }
                )
            }
        }
    }
}