package com.example.frontcapstone.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frontcapstone.api.RetrofitManager
import com.example.frontcapstone.components.layout.TopMenuWithBack
import com.example.frontcapstone.viemodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun SettingPage(
   navigationBack: () -> Unit,
   onLogoutClick:()->Unit,
   mainViewModel: MainViewModel

) {
    var showError by rememberSaveable { mutableStateOf(false) }
    var showNicknameDialog by rememberSaveable { mutableStateOf(false) }
    var nickname by rememberSaveable { mutableStateOf(mainViewModel.userState.value.nickname) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopMenuWithBack(title = "Settings", navigationBack = {navigationBack()}) }, //navigationBack
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "계정 정보",
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(
                text = "닉네임 변경",
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .clickable { showNicknameDialog = true}, //showNicknameDialog = true
                style = LocalTextStyle.current.copy(color = Color.Gray)
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 16.dp),
                thickness = 1.dp,
                color = Color.Gray
            )

            Text(
                text = "계정",
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(
                text = "로그아웃",
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .clickable {onLogoutClick() }, //onLogoutClick()
                style = LocalTextStyle.current.copy(color = Color.Gray)
            )
            Text(
                text = "계정 탈퇴",
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .clickable {}, // onDeleteAccountClick()
                style = LocalTextStyle.current.copy(color = Color.Red)
            )
        }

        // 닉네임 변경 팝업
        if (showNicknameDialog) {
            AlertDialog(
                onDismissRequest = { showNicknameDialog = false },
                title = {
                    Text(text = "닉네임 변경", fontSize = 20.sp, color = Color.Black)
                },
                text = {
                    Column {
                        Text(
                            text = "새로운 닉네임을 입력하세요.",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        OutlinedTextField(
                            value = nickname,//userSettingData.nickname,
                            onValueChange = { nickname = it}, //userViewModel.updateNickname(it)
                            label = { Text("닉네임") },
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = TextStyle(color = Color.Black)

                        )
                    }
                },
                confirmButton = {
                    TextButton(onClick = {
                        CoroutineScope(Dispatchers.Main).launch {
                            try {
                                RetrofitManager.instance.updateUser(
                                    id = mainViewModel.userState.value.id,
                                    nickname = nickname,
                                    onSuccess = {
                                        mainViewModel.updateUserState(nickname = nickname)
                                        nickname = mainViewModel.userState.value.nickname
                                        // 성공 시 처리
                                    },
                                    onFailure = {
                                        // 실패 시 처리
                                    }
                                )
                                // userViewModel.updateNickname(userSettingData.nickname)
                                // showNicknameDialog = false
                            } catch (e: Exception) {
                                // 에러 처리
                                //println("Error: ${e.message}")
                            }
                        }
//                        userViewModel.updateNickname(userSettingData.nickname)
                        showNicknameDialog = false
                    }) {
                        Text("저장")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showNicknameDialog = false }) {
                        Text("취소")
                    }
                }
            )
        }
    }
}