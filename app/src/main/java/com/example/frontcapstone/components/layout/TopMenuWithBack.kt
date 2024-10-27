package com.example.frontcapstone.components.layout

import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.frontcapstone.ui.theme.TopAppBarTextColor
import com.example.frontcapstone.ui.theme.TopAppbarBackgroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopMenuWithBack(title: String, modifier: Modifier = Modifier, navigationBack: () -> Unit) {
    TopAppBar(
        modifier = Modifier.fillMaxSize(),
        title = { Text("$title") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = TopAppbarBackgroundColor,
            titleContentColor = TopAppBarTextColor,
        ),
        navigationIcon = {
            IconButton(onClick = navigationBack) {
                androidx.compose.material3.Icon(
                    tint = Color.White,
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Some Description"
                )
            }
        },
    )
}