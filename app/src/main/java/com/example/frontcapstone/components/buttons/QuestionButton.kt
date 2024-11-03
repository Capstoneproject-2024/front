package com.example.frontcapstone.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frontcapstone.ui.theme.PrimaryContainerColor
import com.example.frontcapstone.ui.theme.QuoteQuestionMintTextColor

@Preview
@Composable
fun QuestionButton() {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(60.dp)
            .background(PrimaryContainerColor, shape = RoundedCornerShape(12.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 왼쪽 컬러 블록
        Box(
            modifier = Modifier
                .width(20.dp)
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
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(
                text = "Question?",
                color = QuoteQuestionMintTextColor,
                fontSize = 20.sp,
            )
            Text(
                text = "Lorem ipsum dolor sit amet consectetur.",
                color = Color.White,
                fontSize = 12.sp
            )
        }

        // 오른쪽 아이콘
        IconButton(
            modifier = Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(QuoteQuestionMintTextColor)
                .padding(4.dp),
            onClick = {},
        ) {
            androidx.compose.material3.Icon(
                imageVector = Icons.Outlined.Edit,
                contentDescription = "Edit",
            )
        }

        Spacer(modifier = Modifier.width(8.dp))


    }
}