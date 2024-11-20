package com.example.frontcapstone.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frontcapstone.components.buttons.WrittingFloatingButton
import com.example.frontcapstone.components.items.BookDetail
import com.example.frontcapstone.components.layout.BottomFiveMenu
import com.example.frontcapstone.components.layout.TopMenuWithBack
import com.example.frontcapstone.viemodel.MainViewModel

@Composable
fun BookDetailPage(
    navigationBack: () -> Unit,
    bottomBaronClickedActions: List<() -> Unit>,
    onFloatingButtonCLicked: () -> Unit,
    mainViewModel: MainViewModel
) {
    val chosenBook by mainViewModel.chosenBook.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopMenuWithBack(title = chosenBook.name, navigationBack = navigationBack) },
        bottomBar = {
            BottomFiveMenu(onClickedActions = bottomBaronClickedActions)
        },
        floatingActionButton = { WrittingFloatingButton(onClicked = onFloatingButtonCLicked) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            BookDetail(
                name = chosenBook.name,
                year = chosenBook.year,
                author = chosenBook.author,
                image = chosenBook.image
            )
        }
    }
}