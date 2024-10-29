package com.example.frontcapstone.components.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.frontcapstone.ui.theme.TopAppBarTextColor
import com.example.frontcapstone.ui.theme.TopAppbarBackgroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteReviewTopMenu(
    modifier: Modifier = Modifier,
    navigationBack: () -> Unit,
    onClickPost: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = TopAppbarBackgroundColor,
            titleContentColor = TopAppBarTextColor,
        ),
        navigationIcon = {
            IconButton(onClick = navigationBack) {
                androidx.compose.material3.Icon(
                    tint = Color.White,
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            Text(
                text = "Post",
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onClickPost() },
                color = TopAppBarTextColor
            )
        }

    )
}