package com.example.frontcapstone.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frontcapstone.components.buttons.ReviewFrame
import com.example.frontcapstone.components.buttons.WrittingFloatingButton
import com.example.frontcapstone.components.items.Line
import com.example.frontcapstone.components.layout.BottomFiveMenu
import com.example.frontcapstone.components.layout.TopMenuWithoutBack

@Composable
fun MainPage(
    bottomBaronClickedActions: List<() -> Unit>,
    onFloatingButtonCLicked: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopMenuWithoutBack(title = "Home") },
        bottomBar = {
            BottomFiveMenu(onClickedActions = bottomBaronClickedActions)
        },
        floatingActionButton = { WrittingFloatingButton(onClicked = onFloatingButtonCLicked) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
        )
        {
            ReviewFrame()
            Line()
        }
    }
}