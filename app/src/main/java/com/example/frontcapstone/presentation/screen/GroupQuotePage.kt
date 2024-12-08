package com.example.frontcapstone.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frontcapstone.components.buttons.QuestionButtonWithEdit
import com.example.frontcapstone.components.buttons.QuoteReviewFrame
import com.example.frontcapstone.components.items.Line
import com.example.frontcapstone.components.layout.BottomThreeMenu
import com.example.frontcapstone.components.layout.TopMenuWithBack
import com.example.frontcapstone.viemodel.MainViewModel

@Composable
fun GroupQuotePage(
    navigationBack: () -> Unit,
    bottomBaronClickedActions: List<() -> Unit>,
//    onQuoteQuestionClicked: () -> Unit,
    onEditButtonClicked: () -> Unit,
    mainViewModel: MainViewModel
) {
    val presentQuoteQuestion by mainViewModel.presentQuoteQuestion.collectAsState()
    val presentQuoteAnswers by mainViewModel.presentQuoteAnswers.collectAsState()

    val hasEverWrittenAnswer =
        presentQuoteAnswers.any { it.userID == mainViewModel.userState.value.id }


    LaunchedEffect(Unit) {
        mainViewModel.getPresentQuestion()
        mainViewModel.getPresentQuestionAnswers()
    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopMenuWithBack(title = "Group Quote page", navigationBack = navigationBack) },
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
                QuestionButtonWithEdit(
                    modifier = Modifier.padding(top = 20.dp),
                    onQuoteQuestionClicked = { },
                    onEditButtonClicked = onEditButtonClicked,
                    question = presentQuoteQuestion,
                    hasEverWrittenAnswer = hasEverWrittenAnswer
                )
            }

            // List items with ReviewFrame and Line
            itemsIndexed(presentQuoteAnswers) { index, answer ->
                QuoteReviewFrame(
                    quoteAnswer = answer
                )
                if (index < presentQuoteAnswers.size - 1) { // 마지막 아이템이 아닌 경우에만 Line 추가
                    Line()
                }
            }
        }
    }
}
