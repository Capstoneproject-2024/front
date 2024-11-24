package com.example.frontcapstone.api

import android.util.Log
import com.example.frontcapstone.api.data.BookData
import com.example.frontcapstone.api.data.BookDataWithoutDesc
import com.example.frontcapstone.api.data.Comment
import com.example.frontcapstone.api.data.FollowerData
import com.example.frontcapstone.api.data.FollowerRequestData
import com.example.frontcapstone.api.data.GroupData
import com.example.frontcapstone.api.data.GroupMemberData
import com.example.frontcapstone.api.data.LocalDateTimeAdapter
import com.example.frontcapstone.api.data.PostComment
import com.example.frontcapstone.api.data.PostReview
import com.example.frontcapstone.api.data.ReviewWithBook
import com.example.frontcapstone.api.data.UserData
import com.example.frontcapstone.api.data.UserInput
import com.example.frontcapstone.api.data.UserUIState
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime

class RetrofitManager {
    companion object {
        val instance: RetrofitManager by lazy { RetrofitManager() }
    }

    private val gson = GsonBuilder()
        .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://34.64.247.136:8001")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    private val apiService = retrofit.create(ApiService::class.java)

    suspend fun createUser(
        nickname: String,
        email: String,
        uid: String,
        onSuccess: (UserUIState) -> Unit,
        onFailure: () -> Unit
    ) {
        try {
            val response = apiService.createUser(userInput = UserInput(nickname, email, uid))
            if (response.isSuccessful) {
                val userUIState = response.body()
                Log.d("API-Request", "createUser : User created with ID: ${userUIState?.uid}")
                if (userUIState != null) {
                    onSuccess(userUIState)
                }

            } else {
                Log.e(
                    "API-Request 1",
                    "Error: ${response.errorBody()}"
                ) // 여기서  Error: okhttp3.ResponseBody$Companion$asResponseBody$1@564d011
                onFailure()
            }
        } catch (e: Exception) {
            Log.e("API-Request", e.toString())
            onFailure()
        }
    }

    suspend fun getUserByEmail(
        email: String,
        onSuccess: (UserUIState) -> Unit,
        onFailure: () -> Unit
    ) {
        try {
            val response = apiService.getUserEmail(email)
            if (response.isSuccessful) {
                val userUIState = response.body()
                if (userUIState != null) {
                    onSuccess(userUIState)
                }
            } else {
                onFailure()
            }

        } catch (e: Exception) {
            Log.e("API-Request", e.toString())
            onFailure()
        }
    }

    suspend fun getUser(id: Int, onSuccess: (UserUIState) -> Unit, onFailure: () -> Unit) {
        try {
            val response = apiService.getUser(id)
            if (response.isSuccessful) {
                val userUIState = response.body()
                if (userUIState != null) {
                    onSuccess(userUIState)
                }

            } else {
                Log.e("API-Request", "Error: ${response.errorBody()}")
                onFailure()
            }
        } catch (e: Exception) {
            Log.e("API-Request", e.toString())
            onFailure()
        }
    }

    suspend fun getBookByName(
        bookName: String,
        onSuccess: (List<BookDataWithoutDesc>) -> Unit,
        onFailure: () -> Unit
    ) {
        try {
            val response = apiService.getBookByName(bookName)
            if (response.isSuccessful) {
                val bookList = response.body()
                if (bookList != null) {
                    onSuccess(bookList)
                }

            } else {
                Log.e("BookAPI-Request", "Error: ${response.errorBody()}")
//                onFailure()
            }
        } catch (e: Exception) {
            Log.e("BookAPI-Request", e.toString())
//            onFailure()
        }
    }

    suspend fun getBookByID(
        id: Int,
        onSuccess: (BookData) -> Unit,
        onFailure: () -> Unit
    ) {
        try {
            val response = apiService.getBookByID(id)
            if (response.isSuccessful) {
                val book = response.body()
                Log.d("BookAPI-Request", book.toString())
                if (book != null) {
                    onSuccess(book)
                }

            } else {
                Log.e("BookAPI-Request", "Error: ${response.errorBody()}")
//                onFailure()
            }
        } catch (e: Exception) {
            Log.e("BookAPI-Request", e.toString())
//            onFailure()
        }
    }

    suspend fun createGroup(
        adminID: Int,
        groupName: String,
        groupDescription: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        try {
            val response = apiService.createGroup(
                adminID = adminID,
                groupName = groupName,
                groupDescription = groupDescription
            )
            if (response.isSuccessful) {
                onSuccess()
            } else {
                Log.e("GroupAPI-Request", "Error: ${response.errorBody()}")
//                onFailure()
            }
        } catch (e: Exception) {
            Log.e("GroupAPI-Request", e.toString())
//            onFailure()
        }
    }

