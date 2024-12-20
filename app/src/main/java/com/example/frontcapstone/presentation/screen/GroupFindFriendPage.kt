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
import com.example.frontcapstone.api.data.GroupMemberData
import com.example.frontcapstone.components.items.SearchGroupFriendCard
import com.example.frontcapstone.components.layout.TopMenuWithBack
import com.example.frontcapstone.components.textInput.SearchFriendTextInput
import com.example.frontcapstone.viemodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun GroupFindFriendPage(
    navigationBack: () -> Unit,
    groupFindFriendText: String,
    onGroupFindFriendTextChanged: (String) -> Unit,
    mainViewModel: MainViewModel
) {

    val searchedNonMemberList by mainViewModel.searchedNonMemberList.collectAsState()

    val coroutineScope = rememberCoroutineScope()

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
                        coroutineScope.launch {
                            mainViewModel.getSearchedNonMemberFriends(groupFindFriendText)
                        }
                    }
                )
            }
            items(searchedNonMemberList) { nonMember ->
                SearchGroupFriendCard(
                    user = nonMember,
                    onAddToGroupClicked = {
                        coroutineScope.launch {
                            mainViewModel.createMember(
                                groupMemberData = GroupMemberData(
                                    groupID = mainViewModel.chosenGroup.value.groupID,
                                    memberID = nonMember.id
                                )
                            )
                            mainViewModel.getSearchedNonMemberFriends(groupFindFriendText)
                        }
                    }
                )
            }
        }
    }
}