package com.example.frontcapstone.presentation.screen

import NewSelectBookButton
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frontcapstone.api.data.PostReview
import com.example.frontcapstone.components.items.BookDetail
import com.example.frontcapstone.components.items.RatingBar
import com.example.frontcapstone.components.layout.WriteReviewTopMenu
import com.example.frontcapstone.components.textInput.QuoteTextInput
import com.example.frontcapstone.components.textInput.ReviewTextInput
import com.example.frontcapstone.viemodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun ReviewPage(
    navigationBack: () -> Unit,
//    onClickPost: () -> Unit,
    onQuoteTextChange: (String) -> Unit,
    quoteText: String,
    reviewText: String,
    onReviewTextChange: (String) -> Unit,
    onSelectButtonClicked: () -> Unit,
    onBookClicked: () -> Unit,
    mainViewModel: MainViewModel
) {
    val chosenBook by mainViewModel.chosenBook.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val user by mainViewModel.userState.collectAsState()

    var rating by rememberSaveable { mutableFloatStateOf(2.5f) } // 초기 별점
    var selectedOption by rememberSaveable { mutableStateOf("public") } // 기본 선택값: Public
    var showError by rememberSaveable { mutableStateOf(false) }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            WriteReviewTopMenu(
                navigationBack = navigationBack,
                onClickPost = {
                    if (reviewText.isBlank() || quoteText.isBlank()) {
                        showError = true // 빈 문자열일 경우 경고 표시
                    } else {
                        coroutineScope.launch {
                            mainViewModel.createReview(
                                postReview = PostReview(
                                    userID = user.id,
                                    bookID = chosenBook.id,
                                    rating = rating,
                                    review = reviewText,
                                    quote = quoteText
                                ),
                                visibilityLevel = selectedOption
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
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
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
            ReviewTextInput(reviewText = reviewText, onReviewTextChange = {
                onReviewTextChange(it)
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

            // RatingBar
            RatingBar(
                rating = rating,
                onRatingChanged = { newRating ->
                    rating = newRating
                },
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                // Public Radio Button
                RadioButtonWithLabel(
                    label = "Public",
                    isSelected = selectedOption == "public",
                    onClick = { selectedOption = "public" }
                )

                // Private Radio Button
                RadioButtonWithLabel(
                    label = "Private",
                    isSelected = selectedOption == "private",
                    onClick = { selectedOption = "private" }
                )
            }

        }
    }
}

@Composable
fun RadioButtonWithLabel(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onClick() } // 전체 Row 클릭 가능
    ) {
        RadioButton(
            selected = isSelected,
            onClick = { onClick() }, // 선택 시 상태 변경
            colors = RadioButtonColors(
                selectedColor = Color.White,
                unselectedColor = Color.White,
                disabledSelectedColor = Color.White,
                disabledUnselectedColor = Color.White
            )
        )
        Text(
            text = label,
            modifier = Modifier.padding(start = 8.dp),
            color = Color.White
        )
    }
}