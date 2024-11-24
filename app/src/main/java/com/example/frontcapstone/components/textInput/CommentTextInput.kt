package com.example.frontcapstone.components.textInput

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frontcapstone.ui.theme.PrimaryBackgroundColor
import com.example.frontcapstone.ui.theme.PrimaryContainerColor
import com.example.frontcapstone.ui.theme.PrimaryPurpleColor

@Composable
fun CommentTextInput(
    commentText: String,
    onCommentTextChanged: (String) -> Unit,
    onSendClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .background(PrimaryContainerColor)
            .height(50.dp)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        BasicTextField(
            value = commentText,
            onValueChange = onCommentTextChanged,
            textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight()
                .weight(1f)
                .clip(shape = RoundedCornerShape(6.dp))
                .background(PrimaryBackgroundColor)
                .padding(8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onSendClicked() }
            ),
            cursorBrush = SolidColor(Color.White)
        )



        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .height(34.dp)
                .fillMaxWidth(0.1f)
                .clip(RoundedCornerShape(9.dp))
                .background(PrimaryPurpleColor)
                .clickable { onSendClicked() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowUpward,
                contentDescription = "Send Comment",
                tint = PrimaryBackgroundColor,
                modifier = Modifier
                    .size(16.dp)
            )
        }

    }
}
