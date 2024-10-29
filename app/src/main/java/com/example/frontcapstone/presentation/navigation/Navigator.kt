package com.example.frontcapstone.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.frontcapstone.presentation.screen.GroupPage
import com.example.frontcapstone.presentation.screen.MainPage
import com.example.frontcapstone.presentation.screen.MyPage
import com.example.frontcapstone.presentation.screen.NoticePage
import com.example.frontcapstone.presentation.screen.SearchPage

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
                SearchPage(bottomBaronClickedActions = bottomBaronClickedActions)
            }
            composable(route = "MainPage") {
                MainPage(bottomBaronClickedActions = bottomBaronClickedActions)
            }
            composable(route = "NoticePage") {
                NoticePage(bottomBaronClickedActions = bottomBaronClickedActions)
            }
            composable(route = "MyPage") {
                MyPage(bottomBaronClickedActions = bottomBaronClickedActions)
            }


            //이 밑으로 상단바 혹은 어쩌구저쩌구 가독성 엔터로챙기자
        }
    }
}