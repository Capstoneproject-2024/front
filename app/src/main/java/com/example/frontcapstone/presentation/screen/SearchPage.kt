package com.example.frontcapstone.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.frontcapstone.components.items.SimpleBook
import com.example.frontcapstone.components.layout.BottomFiveMenu
import com.example.frontcapstone.components.textInput.SearchTextInput

@Composable
fun SearchPage(
    bottomBaronClickedActions: List<() -> Unit>,
    searchText: String,
    onSearchValueChange: (String) -> Unit,
//    onKeyboardDone: (String) -> Unit,
//    searchedBooks: List<BookData>
) {
    val focusManager = LocalFocusManager.current
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .clickable { focusManager.clearFocus() },
        topBar = {
            SearchTextInput(
                searchText = searchText,
                onSearchValueChange = onSearchValueChange,
                onKeyboardDone = {}//onKeyboardDone
            )
        },
        bottomBar = {
            BottomFiveMenu(
                onClickedActions = bottomBaronClickedActions
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(vertical = 8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize(),
                columns = GridCells.Fixed(2), // 한 줄에 2개의 아이템

            ) {
                val temps: List<String> = List(5) { "$it" }
                items(temps) { temp ->
                    SimpleBook() // 나중에 book 데이터를 활용하여 구성 가능
                }
            }
        }

    }
}