package com.example.frontcapstone.presentation.screen

import NewSelectBookButton
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frontcapstone.components.layout.WriteReviewTopMenu
import com.example.frontcapstone.components.textInput.QuoteTextInput
import com.example.frontcapstone.components.textInput.ReviewTextInput

@Composable
fun ReviewPage(
    navigationBack: () -> Unit,
//    onClickPost: () -> Unit,
    onQuoteTextChange: (String) -> Unit,
    quoteText: String,
    reviewText: String,
    onReviewTextChange: (String) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            WriteReviewTopMenu(
                navigationBack = navigationBack,
                onClickPost = { } //onClickPost
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
        )
        {
            NewSelectBookButton()
            QuoteTextInput(quoteText = quoteText, onQuoteTextChange = onQuoteTextChange)
            ReviewTextInput(reviewText = reviewText, onReviewTextChange = onReviewTextChange)
        }
    }
}