package com.example.frontcapstone.viemodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontcapstone.api.RetrofitManager
import com.example.frontcapstone.api.UserBookMap
import com.example.frontcapstone.api.data.BookData
import com.example.frontcapstone.api.data.BookDataWithoutDesc
import com.example.frontcapstone.api.data.Comment
import com.example.frontcapstone.api.data.FollowerData
import com.example.frontcapstone.api.data.FollowerRequestData
import com.example.frontcapstone.api.data.GetQuoteAnswer
import com.example.frontcapstone.api.data.GetQuoteQuestion
import com.example.frontcapstone.api.data.GroupData
import com.example.frontcapstone.api.data.GroupMemberData
import com.example.frontcapstone.api.data.PostComment
import com.example.frontcapstone.api.data.PostQuoteAnswer
import com.example.frontcapstone.api.data.PostReview
import com.example.frontcapstone.api.data.ReviewWithBook
import com.example.frontcapstone.api.data.UserData
import com.example.frontcapstone.api.data.UserUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {

    //로그인 관련
    private val _userState = MutableStateFlow(UserUIState())
    val userState: StateFlow<UserUIState> = _userState.asStateFlow()

    //search 관련
    private val _searchedBooks =
        MutableStateFlow<List<BookDataWithoutDesc>>(emptyList())//MutableStateFlow(mutableListOf<BookData>())
    val searchedBooks: StateFlow<List<BookDataWithoutDesc>> = _searchedBooks.asStateFlow()

    private val _chosenBook = MutableStateFlow(BookData())
    val chosenBook: StateFlow<BookData> = _chosenBook.asStateFlow()

    //group관련
    private val _groupList = MutableStateFlow<List<GroupData>>(emptyList())
    val groupList: StateFlow<List<GroupData>> = _groupList.asStateFlow()

    private val _chosenGroup = MutableStateFlow(GroupData())
    val chosenGroup: StateFlow<GroupData> = _chosenGroup.asStateFlow()

    private val _memberList = MutableStateFlow<List<UserData>>(emptyList())
    val memberList: StateFlow<List<UserData>> = _memberList.asStateFlow()

    private val _searchedNonMemberList = MutableStateFlow<List<UserData>>(emptyList())
    val searchedNonMemberList: StateFlow<List<UserData>> = _searchedNonMemberList.asStateFlow()


    //friend  관련
    private val _requestSenderList = MutableStateFlow<List<UserData>>(emptyList())
    val requestSenderList: StateFlow<List<UserData>> = _requestSenderList.asStateFlow()

    private val _searchedUserList = MutableStateFlow<List<UserData>>(emptyList())
    val searchedUserList: StateFlow<List<UserData>> = _searchedUserList.asStateFlow()

    private val _friendsList = MutableStateFlow<List<UserData>>(emptyList())
    val friendsList: StateFlow<List<UserData>> = _friendsList.asStateFlow()
    private val _friendCandidateList = MutableStateFlow<List<UserData>>(emptyList())
    val friendCandidateList: StateFlow<List<UserData>> = _friendCandidateList.asStateFlow()


    //review 관련
    private val _mainTimelineReviewList = MutableStateFlow<List<ReviewWithBook>>(emptyList())
    val mainTimelineReviewList: StateFlow<List<ReviewWithBook>> =
        _mainTimelineReviewList.asStateFlow()

    private val _myReviewList = MutableStateFlow<List<ReviewWithBook>>(emptyList())
    val myReviewList: StateFlow<List<ReviewWithBook>> =
        _myReviewList.asStateFlow()

    private val _chosenReview = MutableStateFlow(ReviewWithBook())
    val chosenReview: StateFlow<ReviewWithBook> = _chosenReview.asStateFlow()

    private val _groupTimelineReviewList = MutableStateFlow<List<ReviewWithBook>>(emptyList())
    val groupTimelineReviewList: StateFlow<List<ReviewWithBook>> =
        _groupTimelineReviewList.asStateFlow()


    //comment관련
    private val _chosenReviewCommentList = MutableStateFlow<List<Comment>>(emptyList())
    val chosenReviewCommentList: StateFlow<List<Comment>> = _chosenReviewCommentList.asStateFlow()


    //quote 관련
    private val _presentQuoteQuestion = MutableStateFlow(GetQuoteQuestion())
    val presentQuoteQuestion: StateFlow<GetQuoteQuestion> =
        _presentQuoteQuestion.asStateFlow()

    private val _pastQuoteQuestion = MutableStateFlow(GetQuoteQuestion())
    val pastQuoteQuestion: StateFlow<GetQuoteQuestion> = _pastQuoteQuestion.asStateFlow()

    private val _presentQuoteAnswers = MutableStateFlow<List<GetQuoteAnswer>>(emptyList())
    val presentQuoteAnswers: StateFlow<List<GetQuoteAnswer>> = _presentQuoteAnswers.asStateFlow()

    private val _pastQuoteAnswers = MutableStateFlow<List<GetQuoteAnswer>>(emptyList())
    val pastQuoteAnswers: StateFlow<List<GetQuoteAnswer>> = _pastQuoteAnswers.asStateFlow()


    //recommend관련
    private val _questionRecommendBookList = MutableStateFlow<UserBookMap>(emptyMap())
    val questionRecommendBookList: StateFlow<UserBookMap> =
        _questionRecommendBookList.asStateFlow()

    private val _userNicknameList = MutableStateFlow<List<String>>(emptyList())
    val userNicknameList: StateFlow<List<String>> = _userNicknameList.asStateFlow()

    private val _reviewRecommendBookList = MutableStateFlow<List<BookData>>(emptyList())
    val reviewRecommendBookList: StateFlow<List<BookData>> =
        _reviewRecommendBookList.asStateFlow()


    fun updateUserState(id: Int, nickname: String) {
        _userState.update {
            it.copy(
                id = id,
                nickname = nickname
            )
        }
    }

    fun updateUserState(nickname: String) {
        _userState.update {
            it.copy(
                nickname = nickname
            )
        }
    }

    suspend fun updateUserState(nickname: String?, email: String?, uid: String) {
        if (nickname == null || email == null) return
        RetrofitManager.instance.createUser(
            nickname = nickname,
            email = email,
            uid = uid,
            onSuccess = { user: UserUIState ->
                _userState.update {
                    it.copy(
                        nickname = user.nickname,
                        id = user.id,
                        uid = user.uid,
                        email = user.email
                    )
                }
            },
            onFailure = {

            }

        )

        val getUserResponse = RetrofitManager.instance.getUserByEmail(
            email = email,
            onSuccess = { user: UserUIState ->
                _userState.update {
                    it.copy(
                        nickname = user.nickname,
                        id = user.id,
                        uid = user.uid,
                        email = user.email
                    )
                }
            },
            onFailure = {

            }
        )
        //create여부 확인
        // create문 호출
        //state 업데이트
    }

    // 책 검색 관련
    suspend fun searchedBookList(bookName: String) {
        RetrofitManager.instance.getBookByName(
            bookName = bookName,
            onSuccess = { bookList: List<BookDataWithoutDesc> ->
                Log.d("bookAPI - searchedbookAPI", bookList.toString())
                _searchedBooks.update { bookList }
                Log.d("bookAPI - searchedBOokupdate", _searchedBooks.toString())
            },
            onFailure = {

            }
        )
    }

    suspend fun searchedBook(id: Int) {
        RetrofitManager.instance.getBookByID(
            id = id,
            onSuccess = { book: BookData ->
                Log.d("bookAPI - chosenbook api", book.toString())
                _chosenBook.update { book }
                Log.d("bookAPI - chosenbook", _chosenBook.toString())

            },
            onFailure = {}
        )
    }

//    suspend fun getBookByID(id: Int): BookData {
//        val returnBook = CompletableDeferred<BookData>()
//        RetrofitManager.instance.getBookByID(id = id,
//            onSuccess = { book: BookData ->
//                returnBook.complete(book)
//            },
//            onFailure = {
//                returnBook.completeExceptionally(RuntimeException("Failed to fetch book data"))
//            }
//        )
//        return returnBook.await()
//    }

    fun clearSearchThings() {
        _searchedBooks.update { emptyList() }
        _chosenBook.update { BookData() }
    }

    fun clearSearchedBookList() {
        _searchedBooks.update { emptyList() }
    }

    fun clearChosenBook() {
        _chosenBook.update { BookData() }
    }

    fun updateChosenBook(bookData: BookData) {
        _chosenBook.update {
            bookData
        }
    }

    suspend fun createGroup(groupName: String, groupDescription: String) {
        RetrofitManager.instance.createGroup(
            adminID = userState.value.id,
            groupName = groupName,
            groupDescription = groupDescription,
            onSuccess = {},
            onFailure = {}
        )
    }

    suspend fun getUserGroups() {
        RetrofitManager.instance.getUserGroups(
            userID = userState.value.id,
            onSuccess = { groups: List<GroupData> ->
                _groupList.update { groups }
            },
            onFailure = {}
        )
    }

    fun createAndUpdateGroupList(groupName: String, groupDescription: String) {
        viewModelScope.launch {
            createGroup(groupName = groupName, groupDescription = groupDescription)
            getUserGroups()
        }
    }

    fun updateChosenGroup(groupData: GroupData) {
        _chosenGroup.update {
            groupData
        }
    }

    suspend fun deleteGroup(groupID: Int) {
        RetrofitManager.instance.deleteGroup(
            groupID = groupID,
            onSuccess = { },
            onFailure = {}
        )
    }

    suspend fun createMember(groupMemberData: GroupMemberData) {
        RetrofitManager.instance.createMember(
            groupMemberData = groupMemberData,
            onSuccess = { },
            onFailure = {}
        )
    }

    suspend fun getMembers(groupID: Int) {
        RetrofitManager.instance.getMembers(
            groupID = groupID,
            onSuccess = { members: List<UserData> ->
                _memberList.update { members }
            },
            onFailure = {}
        )
    }

    suspend fun deleteMember(deleteMemberID: Int, groupID: Int) {
        RetrofitManager.instance.deleteMember(
            deleteMemberID = deleteMemberID,
            onSuccess = {
                viewModelScope.launch {
                    getMembers(groupID)
                }
            },
            onFailure = {}
        )
    }

    suspend fun getSearchedNonMemberFriends(
        email: String,
    ) {
        RetrofitManager.instance.getSearchedNonMemberFriends(
            groupID = chosenGroup.value.groupID,
            userID = userState.value.id,
            email = email,
            onSuccess = { nonMembers: List<UserData> ->
                _searchedNonMemberList.update { nonMembers }
            },
            onFailure = {}
        )
    }


    suspend fun createFollowerRequest(receiverID: Int) {
        RetrofitManager.instance.createFollowerRequest(
            followerRequest = FollowerRequestData(
                senderID = userState.value.id,
                receiverID = receiverID
            ),
            onSuccess = {},
            onFailure = {}
        )
    }

    suspend fun getRequestSender() {
        RetrofitManager.instance.getRequestSender(
            receiverID = userState.value.id,
            onSuccess = { requestSenders: List<UserData> ->
                _requestSenderList.update { requestSenders }
            },
            onFailure = {}
        )
    }

    suspend fun createFriendAndAutoDelete(requestSenderID: Int) {
        RetrofitManager.instance.createFriendAndAutoDelete(
            follower = FollowerData(
                followerID = userState.value.id,
                followeeID = requestSenderID
            ),
            onSuccess = {
                viewModelScope.launch {
                    getRequestSender()
                }
            },
            onFailure = {}
        )
    }

    suspend fun deleteFriendRequest(requestSenderID: Int) {
        RetrofitManager.instance.deleteFriendRequest(
            senderID = requestSenderID,
            receiverID = userState.value.id,
            onSuccess = {
                viewModelScope.launch {
                    getRequestSender()
                }
            },
            onFailure = {}
        )
    }

    suspend fun getUsersByEmail(userInputEmail: String) {
        RetrofitManager.instance.getUsersByEmail(
            email = userInputEmail,
            onSuccess = { searchedUsers: List<UserData> ->
                _searchedUserList.update {
                    searchedUsers
                }
            },
            onFailure = {}
        )
    }

    suspend fun getFriends() {
        RetrofitManager.instance.getFriends(
            userID = userState.value.id,
            onSuccess = { friends: List<UserData> ->
                _friendsList.update { friends }
            },
            onFailure = {}
        )
    }

    suspend fun getBothRequest() {
        RetrofitManager.instance.getBothRequest(
            userID = userState.value.id,
            onSuccess = { candidates: List<UserData> ->
                _friendCandidateList.update { candidates }
            },
            onFailure = {}
        )
    }

    fun clearRequestSenderList() {
        _requestSenderList.update { emptyList() }
    }

    fun clearAboutFindFriendPageLists() {
        _friendsList.update { emptyList() }
        _friendCandidateList.update { emptyList() }
        _searchedUserList.update { emptyList() }
    }

    suspend fun getMyReview() {
        RetrofitManager.instance.getReviews(
            id = userState.value.id,
            onSuccess = { reviews: List<ReviewWithBook> ->
                _myReviewList.update { reviews }
            },
            onFailure = {}
        )
    }

    suspend fun getTimelineReview() {
        RetrofitManager.instance.getTimelineReview(
            userID = userState.value.id,
            onSuccess = { reviews: List<ReviewWithBook> ->
                _mainTimelineReviewList.update { reviews }
            },
            onFailure = {}
        )
    }

    suspend fun createReview(postReview: PostReview, visibilityLevel: String) {
        RetrofitManager.instance.createReview(
            review = postReview,
            visibilityLevel = visibilityLevel,
            onSuccess = {},
            onFailure = {}
        )
    }

    fun updateChosenReview(reviewWithBook: ReviewWithBook) {
        _chosenReview.update {
            reviewWithBook
        }
    }


    suspend fun getGroupTimelineReviews() {
        RetrofitManager.instance.getGroupTimelineReviews(
            userID = userState.value.id,
            groupID = chosenGroup.value.groupID,
            onSuccess = { reviews: List<ReviewWithBook> ->
                _groupTimelineReviewList.update { reviews }
            },
            onFailure = {}
        )
    }


    //comment 관련
    suspend fun getComments(reviewID: Int) {
        RetrofitManager.instance.getComments(
            userID = userState.value.id,
            reviewID = reviewID,
            onSuccess = { comments: List<Comment> ->
                _chosenReviewCommentList.update { comments }
            },
            onFailure = {}
        )
    }

    suspend fun createComment(postComment: PostComment) {
        RetrofitManager.instance.createComment(
            postComment = postComment,
            onSuccess = {
                viewModelScope.launch {
                    getComments(postComment.reviewID)
                }
            },
            onFailure = {}
        )
    }


    //quote 관련
    suspend fun getPresentQuestion() {
        RetrofitManager.instance.getPresentQuestion(
            groupID = chosenGroup.value.groupID,
            onSuccess = { question: GetQuoteQuestion ->
                _presentQuoteQuestion.update { question }
            },
            onFailure = {}
        )
    }

    suspend fun createQuoteQuestion(
        postQuoteAnswer: PostQuoteAnswer
    ) {
        RetrofitManager.instance.createQuoteQuestion(
            postQuoteAnswer = postQuoteAnswer,
            onSuccess = { },
            onFailure = {}
        )
    }

    suspend fun getPresentQuestionAnswers() {
        RetrofitManager.instance.getPresentQuestionAnswers(
            userID = userState.value.id,
            questionID = presentQuoteQuestion.value.id,
            onSuccess = { quoteAnswers: List<GetQuoteAnswer> ->
                _presentQuoteAnswers.update { quoteAnswers }
            },
            onFailure = {}
        )
    }

    suspend fun getPastQuestion() {
        RetrofitManager.instance.getPastQuestion(
            groupID = chosenGroup.value.groupID,
            onSuccess = { question: GetQuoteQuestion ->
                _pastQuoteQuestion.update { question }
            },
            onFailure = {}
        )
    }

    suspend fun getPastQuestionAnswers() {
        RetrofitManager.instance.getPresentQuestionAnswers( // questionID를 달리 하면 그때 당시의 answer얻기 가능
            userID = userState.value.id,
            questionID = pastQuoteQuestion.value.id,
            onSuccess = { quoteAnswers: List<GetQuoteAnswer> ->
                _pastQuoteAnswers.update { quoteAnswers }
            },
            onFailure = {}
        )
    }
    

    //commend 관련
    suspend fun getQuestionRecommend() {
        RetrofitManager.instance.getQuestionRecommend(
            questionID = pastQuoteQuestion.value.id,
            onSuccess = { books: UserBookMap ->
                _questionRecommendBookList.update { books }
            },
            onFailure = {}
        )
    }

    suspend fun getReviewRecommend() {
        RetrofitManager.instance.getReviewRecommend(
            reviewID = chosenReview.value.id,
            onSuccess = { books: List<BookData> ->
                _reviewRecommendBookList.update { books }
            },
            onFailure = {}
        )
    }
}
