package com.example.frontcapstone.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
        )
        {
            val temps: List<Int> = List(10) { it }
            LazyColumn(modifier = Modifier.padding(6.dp)) {
                items(temps) { temp ->
                    GroupCard(
                        mainColor = QuoteQuestionMintTextColor,
                        onCardClicked = onCardClicked,
                        onEditClicked = onEditClicked,
                        editPossible = (temp % 2 == 0)
                    )
                }
            }

        }
    }
}