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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frontcapstone.components.buttons.ReviewFrame
import com.example.frontcapstone.components.items.Line
import com.example.frontcapstone.components.layout.TopMenuWithBack
import com.example.frontcapstone.viemodel.MainViewModel

@Composable
fun MyReviewPage(
    onClickBack: () -> Unit,
    onReviewClicked: () -> Unit,
    mainViewModel: MainViewModel
) {
    val reviewList by mainViewModel.myReviewList.collectAsState()

    LaunchedEffect(Unit) {
        mainViewModel.getTimelineReview()
        mainViewModel.getMyReview()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopMenuWithBack(
                title = "My Review",
                navigationBack = onClickBack
            )

        },


        ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            itemsIndexed(reviewList) { index, review ->
                ReviewFrame(
                    onClicked = {
                        mainViewModel.updateChosenReview(review)
                        onReviewClicked()
                    },
                    reviewWithBook = review,
                )

                if (index < reviewList.size - 1) { // 마지막 아이템이 아닌 경우에만 Line 추가
                    Line()
                }
            }
        }

    }
}