package com.example.frontcapstone.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PersonAdd
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frontcapstone.components.items.GroupUserSlot
import com.example.frontcapstone.components.layout.BottomThreeMenu
import com.example.frontcapstone.components.layout.TopMenuWithBack
import com.example.frontcapstone.ui.theme.TopAppbarBackgroundColor

@Composable
fun GroupSettingPage(
    navigationBack: () -> Unit,
    bottomBaronClickedActions: List<() -> Unit>,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopMenuWithBack(
                title = "Group Setting page",
                navigationBack = { navigationBack() })
        },
        bottomBar = { BottomThreeMenu(onClickedActions = bottomBaronClickedActions) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(16.dp)
                .padding(innerPadding)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(TopAppbarBackgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xff535466))
                            .padding(8.dp)
                            .clip(RoundedCornerShape(12.dp))
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Transparent),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "members",
                                color = Color.White,
                                modifier = Modifier.padding(8.dp),
                                fontSize = 20.sp
                            )
                            IconButton(
                                onClick = { },
                                modifier = Modifier
                                    .padding(8.dp)
                                    .size(33.dp)
                            ) {
                                androidx.compose.material3.Icon(
                                    modifier = Modifier.size(33.dp),
                                    tint = Color.White,
                                    imageVector = Icons.Outlined.PersonAdd,
                                    contentDescription = "Add Friend",
                                )
                            }
                        }
                    }
                    val temps: List<String> = List(40) { "$it" }
                    LazyColumn(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .height(400.dp)
                            .padding(4.dp)
                    ) {
                        items(temps) { temp ->
                            GroupUserSlot()
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonColors(
                    containerColor = Color(0xFFF8CFCF),
                    contentColor = Color.Black,
                    disabledContainerColor = Color(0xFFF8CFCF),
                    disabledContentColor = Color.Black
                )
            ) {
                Text(
                    text = "delete Group",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            }
        }

    }
}
