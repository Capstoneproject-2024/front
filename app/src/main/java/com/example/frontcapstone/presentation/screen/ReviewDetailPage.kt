package com.example.frontcapstone.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frontcapstone.components.buttons.ReviewDetailFrame
import com.example.frontcapstone.components.items.Comment
import com.example.frontcapstone.components.layout.TopMenuWithBack
import com.example.frontcapstone.components.textInput.CommentTextInput
import com.example.frontcapstone.viemodel.MainViewModel

@Composable
fun ReviewDetailPage(
    navigationBack: () -> Unit,
    mainViewModel: MainViewModel
) {
    val chosenReview by mainViewModel.chosenReview.collectAsState()
    val chosenReviewCommentList by mainViewModel.chosenReviewCommentList.collectAsState() //불려지는 페이지에서 세팅해줘야함.

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopMenuWithBack(title = "Find Friend", navigationBack = navigationBack) },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            item {
                ReviewDetailFrame(
                    reviewWithBook = chosenReview
                )
            }
            items(chosenReviewCommentList) { comment ->
                Comment(
                    comment = comment
                )
            }
            item {
                CommentTextInput()
            }
        }
    }
}