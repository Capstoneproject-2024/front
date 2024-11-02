package com.example.frontcapstone.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.frontcapstone.components.layout.TopMenuWithBack

@Composable
fun GroupSettingPage(navigtionBack: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopMenuWithBack(title = "Group Settiong page", navigationBack = navigtionBack) },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        )
        {
            Text("Group Settiong Screen")
        }
    }
}