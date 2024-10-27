package com.example.frontcapstone.components.layout


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.frontcapstone.ui.theme.TopAppBarTextColor
import com.example.frontcapstone.ui.theme.TopAppbarBackgroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopMenuWithoutBack(title: String, modifier: Modifier = Modifier, navigationBack: () -> Unit) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Text(
                text = "$title",
                textAlign = TextAlign.Center,
                color = TopAppBarTextColor,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = TopAppbarBackgroundColor,
        ),
    )
}