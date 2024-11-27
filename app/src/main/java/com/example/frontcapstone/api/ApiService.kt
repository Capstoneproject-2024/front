package com.example.frontcapstone.api

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

    @DELETE("/group/delete_group")
    suspend fun deleteGroup(
        @Query("groupID") groupID: Int,
    ): Response<SuccessResponse>

    @POST("/group/create_member")
    suspend fun createMember(
        @Body groupMemberData: GroupMemberData
    ): Response<SuccessResponse>

    @GET("/group/get_members")
    suspend fun getMembers(
        @Query("groupID") groupID: Int
    ): Response<List<UserData>>

    @DELETE("/group/delete_member")
    suspend fun deleteMember(
        @Query("deleteMemberID") deleteMemberID: Int
    ): Response<SuccessResponse>

    @GET("/group/get_searched_nonMember_friends")
    suspend fun getSearchedNonMemberFriends(
        @Query("groupID") groupID: Int,
        @Query("userID") userID: Int,
        @Query("email") email: String,
    ): Response<List<UserData>>


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
    @GET("/review/get_timeline_reviews")
    suspend fun getTimelineReview(
        @Query("userID") userID: Int,
    ): Response<List<ReviewWithBook>>

    @GET("/review/get_my_review")
    suspend fun getMyReview(
        @Query("userID") userID: Int,
    ): Response<List<ReviewWithBook>>

    @POST("/review/create_review/{visibilityLevel}")
    suspend fun createReview(
        @Path("visibilityLevel") visibilityLevel: String,
        @Body review: PostReview
    ): Response<SuccessResponse>

    @GET("/review/get_group_timeline_reviews")
    suspend fun getGroupTimelineReviews(
        @Query("userID") userID: Int,
        @Query("groupID") groupID: Int,
    ): Response<List<ReviewWithBook>>


    //comment 관련
    @GET("/comment/get_comments")
    suspend fun getComments(
        @Query("reviewID") reviewID: Int,
        @Query("userID") userID: Int,
    ): Response<List<Comment>>

    @POST("/comment/create_comment")
    suspend fun createComment(
        @Body postComment: PostComment
    ): Response<SuccessResponse>


    //quote; 관련
    @GET("/quote/get_present_question")
    suspend fun getPresentQuestion(
        @Query("groupID") groupID: Int,
    ): Response<GetQuoteQuestion>

    @POST("/quote/create_quote_answer")
    suspend fun createQuoteQuestion(
        @Body postQuoteAnswer: PostQuoteAnswer
    ): Response<SuccessResponse>

    @GET("/quote/get_present_question_answers")
    suspend fun getPresentQuestionAnswers(
        @Query("questionID") questionID: Int,
        @Query("userID") userID: Int,
    ): Response<List<GetQuoteAnswer>>

    @GET("/quote/get_past_question")
    suspend fun getPastQuestion(
        @Query("groupID") groupID: Int,
    ): Response<GetQuoteQuestion>
}