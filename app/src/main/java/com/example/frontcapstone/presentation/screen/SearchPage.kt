package com.example.frontcapstone.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.example.frontcapstone.components.layout.BottomFiveMenu
import com.example.frontcapstone.components.textInput.SearchTextInput

@Composable
fun SearchPage(
    bottomBaronClickedActions: List<() -> Unit>,
    searchText: String,
    onSearchValueChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .clickable { focusManager.clearFocus() },
        topBar = {
            SearchTextInput(
                searchText = searchText,
                onSearchValueChange = onSearchValueChange
            )
        },
        bottomBar = { BottomFiveMenu(onClickedActions = bottomBaronClickedActions) }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        )
        {
            Text("Search Screen")
        }
    }
}