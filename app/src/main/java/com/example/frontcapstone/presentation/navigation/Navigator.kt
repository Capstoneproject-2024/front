package com.example.frontcapstone.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.frontcapstone.presentation.screen.FindFriendPage
import com.example.frontcapstone.presentation.screen.GroupPage
import com.example.frontcapstone.presentation.screen.MainPage
import com.example.frontcapstone.presentation.screen.MyPage
import com.example.frontcapstone.presentation.screen.NoticePage
import com.example.frontcapstone.presentation.screen.ReviewPage
import com.example.frontcapstone.presentation.screen.SearchPage
import com.example.frontcapstone.presentation.screen.SettingPage

@Composable
fun Navigator() {
    val navController = rememberNavController()
    val bottomBaronClickedActions = listOf(
        { navController.navigate("GroupPage") },
        { navController.navigate("SearchPage") },
        { navController.navigate("MainPage") },
        { navController.navigate("NoticePage") },
        { navController.navigate("MyPage") }
    )
    val navigationBack: () -> Unit = { navController.navigateUp() }

    //search value 나중에 api 연결하기
    var searchText by rememberSaveable { mutableStateOf("") }

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "MainPage",
            modifier = Modifier.padding(innerPadding)
        ) {
            //BottomBar navigation
            composable(route = "GroupPage") {
                GroupPage(bottomBaronClickedActions = bottomBaronClickedActions)
            }
            composable(route = "SearchPage") {
                SearchPage(
                    bottomBaronClickedActions = bottomBaronClickedActions,
                    searchText = searchText,
                    onSearchValueChange = { searchText = it })
            }
            composable(route = "MainPage") {
                MainPage(
                    bottomBaronClickedActions = bottomBaronClickedActions,
                    onFloatingButtonCLicked = { navController.navigate("ReviewPage") })
            }
            composable(route = "NoticePage") {
                NoticePage(bottomBaronClickedActions = bottomBaronClickedActions)
            }
            composable(route = "MyPage") {
                MyPage(
                    bottomBaronClickedActions = bottomBaronClickedActions,
                    moveToFindFriendPage = { navController.navigate("FindFriendPage") },
                    moveToSettingPage = { navController.navigate("SettingPage") }
                )
            }


            //Review page 이동
            composable(route = "ReviewPage") {
                ReviewPage(
                    navigationBack = navigationBack,
                    onClickPost = { navController.navigate("MainPage") },
                )
            }
            //SettingPage이동
            composable(route = "SettingPage") {
                SettingPage(navigtionBack = navigationBack)
            }
            //Find Friend Page 이동
            composable(route = "FindFriendPage") {
                FindFriendPage(navigtionBack = navigationBack)
            }
        }
    }
}