package com.example.frontcapstone.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            PastQuestionButton(modifier = Modifier.padding(top = 20.dp))
            val temps: List<String> = List(3) { "$it" }
            LazyColumn(modifier = Modifier.padding(6.dp)) {
                items(temps) { temp ->
                    ReviewFrame()
                    Line()
                }
            }
        }
    }
}