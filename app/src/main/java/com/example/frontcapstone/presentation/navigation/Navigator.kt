package com.example.frontcapstone.presentation.navigation

//import SignInButton
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.frontcapstone.AuthManager
import com.example.frontcapstone.presentation.screen.BookDetailPage
import com.example.frontcapstone.presentation.screen.FindFriendPage
import com.example.frontcapstone.presentation.screen.FriendRequestPage
import com.example.frontcapstone.presentation.screen.GroupArchivePage
import com.example.frontcapstone.presentation.screen.GroupMainPage
import com.example.frontcapstone.presentation.screen.GroupNameSettingPage
import com.example.frontcapstone.presentation.screen.GroupPage
import com.example.frontcapstone.presentation.screen.GroupQuotePage
import com.example.frontcapstone.presentation.screen.GroupSettingPage
import com.example.frontcapstone.presentation.screen.LoginPage
import com.example.frontcapstone.presentation.screen.MainPage
import com.example.frontcapstone.presentation.screen.MyPage
import com.example.frontcapstone.presentation.screen.NoticePage
import com.example.frontcapstone.presentation.screen.QuoteQuestionPage
import com.example.frontcapstone.presentation.screen.QuoteReviewPage
import com.example.frontcapstone.presentation.screen.RegisterPage
import com.example.frontcapstone.presentation.screen.ReviewDetailPage
import com.example.frontcapstone.presentation.screen.ReviewPage
import com.example.frontcapstone.presentation.screen.SearchPage
import com.example.frontcapstone.presentation.screen.SearchPageWithoutBottomBar
import com.example.frontcapstone.presentation.screen.SettingPage
import com.example.frontcapstone.viemodel.MainViewModel

fun navivationWithClear(navController: NavController, route: String) {
    navController.popBackStack()
    navController.navigate(route)
}

