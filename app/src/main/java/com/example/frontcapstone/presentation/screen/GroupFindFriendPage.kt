package com.example.frontcapstone.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frontcapstone.components.layout.TopMenuWithBack
import com.example.frontcapstone.components.textInput.SearchFriendTextInput
import com.example.frontcapstone.viemodel.MainViewModel

@Composable
fun GroupFindFriendPage(
    navigationBack: () -> Unit,
    groupFindFriendText: String,
    onGroupFindFriendTextChanged: (String) -> Unit,
    mainViewModel: MainViewModel
) {
//    val coroutineScope = rememberCoroutineScope()


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopMenuWithBack(title = "Find User", navigationBack = navigationBack) },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            item {
                SearchFriendTextInput(
                    findFriendText = groupFindFriendText,
                    onFindFriendTextChanged = onGroupFindFriendTextChanged,
                    onKeyboardDone = {
//                        coroutineScope.launch {
//                            mainViewModel.getFriends()
//                            mainViewModel.getBothRequest()
//                            mainViewModel.getUsersByEmail(groupFindFriendText)
//                        }
                    }
                )
            }
//            items(searchedUserList) { user ->
//                SearchedFriendCard(
//                    user = user,
//                    friendsList = friendsList,
//                    friendCandidateList = friendCandidateList,
//                    meID = userState.id,
//                    onSendRequestClicked = {
//                        coroutineScope.launch {
//                            launch { mainViewModel.createFollowerRequest(receiverID = user.id) }.join()
//                            launch { mainViewModel.getBothRequest() }.join()
//                        }
//                    }
//                )
//            }
        }
    }
}