package com.example.frontcapstone.viemodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontcapstone.api.RetrofitManager
import com.example.frontcapstone.api.data.BookData
import com.example.frontcapstone.api.data.BookDataWithoutDesc
import com.example.frontcapstone.api.data.GroupData
import com.example.frontcapstone.api.data.UserData
import com.example.frontcapstone.api.data.UserUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {

    private val _userState = MutableStateFlow(UserUIState())
    val userState: StateFlow<UserUIState> = _userState.asStateFlow()

    private val _searchedBooks =
        MutableStateFlow<List<BookDataWithoutDesc>>(emptyList())//MutableStateFlow(mutableListOf<BookData>())
    val searchedBooks: StateFlow<List<BookDataWithoutDesc>> = _searchedBooks.asStateFlow()

    private val _chosenBook = MutableStateFlow(BookData())
    val chosenBook: StateFlow<BookData> = _chosenBook.asStateFlow()

    private val _groupList = MutableStateFlow<List<GroupData>>(emptyList())
    val groupList: StateFlow<List<GroupData>> = _groupList.asStateFlow()

    private val _requestSenderList = MutableStateFlow<List<UserData>>(emptyList())
    val requestSenderList: StateFlow<List<UserData>> = _requestSenderList.asStateFlow()


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

    suspend fun getRequestSender() {
        RetrofitManager.instance.getRequestSender(
            receiverID = userState.value.id,
            onSuccess = { requestSenders: List<UserData> ->
                _requestSenderList.update { requestSenders }
            },
            onFailure = {}
        )
    }
}
