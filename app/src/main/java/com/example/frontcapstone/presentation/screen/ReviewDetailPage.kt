package com.example.frontcapstone.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.frontcapstone.api.data.BookData
import com.example.frontcapstone.api.data.PostComment
import com.example.frontcapstone.components.buttons.ReviewDetailFrame
import com.example.frontcapstone.components.items.BookRecommendationCard
import com.example.frontcapstone.components.items.Comment
import com.example.frontcapstone.components.layout.TopMenuWithBack
import com.example.frontcapstone.components.textInput.CommentTextInput
import com.example.frontcapstone.viemodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun ReviewDetailPage(
    navigationBack: () -> Unit,
    commentText: String,
    onCommentTextChanged: (String) -> Unit,
    onRecommendBookClicked: (BookData) -> Unit,
    clearText: () -> Unit,
    mainViewModel: MainViewModel
) {
    val chosenReview by mainViewModel.chosenReview.collectAsState()
    val chosenReviewCommentList by mainViewModel.chosenReviewCommentList.collectAsState()
    val reviewRecommendBooks by mainViewModel.reviewRecommendBookList.collectAsState()

    val coroutineScope = rememberCoroutineScope()
    var showError by rememberSaveable { mutableStateOf(false) }

    val pagerState = rememberPagerState(pageCount = { 1 })


    LaunchedEffect(Unit) {
        mainViewModel.getComments(reviewID = chosenReview.id)
        mainViewModel.getReviewRecommend()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopMenuWithBack(title = "Review Detail", navigationBack = navigationBack) },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            item {
                ReviewDetailFrame(
                    reviewWithBook = chosenReview,
                )
            }
            items(chosenReviewCommentList) { comment ->
                Comment(
                    comment = comment
                )
            }
            item {
                CommentTextInput(
                    commentText = commentText,
                    onCommentTextChanged = onCommentTextChanged,
                    onSendClicked = {
                        if (commentText.isBlank()) {
                            showError = true // 빈 문자열일 경우 경고 표시
                        } else {
                            coroutineScope.launch {
                                mainViewModel.createComment(
                                    PostComment(
                                        reviewID = chosenReview.id,
                                        userID = mainViewModel.userState.value.id,
                                        comment = commentText
                                    )
                                )
                                clearText()
                            }
                        }
                    }
                )
                if (showError) {
                    Text(
                        text = "comment cannot be empty!",
                        color = Color.Red,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            item {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth()
                ) { page ->
                    BookRecommendationCard(
                        bookList = reviewRecommendBooks,
                        onRecommendBookClicked = onRecommendBookClicked
                    )
                }
            }
        }
    }
}