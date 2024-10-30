package com.example.frontcapstone.components.textInput

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
fun SearchTextInput(
    modifier: Modifier = Modifier,
    searchText: String,
    onSearchValueChange: (String) -> Unit,
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = TopAppbarBackgroundColor,
            titleContentColor = TopAppBarTextColor,
        ),
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search")
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = searchText,
                    onValueChange = onSearchValueChange,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = TopAppBarTextColor,
                        unfocusedTextColor = TopAppBarTextColor,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                    )
                )
            }
        },
    )
}