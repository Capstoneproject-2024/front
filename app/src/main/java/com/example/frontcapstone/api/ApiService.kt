package com.example.frontcapstone.api

import com.example.frontcapstone.api.data.UserInput
import com.example.frontcapstone.api.data.UserUIState
import com.example.frontcapstone.data.BookData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
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
}