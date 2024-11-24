package com.example.frontcapstone.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.frontcapstone.ui.theme.PrimaryContainerColor
import com.example.frontcapstone.ui.theme.UserTextPrupleColor

@Composable
fun QuoteQuestionCard(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(color = PrimaryContainerColor, shape = RoundedCornerShape(12.dp)) // 배경색과 둥근 모서리 설정
            .padding(horizontal = 16.dp, vertical = 12.dp), // 내부 패딩
        verticalAlignment = Alignment.CenterVertically
    ) {
        // "Q." 아이콘
        Text(
            text = "Q.",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = UserTextPrupleColor, // 아이콘 색상 설정
            modifier = Modifier.padding(end = 8.dp)
        )

        // 질문 텍스트
        Text(
            text = "Lorem ipsum dolor sit amet consectetur?",
            fontSize = 16.sp,
            color = Color.White,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
