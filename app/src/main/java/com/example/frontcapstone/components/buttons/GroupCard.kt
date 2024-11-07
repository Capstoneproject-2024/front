package com.example.frontcapstone.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frontcapstone.ui.theme.PrimaryContainerColor
import com.example.frontcapstone.ui.theme.QuoteQuestionMintTextColor

@Preview
@Composable
fun GroupCard(
    mainColor: Color = QuoteQuestionMintTextColor,
    onEditClicked: () -> Unit,
    onCardClicked: () -> Unit,
    editPossible: Boolean = false
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(130.dp)
            .clickable { onCardClicked() },
        colors = CardDefaults.cardColors(
            containerColor = PrimaryContainerColor
        ),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
        ) {
            Box(
                modifier = Modifier
                    .width(24.dp)
                    .fillMaxHeight()
                    .background(
                        mainColor
                    )
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(270.dp),
            )
            {
                Text(
                    text = "Group name A", //Todo 글자수 제한 필요
                    modifier = Modifier
                        .padding(8.dp)
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f),
                    color = mainColor,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Group description Lorem ipsum dolor sit amet  dfd consectetur.",
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth()
                        .fillMaxHeight(0.9f),
                    color = Color.White,
                )
            }

            if (editPossible) {
                Button(
                    onClick = onEditClicked,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxHeight(0.25f)
                        .fillMaxWidth(0.8f)
                        .clip(shape = RoundedCornerShape(2.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = mainColor,
                        contentColor = PrimaryContainerColor
                    ),
                    contentPadding = PaddingValues(4.dp)
                ) {
                    Text(
                        text = "edit",
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                    )
                }
            }

        }
    }
}