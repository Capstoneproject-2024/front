package com.example.frontcapstone.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frontcapstone.components.buttons.LegacyReviewFrame
import com.example.frontcapstone.components.buttons.WrittingFloatingButton
import com.example.frontcapstone.components.items.Line
import com.example.frontcapstone.components.items.QuoteQuestionCard
import com.example.frontcapstone.components.layout.BottomThreeMenu
import com.example.frontcapstone.components.layout.TopMenuWithBack

@Composable
fun QuoteQuestionPage(
    navigationBack: () -> Unit,
    bottomBaronClickedActions: List<() -> Unit>,
    editPossibleOrNot: Boolean = true,
    onEditButtonClicked: () -> Unit,
    onReviewClicked: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopMenuWithBack(title = "Group Quote page", navigationBack = navigationBack) },
        bottomBar = { BottomThreeMenu(onClickedActions = bottomBaronClickedActions) },
        floatingActionButton = {
            if (editPossibleOrNot) {
                WrittingFloatingButton(
                    onClicked = {
                        if (editPossibleOrNot) {
                            onEditButtonClicked()
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp) // 각 아이템 사이에 간격 추가
        ) {
            item {
                QuoteQuestionCard()
            }

            // List items with ReviewFrame and Line
            val temps: List<String> = List(1) { "$it" }
            itemsIndexed(temps) { index, temp ->
                LegacyReviewFrame(onClicked = onReviewClicked)
                if (index < temps.size - 1) { // 마지막 아이템이 아닌 경우에만 Line 추가
                    Line()
                }
            }
        }
    }
}
