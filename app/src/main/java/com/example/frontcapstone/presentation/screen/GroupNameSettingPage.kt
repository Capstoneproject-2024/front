package com.example.frontcapstone.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frontcapstone.components.layout.TopMenuWithBack
import com.example.frontcapstone.ui.theme.PrimaryContainerColor
import com.example.frontcapstone.ui.theme.PrimaryPurpleColor

@Composable
fun GroupNameSettingPage(
    navigationBack: () -> Unit,
    groupNameText: String,
    groupDescriptionText: String,
    onGroupDescriptionTextChanged: (String) -> Unit,
    onGroupNameTextChanged: (String) -> Unit,
    onConfirmButtonClicked: () -> Unit,
) {
    // 경고 메시지 상태 관리
    var showError by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopMenuWithBack(
                title = "Group Name Setting",
                navigationBack = navigationBack
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = groupNameText,
                onValueChange = {
                    onGroupNameTextChanged(it)
                    showError = it.isBlank() // 빈 문자열일 경우 에러 표시
                },
                placeholder = {
                    Text(
                        text = "Write Group Name",
                        color = Color.LightGray,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = PrimaryContainerColor,
                    focusedContainerColor = PrimaryContainerColor,
                    focusedIndicatorColor = PrimaryContainerColor,
                    unfocusedIndicatorColor = PrimaryContainerColor,
                    cursorColor = Color.White
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 16.sp,
                    color = Color.White,
                    textAlign = TextAlign.Start
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = groupDescriptionText,
                onValueChange = {
                    onGroupDescriptionTextChanged(it)
                    showError = it.isBlank()
                },
                placeholder = {
                    Text(
                        text = "Write Group Description",
                        color = Color.LightGray,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = PrimaryContainerColor,
                    focusedContainerColor = PrimaryContainerColor,
                    focusedIndicatorColor = PrimaryContainerColor,
                    unfocusedIndicatorColor = PrimaryContainerColor,
                    cursorColor = Color.White
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Start
                )
            )

            if (showError) {
                Text(
                    text = "Group name and description cannot be empty!",
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (groupNameText.isBlank() || groupDescriptionText.isBlank()) {
                        showError = true // 빈 문자열일 경우 경고 표시
                    } else {
                        onConfirmButtonClicked() // 정상적인 경우에만 동작
                    }
                },
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryPurpleColor,
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .height(42.dp)
                    .width(200.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "Confirm",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
