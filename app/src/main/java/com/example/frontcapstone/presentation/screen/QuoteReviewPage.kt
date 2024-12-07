package com.example.frontcapstone.presentation.screen

import NewSelectBookButton
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frontcapstone.api.data.PostQuoteAnswer
import com.example.frontcapstone.components.items.BookDetail
import com.example.frontcapstone.components.layout.WriteReviewTopMenu
import com.example.frontcapstone.components.textInput.QuoteTextInput
import com.example.frontcapstone.viemodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun QuoteReviewPage(
    navigationBack: () -> Unit,
    onQuoteTextChange: (String) -> Unit,
    quoteText: String,
    onSelectButtonClicked: () -> Unit,
    onBookClicked: () -> Unit,
    mainViewModel: MainViewModel
) {
    val chosenBook by mainViewModel.chosenBook.collectAsState()

    var showError by rememberSaveable { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            WriteReviewTopMenu(
                navigationBack = navigationBack,
                onClickPost = {
                    if (quoteText.isBlank()) {
                        showError = true // 빈 문자열일 경우 경고 표시
                    } else {
                        coroutineScope.launch {
                            mainViewModel.createQuoteQuestion(
                                postQuoteAnswer = PostQuoteAnswer(
                                    questionID = mainViewModel.presentQuoteQuestion.value.id,
                                    userID = mainViewModel.userState.value.id,
                                    bookID = chosenBook.id,
                                    quotation = quoteText,
                                )
                            )
                            mainViewModel.clearChosenBook()
                            navigationBack()
                        }
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
        )
        {
            if (chosenBook.id >= 0) {
                BookDetail(
                    name = chosenBook.name,
                    year = chosenBook.year,
                    author = chosenBook.author,
                    image = chosenBook.image,
                    onClicked = onBookClicked,
                    desc = chosenBook.desc
                )
            } else {
                NewSelectBookButton(
                    onClicked = onSelectButtonClicked
                )
            }
            QuoteTextInput(quoteText = quoteText, onQuoteTextChange = {
                onQuoteTextChange(it)
                showError = it.isBlank()
            })

            if (showError) {
                Text(
                    text = "review and quote cannot be empty!",
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}