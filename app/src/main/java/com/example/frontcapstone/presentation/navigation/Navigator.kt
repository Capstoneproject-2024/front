package com.example.frontcapstone.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.frontcapstone.presentation.screen.FindFriendPage
import com.example.frontcapstone.presentation.screen.FriendRequestPage
import com.example.frontcapstone.presentation.screen.GroupArchivePage
import com.example.frontcapstone.presentation.screen.GroupMainPage
import com.example.frontcapstone.presentation.screen.GroupPage
import com.example.frontcapstone.presentation.screen.GroupQuotePage
import com.example.frontcapstone.presentation.screen.GroupSettingPage
import com.example.frontcapstone.presentation.screen.MainPage
import com.example.frontcapstone.presentation.screen.MyPage
import com.example.frontcapstone.presentation.screen.NoticePage
import com.example.frontcapstone.presentation.screen.QuoteQuestionPage
import com.example.frontcapstone.presentation.screen.ReviewPage
import com.example.frontcapstone.presentation.screen.SearchPage
import com.example.frontcapstone.presentation.screen.SettingPage

fun navivationWithClear(navController: NavController, route: String) {
    navController.popBackStack()
    navController.navigate(route)
}

@Composable
fun Navigator() {
    val navController = rememberNavController()
    val bottomBar5onClickedActions = listOf(
        { navivationWithClear(navController = navController, route = "GroupPage") },
        { navivationWithClear(navController = navController, route = "SearchPage") },
        { navivationWithClear(navController = navController, route = "MainPage") },
        { navivationWithClear(navController = navController, route = "NoticePage") },
        { navivationWithClear(navController = navController, route = "MyPage") }
    )
    val bottomBar3onClickedActions = listOf(
        { navivationWithClear(navController = navController, route = "GroupQuotePage") },
        { navivationWithClear(navController = navController, route = "GroupMainPage") },
        { navivationWithClear(navController = navController, route = "GroupArchivePage") },
    )
    val navigationBack: () -> Unit = { navController.navigateUp() }

    //search value 나중에 api 연결하기
    var searchText by rememberSaveable { mutableStateOf("") }
    var quoteText by rememberSaveable { mutableStateOf("") }
    var reviewText by rememberSaveable { mutableStateOf("") }
    var findFriendText by rememberSaveable { mutableStateOf("") }

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "MainPage",
            modifier = Modifier.padding(innerPadding)
        ) {
            //BottomBar navigation
            composable(route = "GroupPage") {
                GroupPage(bottomBaronClickedActions = bottomBar5onClickedActions,
                    onCardClicked = { navController.navigate("GroupMainPage") },
                    onEditClicked = { navController.navigate("GroupSettingPage") })
            }
            composable(route = "SearchPage") {
                SearchPage(
                    bottomBaronClickedActions = bottomBar5onClickedActions,
                    searchText = searchText,
                    onSearchValueChange = { searchText = it })
            }
            composable(route = "MainPage") {
                MainPage(
                    bottomBaronClickedActions = bottomBar5onClickedActions,
                    onFloatingButtonCLicked = { navController.navigate("ReviewPage") },
                )
            }
            composable(route = "NoticePage") {
                NoticePage(bottomBaronClickedActions = bottomBar5onClickedActions)
            }
            composable(route = "MyPage") {
                MyPage(
                    bottomBaronClickedActions = bottomBar5onClickedActions,
                    moveToFindFriendPage = { navController.navigate("FindFriendPage") },
                    moveToSettingPage = { navController.navigate("SettingPage") },
                    moveToFriendRequestPage = { navController.navigate("FriendRequestPage") }
                )
            }


            //Review page 이동
            composable(route = "ReviewPage") {
                ReviewPage(
                    navigationBack = navigationBack,
//                    onClickPost = { navController.navigate("MainPage") },
                    quoteText = quoteText,
                    onQuoteTextChange = { quoteText = it },
                    reviewText = reviewText,
                    onReviewTextChange = { reviewText = it }
                )
            }
            //SettingPage이동
            composable(route = "SettingPage") {
                SettingPage(navigtionBack = navigationBack)
            }
            //Find Friend Page 이동
            composable(route = "FindFriendPage") {
                FindFriendPage(
                    navigtionBack = navigationBack,
                    findFriendText = findFriendText,
                    onFindFriendTextChanged = { findFriendText = it }
                )
            }

            composable(route = "GroupSettingPage") {
                GroupSettingPage(navigtionBack = navigationBack)
            }

            composable(route = "GroupMainPage") {
                GroupMainPage(
                    navigtionBack = navigationBack,
                    bottomBaronClickedActions = bottomBar3onClickedActions
                )
            }
            composable(route = "GroupQuotePage") {
                GroupQuotePage(
                    navigationBack = navigationBack,
                    bottomBaronClickedActions = bottomBar3onClickedActions,
                    onQuoteQuestionClicked = { navController.navigate("QuoteQuestionPage") }
                )
            }
            composable(route = "GroupArchivePage") {
                GroupArchivePage(
                    navigtionBack = navigationBack,
                    bottomBaronClickedActions = bottomBar3onClickedActions
                )
            }

            composable(route = "FriendRequestPage") {
                FriendRequestPage(
                    navigtionBack = navigationBack,
                )
            }

            composable(route = "QuoteQuestionPage") {
                QuoteQuestionPage(
                    navigationBack = navigationBack,
                    bottomBaronClickedActions = bottomBar3onClickedActions
                )
            }

        }
    }
}
// // 추후 nav 분류할거면 사용
//fun NavGraphBuilder.loginGraph(navController: NavController) {
//    navigation(startDestination = "username", route = "login") {
//        composable("username") { ... }
//        composable("password") { ... }
//        composable("registration") { ... }
//    }
//}