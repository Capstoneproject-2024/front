package com.example.frontcapstone.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frontcapstone.ui.theme.PrimaryContainerColor
import com.example.frontcapstone.ui.theme.QuoteQuestionMintTextColor

@Preview
@Composable
fun QuestionButtonWithoutEdit(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(60.dp)
            .background(PrimaryContainerColor, shape = RoundedCornerShape(12.dp))
            .clickable { },
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 왼쪽 컬러 블록
        Box(
            modifier = Modifier
                .width(30.dp)
                .fillMaxHeight()
                .background(
                    QuoteQuestionMintTextColor,
                    shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)
                )
        )

        Spacer(modifier = Modifier.width(8.dp))

        // 텍스트 부분
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(vertical = 4.dp, horizontal = 8.dp)
                .fillMaxHeight(0.9f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Question?",
                color = QuoteQuestionMintTextColor,
                fontSize = 24.sp,
            )
        }
        Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowForwardIos,
            contentDescription = "Next",
            tint = QuoteQuestionMintTextColor
        )
    }
}