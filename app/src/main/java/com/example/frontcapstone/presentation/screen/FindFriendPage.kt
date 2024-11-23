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
import com.example.frontcapstone.components.items.SearchedFriendCard
import com.example.frontcapstone.components.layout.TopMenuWithBack
import com.example.frontcapstone.components.textInput.SearchFriendTextInput
import com.example.frontcapstone.viemodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun FindFriendPage(
    navigationBack: () -> Unit,
    findFriendText: String,
    onFindFriendTextChanged: (String) -> Unit,
    mainViewModel: MainViewModel
) {
    val searchedUserList by mainViewModel.searchedUserList.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopMenuWithBack(title = "Find Friend", navigationBack = navigationBack) },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            item {
                SearchFriendTextInput(
                    findFriendText = findFriendText,
                    onFindFriendTextChanged = onFindFriendTextChanged,
                    onKeyboardDone = {
                        coroutineScope.launch {
                            mainViewModel.getUsersByEmail(findFriendText)
                        }
                    }
                )
            }
            items(searchedUserList) { user ->
                SearchedFriendCard(
                    name = user.nickname
                )
            }
        }
    }
}