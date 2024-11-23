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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.frontcapstone.components.items.SimpleBook
import com.example.frontcapstone.components.textInput.SearchTextInput
import com.example.frontcapstone.viemodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun SearchPageWithoutBottomBar(
    navigationBack: () -> Unit,
    searchText: String,
    onSearchValueChange: (String) -> Unit,
    mainViewModel: MainViewModel,
) {
    val books by mainViewModel.searchedBooks.collectAsState(initial = emptyList())
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .clickable { focusManager.clearFocus() },
        topBar = {
            SearchTextInput(
                searchText = searchText,
                onSearchValueChange = onSearchValueChange,
                onKeyboardDone = {
                    coroutineScope.launch {
                        mainViewModel.searchedBookList(bookName = searchText)
                        focusManager.clearFocus()
                    }
                }
            )
        },
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
                columns = GridCells.Fixed(2),
            ) {
                items(books) {
                    SimpleBook(
                        name = it.name,
                        image = it.image,
                        onClicked = {
                            coroutineScope.launch {
                                mainViewModel.searchedBook(it.id)
                                navigationBack()
                            }
                        }
                    )
                }
            }
        }

    }
}