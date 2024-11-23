package com.example.frontcapstone.api

import com.example.frontcapstone.api.data.BookData
import com.example.frontcapstone.api.data.BookDataWithoutDesc
import com.example.frontcapstone.api.data.FollowerData
import com.example.frontcapstone.api.data.FollowerRequestData
import com.example.frontcapstone.api.data.GroupData
import com.example.frontcapstone.api.data.Review
import com.example.frontcapstone.api.data.SuccessResponse
import com.example.frontcapstone.api.data.UserData
import com.example.frontcapstone.api.data.UserInput
import com.example.frontcapstone.api.data.UserUIState
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    //auth 관련
    @POST("/user/create_user")
    suspend fun createUser(
        @Body userInput: UserInput
    ): Response<UserUIState>

    @GET("/user/get_user")
    suspend fun getUser(
        @Query("id") id: Int
    ): Response<UserUIState>

    @GET("/user/get_user_email")
    suspend fun getUserEmail(
        @Query("email") email: String
    ): Response<UserUIState>


    // book_router 관련
    @GET("/book/search_by_name")
    suspend fun getBookByName(
        @Query("bookName") bookName: String
    ): Response<List<BookDataWithoutDesc>>

    @GET("/book/search_by_id/{id}")
    suspend fun getBookByID(
        @Path("id") id: Int
    ): Response<BookData>


    // group_router 관련
    @POST("/group/create_group/{adminID}/{groupName}/{groupDescription}")
    suspend fun createGroup(
        @Path("adminID") adminID: Int,
        @Path("groupName") groupName: String,
        @Path("groupDescription") groupDescription: String,
    ): Response<SuccessResponse>

    @GET("/group/get_user_groups")
    suspend fun getUserGroups(
        @Query("userID") userID: Int,
    ): Response<List<GroupData>>


    //friend_router 관련
    @GET("/friend/get_users_by_email")
    suspend fun getUsersByEmail(
        @Query("email") email: String,
    ): Response<List<UserData>>

    @POST("/friend/create_followerRequest")
    suspend fun createFollowerRequest(
        @Body followerRequest: FollowerRequestData
    ): Response<SuccessResponse>

    @GET("/friend/get_request_sender")
    suspend fun getRequestSender(
        @Query("receiverID") receiverID: Int
    ): Response<List<UserData>>

    @POST("/friend/create_friend_and_autoDelete")
    suspend fun createFriendAndAutoDelete(
        @Body follower: FollowerData
    ): Response<SuccessResponse>

    @DELETE("/friend/delete_friend_request")
    suspend fun deleteFriendRequest(
        @Query("senderID") senderID: Int,
        @Query("receiverID") receiverID: Int
    ): Response<SuccessResponse>

    @GET("/friend/get_friends")
    suspend fun getFriends(
        @Query("userID") userID: Int,
    ): Response<List<UserData>>

    @GET("/friend/get_both_request")
    suspend fun getBothRequest(
        @Query("userID") userID: Int,
    ): Response<List<UserData>>


    //review 관련
    @GET("/review/get_user_reviews")
    suspend fun getUserReviews(
        @Query("userID") userID: Int,
    ): Response<List<Review>>
}