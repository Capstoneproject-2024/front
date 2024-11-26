package com.example.frontcapstone.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frontcapstone.components.buttons.PastQuestionButton
import com.example.frontcapstone.components.buttons.QuoteReviewFrame
import com.example.frontcapstone.components.items.BookRecomendationCard
import com.example.frontcapstone.components.items.Line
import com.example.frontcapstone.components.layout.BottomThreeMenu
import com.example.frontcapstone.components.layout.TopMenuWithBack
import com.example.frontcapstone.viemodel.MainViewModel

@Composable
fun GroupArchivePage(
    navigationBack: () -> Unit,
    bottomBaronClickedActions: List<() -> Unit>,
    onReviewClicked: () -> Unit,
    mainViewModel: MainViewModel

) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val pastQuoteQuestion by mainViewModel.pastQuoteQuestion.collectAsState()
    val pastQuoteAnswers by mainViewModel.pastQuoteAnswers.collectAsState()


    LaunchedEffect(Unit) {
        mainViewModel.getPastQuestion()
        mainViewModel.getPastQuestion()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopMenuWithBack(title = "Group Archive page", navigationBack = navigationBack) },
        bottomBar = { BottomThreeMenu(onClickedActions = bottomBaronClickedActions) },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp) // 각 아이템 사이에 간격 추가
        ) {
            item {
                PastQuestionButton(
                    modifier = Modifier.padding(top = 20.dp),
                    pastQuestion = pastQuoteQuestion
                )
            }

            // List items with ReviewFrame and Line
            itemsIndexed(pastQuoteAnswers) { index, answer ->
                QuoteReviewFrame(
                    quoteAnswer = answer
                )
                if (index < pastQuoteAnswers.size - 1) { // 마지막 아이템이 아닌 경우에만 Line 추가
                    Line()
                }
            }
            item {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth()
                ) { page ->
                    BookRecomendationCard()
                }
            }

        }
    }
}


