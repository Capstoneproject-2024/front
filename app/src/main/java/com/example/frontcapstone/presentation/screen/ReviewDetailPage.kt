package com.example.frontcapstone.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.frontcapstone.components.buttons.ReviewDetailFrame
import com.example.frontcapstone.components.items.Comment
import com.example.frontcapstone.components.layout.TopMenuWithBack
import com.example.frontcapstone.components.textInput.CommentTextInput

@Composable
fun ReviewDetailPage(
    navigationBack: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopMenuWithBack(title = "Review", navigationBack = navigationBack) },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            item {
                ReviewDetailFrame()
            }
            val temps: List<String> = List(3) { "$it" }
            items(temps) { temp ->
                Comment()
            }
            item {
                CommentTextInput()
            }
        }
    }
}