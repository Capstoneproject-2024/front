package com.example.frontcapstone.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frontcapstone.ui.theme.PrimaryContainerColor

@Composable
fun GroupCard(mainColor: Color, onEditClicked: () -> Unit, onCardClicked: () -> Unit) {
    val height = 130.dp
    val width = 240.dp
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onCardClicked() },
        colors = CardDefaults.cardColors(
            containerColor = mainColor
        ),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp, height) // 원하는 크기 설정
                    .background(
                        mainColor
                    )
            )
            Column(modifier = Modifier.size(height = height, width = 270.dp)) {
                Text(
                    text = "Group name A",
                    modifier = Modifier.padding(8.dp),
                    color = mainColor,
                    fontSize = 24.sp,
                )
                Text(
                    text = "Group description",
                    modifier = Modifier.padding(8.dp),
                    color = Color.White,
                )
            }
            Button(
                onClick = onEditClicked,
                modifier = Modifier.padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = mainColor,
                    contentColor = PrimaryContainerColor
                ),

                ) {
                Text("edit")
            }
        }
    }
}