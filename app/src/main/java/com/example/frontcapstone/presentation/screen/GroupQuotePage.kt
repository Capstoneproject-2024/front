package com.example.frontcapstone.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.frontcapstone.components.buttons.ReviewFrame
import com.example.frontcapstone.components.layout.BottomThreeMenu
import com.example.frontcapstone.components.layout.TopMenuWithBack

@Composable
fun GroupQuotePage(navigtionBack: () -> Unit, bottomBaronClickedActions: List<() -> Unit>) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopMenuWithBack(title = "Group Quote page", navigationBack = navigtionBack) },
        bottomBar = { BottomThreeMenu(onClickedActions = bottomBaronClickedActions) },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        )
        {
            Text("Group Quote Screen")
            ReviewFrame()
        }
    }
}