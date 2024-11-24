package com.example.frontcapstone.presentation.screen

import NewGroupButton
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frontcapstone.components.buttons.GroupCard
import com.example.frontcapstone.components.layout.BottomFiveMenu
import com.example.frontcapstone.components.layout.TopMenuWithoutBack
import com.example.frontcapstone.ui.theme.QuoteQuestionMintTextColor
import com.example.frontcapstone.ui.theme.SecondaryGroupGreenColor
import com.example.frontcapstone.viemodel.MainViewModel

@Composable
fun GroupPage(
    bottomBaronClickedActions: List<() -> Unit>,
    onEditClicked: () -> Unit,
    onCardClicked: () -> Unit,
    onNewGroupClicked: () -> Unit,
    mainViewModel: MainViewModel
) {
    val groupList by mainViewModel.groupList.collectAsState()

    LaunchedEffect(Unit) {
        mainViewModel.getUserGroups()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopMenuWithoutBack(title = "Group") },
        bottomBar = { BottomFiveMenu(onClickedActions = bottomBaronClickedActions) }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
                .fillMaxSize()
        ) {
            items(groupList) { group ->
                GroupCard(
                    mainColor = if (group.role == "admin") QuoteQuestionMintTextColor else SecondaryGroupGreenColor,
                    onCardClicked = onCardClicked,
                    onEditClicked = onEditClicked,
                    editPossible = group.role == "admin",
                    groupName = group.groupName,
                    groupDescription = group.groupDescription
                )
            }
            item {
                NewGroupButton(
                    onClicked = onNewGroupClicked
                )
            }

        }

    }
}