@Composable
fun Navigator(
    mainViewModel: MainViewModel,
    googleSignIn: () -> Unit,
    authManager: AuthManager
) {

    val userState by mainViewModel.userState.collectAsState()

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

    val navigateToReviewDetail: () -> Unit = { navController.navigate("ReviewDetailPage") }

    var searchText by rememberSaveable { mutableStateOf("") }

    var groupNameText by rememberSaveable { mutableStateOf("") }
    var groupDescriptionText by rememberSaveable { mutableStateOf("") }

    var quoteTextinReview by rememberSaveable { mutableStateOf("") }
    var quoteTextinQuote by rememberSaveable { mutableStateOf("") }
    var reviewText by rememberSaveable { mutableStateOf("") }
    var findFriendText by rememberSaveable { mutableStateOf("") }

    // 해당 페이지로 이동 시 초기화
    navController.addOnDestinationChangedListener { _, destination, _ ->
        when (destination.route) {
            "SearchPage" -> {
                searchText = ""
                mainViewModel.clearSearchedBookList()
            }

            "SearchPageWithoutBottomBar" -> {
                searchText = ""
                mainViewModel.clearSearchedBookList()
            }

            "ReviewPage" -> {
                quoteTextinReview = ""
                reviewText = ""
            }

            "QuoteReviewPage" -> quoteTextinQuote = ""
            "FindFriendPage" -> findFriendText = ""

            "GroupNameSettingPage" -> groupNameText = ""
        }
    }

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = if (userState.isValid()) "MainPage" else "LoginPage",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = "LoginPage") {
                LoginPage(
                    mainViewModel = mainViewModel,
                    authManager = authManager,

                    onClick = {
                        googleSignIn()
                    }
                )
            }
            composable(route = "RegieterPage"){
                RegisterPage(
                    tryAuthWithEmailAndPassword =  { email:String, password:String, onAuthFailure:()->Unit, onFailure:()->Unit ->
                        authManager.tryAuthWithEmailAndPassword(email,password,onAuthFailure,onFailure)
                    }
                )
            }

            //BottomBar navigation

            composable(route = "GroupPage") {
                GroupPage(
                    bottomBaronClickedActions = bottomBar5onClickedActions,
                    onCardClicked = { navController.navigate("GroupMainPage") },
                    onEditClicked = { navController.navigate("GroupSettingPage") },
                    onNewGroupClicked = { navController.navigate("GroupNameSettingPage") },
                    mainViewModel = mainViewModel
                )
            }
            composable(route = "SearchPage") {
                SearchPage(
                    bottomBaronClickedActions = bottomBar5onClickedActions,
                    searchText = searchText,
                    onSearchValueChange = { searchText = it },
                    mainViewModel = mainViewModel,
                    navigateToBookDetail = {
                        navController.navigate("BookDetailPage")
                    }
                )
            }

            composable(route = "SearchPageWithoutBottomBar") {
                SearchPageWithoutBottomBar(
                    searchText = searchText,
                    onSearchValueChange = { searchText = it },
                    mainViewModel = mainViewModel,
                    navigationBack = { navigationBack() }
                )
            }

            composable(route = "MainPage") {
                MainPage(
                    bottomBaronClickedActions = bottomBar5onClickedActions,
                    onFloatingButtonCLicked = { navController.navigate("ReviewPage") },
                    onReviewClicked = navigateToReviewDetail
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
                    moveToFriendRequestPage = { navController.navigate("FriendRequestPage") },
                    mainViewModel = mainViewModel
                )
            }


            //Review page 이동
            composable(route = "ReviewPage") {
                ReviewPage(
                    navigationBack = navigationBack,
//                    onClickPost = { navController.navigate("MainPage") },
                    quoteText = quoteTextinReview,
                    onQuoteTextChange = { quoteTextinReview = it },
                    reviewText = reviewText,
                    onReviewTextChange = { reviewText = it },
                    onSelectButtonClicked = { navController.navigate("SearchPageWithoutBottomBar") },
                    onBookClicked = { navController.navigate("SearchPageWithoutBottomBar") },
                    mainViewModel = mainViewModel
                )
            }

            //SettingPage이동
            composable(route = "SettingPage") {
                SettingPage(navigationBack = navigationBack)
            }
            //Find Friend Page 이동
            composable(route = "FindFriendPage") {
                FindFriendPage(
                    navigationBack = navigationBack,
                    findFriendText = findFriendText,
                    onFindFriendTextChanged = { findFriendText = it },
                    mainViewModel = mainViewModel
                )
            }

            composable(route = "GroupSettingPage") {
                GroupSettingPage(
                    navigationBack = navigationBack,
                    bottomBaronClickedActions = bottomBar3onClickedActions,
                )
            }

            composable(route = "GroupMainPage") {
                GroupMainPage(
                    navigationBack = navigationBack,
                    bottomBaronClickedActions = bottomBar3onClickedActions,
                    onReviewClicked = navigateToReviewDetail
                )
            }
            composable(route = "GroupQuotePage") {
                GroupQuotePage(
                    navigationBack = navigationBack,
                    bottomBaronClickedActions = bottomBar3onClickedActions,
                    onQuoteQuestionClicked = { navController.navigate("QuoteQuestionPage") },
                    onEditButtonClicked = { navController.navigate("QuoteReviewPage") },
                    onReviewClicked = navigateToReviewDetail
                )
            }
            composable(route = "GroupArchivePage") {
                GroupArchivePage(
                    navigationBack = navigationBack,
                    bottomBaronClickedActions = bottomBar3onClickedActions,
                    onReviewClicked = navigateToReviewDetail
                )
            }

            composable(route = "FriendRequestPage") {
                FriendRequestPage(
                    navigationBack = navigationBack,
                    mainViewModel = mainViewModel
                )
            }

            composable(route = "QuoteQuestionPage") {
                QuoteQuestionPage(
                    navigationBack = navigationBack,
                    bottomBaronClickedActions = bottomBar3onClickedActions,
                    onEditButtonClicked = { navController.navigate("QuoteReviewPage") },
                    onReviewClicked = navigateToReviewDetail
                )
            }

            composable(route = "QuoteReviewPage") {
                QuoteReviewPage(
                    navigationBack = navigationBack,
                    quoteText = quoteTextinQuote,
                    onQuoteTextChange = { quoteTextinQuote = it },
                    onSelectButtonClicked = { navController.navigate("SearchPageWithoutBottomBar") },
                    onBookClicked = { navController.navigate("SearchPageWithoutBottomBar") },

                    )
            }

            composable(route = "ReviewDetailPage") {
                ReviewDetailPage(
                    navigationBack = navigationBack,
                )
            }

            composable(route = "BookDetailPage") {
                BookDetailPage(
                    navigationBack = navigationBack,
                    bottomBaronClickedActions = bottomBar5onClickedActions,
                    onFloatingButtonCLicked = { navController.navigate("ReviewPage") },
                    mainViewModel = mainViewModel,
                )
            }

            composable(route = "GroupNameSettingPage") {
                GroupNameSettingPage(
                    navigationBack = navigationBack,
                    groupNameText = groupNameText,
                    groupDescriptionText = groupDescriptionText,
                    onGroupNameTextChanged = { groupNameText = it },
                    onGroupDescriptionTextChanged = { groupDescriptionText = it },
                    onConfirmButtonClicked = {
                        mainViewModel.createAndUpdateGroupList(
                            groupName = groupNameText,
                            groupDescription = groupDescriptionText
                        )
                        navigationBack()
                    },
                )
            }

        }
    }
}