    suspend fun getUserGroups(
        userID: Int,
        onSuccess: (List<GroupData>) -> Unit,
        onFailure: () -> Unit
    ) {
        try {
            val response = apiService.getUserGroups(userID)
            if (response.isSuccessful) {
                val groupList = response.body()
                if (groupList != null) {
                    onSuccess(groupList)
                }

            } else {
                Log.e("GroupAPI-Request", "Error: ${response.errorBody()}")
//                onFailure()
            }
        } catch (e: Exception) {
            Log.e("GroupAPI-Request", e.toString())
//            onFailure()
        }
    }

    suspend fun deleteGroup(
        groupID: Int,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        try {
            val response = apiService.deleteGroup(groupID = groupID)
            if (response.isSuccessful) {
                onSuccess()
            } else {
                Log.e("Group-delete-Request", "Error: ${response.errorBody()}")
//                onFailure()
            }
        } catch (e: Exception) {
            Log.e("Group-delete-Request", e.toString())
//            onFailure()
        }
    }

    suspend fun createMember(
        groupMemberData: GroupMemberData,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        try {
            val response = apiService.createMember(groupMemberData = groupMemberData)
            if (response.isSuccessful) {
                onSuccess()
            } else {
                Log.e("Group-memberCreate-Request", "Error: ${response.errorBody()}")
//                onFailure()
            }
        } catch (e: Exception) {
            Log.e("Group-memberCreate-Request", e.toString())
//            onFailure()
        }
    }

    suspend fun getMembers(
        groupID: Int,
        onSuccess: (List<UserData>) -> Unit,
        onFailure: () -> Unit
    ) {
        try {
            val response = apiService.getMembers(groupID)
            if (response.isSuccessful) {
                val memberList = response.body()
                if (memberList != null) {
                    onSuccess(memberList)
                }

            } else {
                Log.e("GroupAPI-Request", "Error: ${response.errorBody()}")
//                onFailure()
            }
        } catch (e: Exception) {
            Log.e("GroupAPI-Request", e.toString())
//            onFailure()
        }
    }

    suspend fun deleteMember(
        deleteMemberID: Int,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        try {
            val response = apiService.deleteMember(deleteMemberID = deleteMemberID)
            if (response.isSuccessful) {
                onSuccess()
            } else {
                Log.e("Group-memberDelete-Request", "Error: ${response.errorBody()}")
//                onFailure()
            }
        } catch (e: Exception) {
            Log.e("Group-memberDelete-Request", e.toString())
//            onFailure()
        }
    }

    suspend fun getSearchedNonMemberFriends(
        userID: Int,
        groupID: Int,
        email: String,
        onSuccess: (List<UserData>) -> Unit,
        onFailure: () -> Unit
    ) {
        try {
            val response = apiService.getSearchedNonMemberFriends(
                groupID = groupID,
                userID = userID,
                email = email,
            )
            Log.d("response", response.toString())
            if (response.isSuccessful) {
                val nonMemberList = response.body()
                if (nonMemberList != null) {
                    onSuccess(nonMemberList)
                }

            } else {
                Log.e("member-getnon-Request", "Error: ${response.errorBody()}")
//                onFailure()
            }
        } catch (e: Exception) {
            Log.e("member-non-Request", e.toString())
//            onFailure()
        }
    }


    suspend fun getUsersByEmail(
        email: String,
        onSuccess: (List<UserData>) -> Unit,
        onFailure: () -> Unit
    ) {
        try {
            val response = apiService.getUsersByEmail(email)
            if (response.isSuccessful) {
                val userList = response.body()
                if (userList != null) {
                    onSuccess(userList)
                }

            } else {
                Log.e("FriendAPI-getUsers-Request", "Error: ${response.errorBody()}")
//                onFailure()
            }
        } catch (e: Exception) {
            Log.e("Friend-getUsers-Request", e.toString())
//            onFailure()
        }
    }

    suspend fun createFollowerRequest(
        followerRequest: FollowerRequestData,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        try {
            val response = apiService.createFollowerRequest(followerRequest)
            if (response.isSuccessful) {
                onSuccess()
            } else {
                Log.e("FriendAPI-create-Request", "Error: ${response.errorBody()}")
//                onFailure()
            }
        } catch (e: Exception) {
            Log.e("Friend-create-Request", e.toString())
//            onFailure()
        }
    }

    suspend fun getRequestSender(
        receiverID: Int,
        onSuccess: (List<UserData>) -> Unit,
        onFailure: () -> Unit
    ) {
        try {
            val response = apiService.getRequestSender(receiverID)
            if (response.isSuccessful) {
                val senderIDList = response.body()
                if (senderIDList != null) {
                    onSuccess(senderIDList)
                }

            } else {
                Log.e("FriendAPI-getReceivers-Request", "Error: ${response.errorBody()}")
//                onFailure()
            }
        } catch (e: Exception) {
            Log.e("Friend-getReceivers-Request", e.toString())
//            onFailure()
        }
    }

