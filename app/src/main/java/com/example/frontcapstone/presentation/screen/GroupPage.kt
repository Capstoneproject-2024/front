package com.example.frontcapstone.presentation.screen

import NewGroupButton
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frontcapstone.components.buttons.GroupCard
import com.example.frontcapstone.components.layout.BottomFiveMenu
import com.example.frontcapstone.components.layout.TopMenuWithoutBack
import com.example.frontcapstone.ui.theme.QuoteQuestionMintTextColor

@Composable
fun GroupPage(
    bottomBaronClickedActions: List<() -> Unit>,
    onEditClicked: () -> Unit,
    onCardClicked: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopMenuWithoutBack(title = "Group") },
        bottomBar = { BottomFiveMenu(onClickedActions = bottomBaronClickedActions) }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        )
        {
            val temps: List<Int> = List(2) { it }
//            LazyColumn(modifier = Modifier.padding(6.dp)) {
//                items(temps) { temp ->
//                    GroupCard(
//                        mainColor = QuoteQuestionMintTextColor,
//                        onCardClicked = onCardClicked,
//                        onEditClicked = onEditClicked,
//                        editPossible = (temp % 2 == 0)
//                    )
//                }
//            }
            temps.forEach { temp ->
                GroupCard(
                    mainColor = QuoteQuestionMintTextColor,
                    onCardClicked = onCardClicked,
                    onEditClicked = onEditClicked,
                    editPossible = (temp % 2 == 0)
                )
            }
            NewGroupButton()

        }
    }
}