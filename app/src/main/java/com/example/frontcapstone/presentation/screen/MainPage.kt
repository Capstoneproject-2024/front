package com.example.frontcapstone.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frontcapstone.components.buttons.ReviewFrame
import com.example.frontcapstone.components.buttons.WrittingFloatingButton
import com.example.frontcapstone.components.items.Line
import com.example.frontcapstone.components.layout.BottomFiveMenu
import com.example.frontcapstone.components.layout.TopMenuWithoutBack
import com.example.frontcapstone.viemodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun MainPage(
    bottomBaronClickedActions: List<() -> Unit>,
    onFloatingButtonCLicked: () -> Unit,
    onReviewClicked: () -> Unit,
    mainViewModel: MainViewModel
) {
    val mainTimelineReviewList by mainViewModel.mainTimelineReviewList.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        mainViewModel.getTimelineReview()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopMenuWithoutBack(title = "Home") },
        bottomBar = {
            BottomFiveMenu(onClickedActions = bottomBaronClickedActions)
        },
        floatingActionButton = { WrittingFloatingButton(onClicked = onFloatingButtonCLicked) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            itemsIndexed(mainTimelineReviewList) { index, review ->
                ReviewFrame(
                    onClicked = {
                        mainViewModel.updateChosenReview(review)
                        coroutineScope.launch {
                            mainViewModel.getComments(reviewID = review.id)
                        }
                        onReviewClicked()
                    },
                    reviewWithBook = review,
                )

                if (index < mainTimelineReviewList.size - 1) { // 마지막 아이템이 아닌 경우에만 Line 추가
                    Line()
                }
            }
        }

    }
}