    suspend fun createFriendAndAutoDelete(
        follower: FollowerData,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        try {
            val response = apiService.createFriendAndAutoDelete(follower)
            if (response.isSuccessful) {
                onSuccess()
            } else {
                Log.e("FriendAPI-create&autoDelete-Request", "Error: ${response.errorBody()}")
//                onFailure()
            }
        } catch (e: Exception) {
            Log.e("Friend-create&autoDelete-Request", e.toString())
//            onFailure()
        }
    }

    suspend fun deleteFriendRequest(
        senderID: Int,
        receiverID: Int,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        try {
            val response =
                apiService.deleteFriendRequest(senderID = senderID, receiverID = receiverID)
            if (response.isSuccessful) {
                onSuccess()
            } else {
                Log.e("FriendAPI-create&autoDelete-Request", "Error: ${response.errorBody()}")
//                onFailure()
            }
        } catch (e: Exception) {
            Log.e("Friend-create&autoDelete-Request", e.toString())
//            onFailure()
        }
    }

    suspend fun getFriends(
        userID: Int,
        onSuccess: (List<UserData>) -> Unit,
        onFailure: () -> Unit
    ) {
        try {
            val response = apiService.getFriends(userID)
            if (response.isSuccessful) {
                val friendList = response.body()
                if (friendList != null) {
                    onSuccess(friendList)
                }

            } else {
                Log.e("FriendAPI-getReceivers-Request", "Error: ${response.errorBody()}")
//                onFailure()
            }
        } catch (e: Exception) {
            Log.e("Friend-getReceivers-Request", e.toString())
//            onFailure()
        }
    }

    suspend fun getBothRequest(
        userID: Int,
        onSuccess: (List<UserData>) -> Unit,
        onFailure: () -> Unit
    ) {
        try {
            val response = apiService.getBothRequest(userID)
            if (response.isSuccessful) {
                val friendCandidateList = response.body()
                if (friendCandidateList != null) {
                    onSuccess(friendCandidateList)
                }

            } else {
                Log.e("FriendAPI-getReceivers-Request", "Error: ${response.errorBody()}")
//                onFailure()
            }
        } catch (e: Exception) {
            Log.e("Friend-getReceivers-Request", e.toString())
//            onFailure()
        }
    }

    suspend fun getTimelineReview(
        userID: Int,
        onSuccess: (List<ReviewWithBook>) -> Unit,
        onFailure: () -> Unit
    ) {
        try {
            val response = apiService.getTimelineReview(userID)
            if (response.isSuccessful) {
                val userMainTimeline = response.body()
                if (userMainTimeline != null) {
                    onSuccess(userMainTimeline)
                }

            } else {
                Log.e("ReviewAPI-getUserReviews-Request", "Error: ${response.errorBody()}")
//                onFailure()
            }
        } catch (e: Exception) {
            Log.e("ReviewAPI-getUserReviews-Request", e.toString())
//            onFailure()
        }
    }

    suspend fun createReview(
        review: PostReview,
        visibilityLevel: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        try {
            val response =
                apiService.createReview(review = review, visibilityLevel = visibilityLevel)
            if (response.isSuccessful) {
                onSuccess()
            } else {
                Log.e("FriendAPI-create&autoDelete-Request", "Error: ${response.errorBody()}")
//                onFailure()
            }
        } catch (e: Exception) {
            Log.e("Friend-create&autoDelete-Request", e.toString())
//            onFailure()
        }
    }


    suspend fun getComments(
        reviewID: Int,
        userID: Int,
        onSuccess: (List<Comment>) -> Unit,
        onFailure: () -> Unit
    ) {
        try {
            val response = apiService.getComments(reviewID = reviewID, userID = userID)
            if (response.isSuccessful) {
                val commentList = response.body()
                if (commentList != null) {
                    onSuccess(commentList)
                }

            } else {
                Log.e("Comment-getComments-Request", "Error: ${response.errorBody()}")
//                onFailure()
            }
        } catch (e: Exception) {
            Log.e("Comment-getComments-Request", e.toString())
//            onFailure()
        }
    }

    suspend fun createComment(
        postComment: PostComment,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        try {
            val response =
                apiService.createComment(postComment = postComment)
            if (response.isSuccessful) {
                onSuccess()
            } else {
                Log.e("Comment-create-Request", "Error: ${response.errorBody()}")
//                onFailure()
            }
        } catch (e: Exception) {
            Log.e("Comment-create-Request", e.toString())
//            onFailure()
        }
    }

}