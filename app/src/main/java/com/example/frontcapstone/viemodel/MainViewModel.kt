package com.example.frontcapstone.viemodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontcapstone.api.RetrofitManager
import com.example.frontcapstone.api.data.BookData
import com.example.frontcapstone.api.data.BookDataWithoutDesc
import com.example.frontcapstone.api.data.Comment
import com.example.frontcapstone.api.data.FollowerData
import com.example.frontcapstone.api.data.FollowerRequestData
import com.example.frontcapstone.api.data.GroupData
import com.example.frontcapstone.api.data.GroupMemberData
import com.example.frontcapstone.api.data.PostComment
import com.example.frontcapstone.api.data.PostReview
import com.example.frontcapstone.api.data.ReviewWithBook
import com.example.frontcapstone.api.data.UserData
import com.example.frontcapstone.api.data.UserUIState
import kotlinx.coroutines.CompletableDeferred
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

    private val _chosenReview = MutableStateFlow(ReviewWithBook())
    val chosenReview: StateFlow<ReviewWithBook> = _chosenReview.asStateFlow()

    //comment관련
    private val _chosenReviewCommentList = MutableStateFlow<List<Comment>>(emptyList())
    val chosenReviewCommentList: StateFlow<List<Comment>> = _chosenReviewCommentList.asStateFlow()


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

    suspend fun getBookByID(id: Int): BookData {
        val returnBook = CompletableDeferred<BookData>()
        RetrofitManager.instance.getBookByID(id = id,
            onSuccess = { book: BookData ->
                returnBook.complete(book)
            },
            onFailure = {
                returnBook.completeExceptionally(RuntimeException("Failed to fetch book data"))
            }
        )
        return returnBook.await()
    }

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
}
