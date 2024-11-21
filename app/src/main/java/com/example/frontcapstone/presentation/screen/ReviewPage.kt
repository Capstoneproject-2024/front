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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.frontcapstone.components.items.BookDetail
import com.example.frontcapstone.components.items.RatingBar
import com.example.frontcapstone.components.layout.WriteReviewTopMenu
import com.example.frontcapstone.components.textInput.QuoteTextInput
import com.example.frontcapstone.components.textInput.ReviewTextInput
import com.example.frontcapstone.viemodel.MainViewModel

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
    var rating by rememberSaveable { mutableFloatStateOf(2.5f) } // 초기 별점
    var selectedOption by rememberSaveable { mutableStateOf("Public") } // 기본 선택값: Public


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
                    onClicked = onBookClicked
                )
            } else {
                NewSelectBookButton(
                    onClicked = onSelectButtonClicked
                )
            }
            QuoteTextInput(quoteText = quoteText, onQuoteTextChange = onQuoteTextChange)
            ReviewTextInput(reviewText = reviewText, onReviewTextChange = onReviewTextChange)


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
                    isSelected = selectedOption == "Public",
                    onClick = { selectedOption = "Public" }
                )

                // Private Radio Button
                RadioButtonWithLabel(
                    label = "Private",
                    isSelected = selectedOption == "Private",
                    onClick = { selectedOption = "Private" }
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