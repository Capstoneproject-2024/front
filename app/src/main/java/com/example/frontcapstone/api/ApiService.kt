package com.example.frontcapstone.api

import com.example.frontcapstone.api.data.UserInput
import com.example.frontcapstone.api.data.UserUIState
import com.example.frontcapstone.data.BookData
import com.example.frontcapstone.data.GroupData
import com.example.frontcapstone.data.SuccessResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("create_user")
    suspend fun createUser(
        @Body userInput: UserInput
    ): Response<UserUIState>

    @GET("get_user")
    suspend fun getUser(
        @Query("id") id: Int
    ): Response<UserUIState>

    @GET("get_user_email")
    suspend fun getUserEmail(
        @Query("email") email: String
    ): Response<UserUIState>

    @GET("/book/search_by_name")
    suspend fun getBookByName(
        @Query("bookName") bookName: String
    ): Response<List<BookData>>

    @GET("/book/search_by_id/{id}")
    suspend fun getBookByID(
        @Path("id") id: Int
    ): Response<BookData>

    @POST("/group/create_group/{userID}/{groupName}/{groupDescription}")
    suspend fun createGroup(
        @Path("userID") userID: Int,
        @Path("groupName") groupName: String,
        @Path("groupDescription") groupDescription: String,
    ): Response<SuccessResponse>

    @GET("/group/get_user_groups")
    suspend fun getUserGroups(
        @Query("userID") userID: Int,
    ): Response<List<GroupData>>
}