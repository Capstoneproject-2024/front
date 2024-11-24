package com.example.frontcapstone.components.textInput

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frontcapstone.ui.theme.PrimaryPurpleColor
import com.example.frontcapstone.ui.theme.UnFocusedReviewContainerColor

@Composable
fun ReviewTextInput(
    reviewText: String,
    onReviewTextChange: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(
                shape = RoundedCornerShape(12.dp)
            )
            .height(180.dp) // 높이 설정
    ) {
        TextField(
            value = reviewText,
            onValueChange = onReviewTextChange,
            placeholder = {
                Text(
                    text = "Write Review...",
                    color = Color.Black,
                    fontSize = 16.sp
                )
            },
            textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = PrimaryPurpleColor,
                unfocusedContainerColor = UnFocusedReviewContainerColor
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
        )
    }
}
