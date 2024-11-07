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
import com.example.frontcapstone.components.buttons.PastQuestionButton
import com.example.frontcapstone.components.buttons.ReviewFrame
import com.example.frontcapstone.components.items.Line
import com.example.frontcapstone.components.layout.BottomThreeMenu
import com.example.frontcapstone.components.layout.TopMenuWithBack

@Composable
fun GroupArchivePage(navigtionBack: () -> Unit, bottomBaronClickedActions: List<() -> Unit>) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopMenuWithBack(title = "Group Archive page", navigationBack = navigtionBack) },
        bottomBar = { BottomThreeMenu(onClickedActions = bottomBaronClickedActions) },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp) // 각 아이템 사이에 간격 추가
        ) {
            item {
                PastQuestionButton(modifier = Modifier.padding(top = 20.dp))
            }

            // List items with ReviewFrame and Line
            val temps: List<String> = List(10) { "$it" }
            itemsIndexed(temps) { index, temp ->
                ReviewFrame()
                if (index < temps.size - 1) { // 마지막 아이템이 아닌 경우에만 Line 추가
                    Line()
                }
            }
        }
    }
}