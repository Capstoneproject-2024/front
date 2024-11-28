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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frontcapstone.api.data.BookData
import com.example.frontcapstone.components.buttons.PastQuestionButton
import com.example.frontcapstone.components.buttons.QuoteReviewFrame
import com.example.frontcapstone.components.items.BookRecommendationCard
import com.example.frontcapstone.components.items.Line
import com.example.frontcapstone.components.layout.BottomThreeMenu
import com.example.frontcapstone.components.layout.TopMenuWithBack
import com.example.frontcapstone.viemodel.MainViewModel

@Composable
fun GroupArchivePage(
    navigationBack: () -> Unit,
    bottomBaronClickedActions: List<() -> Unit>,
    onRecommendBookClicked: (BookData) -> Unit,
    mainViewModel: MainViewModel

) {
    val pastQuoteQuestion by mainViewModel.pastQuoteQuestion.collectAsState()
    val pastQuoteAnswers by mainViewModel.pastQuoteAnswers.collectAsState()
    val questionRecommendBooks by mainViewModel.questionRecommendBookList.collectAsState()
    val userState by mainViewModel.userState.collectAsState()

    val userIDList = rememberSaveable { mutableStateOf(emptyList<Int>()) } // userIDList는 빈 리스트로 초기화
    val pagerState = rememberPagerState(
        pageCount = { userIDList.value.size } // 페이지 개수는 userIDList 크기로 설정
    )

    // questionRecommendBooks 업데이트에 따라 userIDList와 pagerState 업데이트
    LaunchedEffect(questionRecommendBooks, userState) {
        userIDList.value = questionRecommendBooks.keys.mapNotNull { it.toIntOrNull() }
        val initialPage = userIDList.value.indexOf(userState.id).takeIf { it != -1 } ?: 0
        pagerState.scrollToPage(initialPage)
    }

    LaunchedEffect(Unit) {
        mainViewModel.getPastQuestion()
        mainViewModel.getPastQuestionAnswers()
        mainViewModel.getQuestionRecommend()
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
                    BookRecommendationCard(
                        bookList = questionRecommendBooks[userIDList.value.getOrNull(page)
                            ?.toString()]
                            ?: emptyList(),
                        onRecommendBookClicked = onRecommendBookClicked
                    )
                }
            }

        }
    }
}


