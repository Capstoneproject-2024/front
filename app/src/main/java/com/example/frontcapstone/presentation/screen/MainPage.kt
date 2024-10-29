package com.example.frontcapstone.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.frontcapstone.components.layout.BottomFiveMenu
import com.example.frontcapstone.components.layout.TopMenuWithoutBack

@Preview
@Composable
fun MainPage(bottomBaronClickedActions: List<() -> Unit>) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopMenuWithoutBack(title = "Home") },
        bottomBar = {
            BottomFiveMenu(onClickedActions = bottomBaronClickedActions)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        )
        {
            Text("Main Page")
        }
    }
}