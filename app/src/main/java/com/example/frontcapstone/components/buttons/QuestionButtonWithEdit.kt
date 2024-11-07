package com.example.frontcapstone.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frontcapstone.ui.theme.PrimaryContainerColor
import com.example.frontcapstone.ui.theme.QuoteQuestionMintTextColor

@Composable
fun QuestionButtonWithEdit(
    modifier: Modifier = Modifier,
    onQuoteQuestionClicked: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(90.dp)
            .background(PrimaryContainerColor, shape = RoundedCornerShape(12.dp))
            .clickable { onQuoteQuestionClicked() },
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
                .fillMaxWidth(0.8f)
                .padding(vertical = 4.dp, horizontal = 8.dp)
                .fillMaxHeight(0.9f)
        ) {
            Text(
                text = "Question?",
                color = QuoteQuestionMintTextColor,
                fontSize = 24.sp,
            )
            Text(
                text = "Lorem ipsum dolor sit amet  dfd consectetur.",
                color = Color.White,
                fontSize = 16.sp
            )
        }

        // 오른쪽 아이콘
        IconButton(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(16.dp))
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