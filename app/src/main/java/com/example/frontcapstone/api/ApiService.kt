package com.example.frontcapstone.api

import com.example.frontcapstone.api.data.UserID
import com.example.frontcapstone.api.data.UserInput
import com.example.frontcapstone.data.UserData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {


    @POST("create_user")
    suspend fun createUser(
        @Body userInput :UserInput
    ): Response<UserID>

    @GET("get_user")
    fun getUser(
        @Query("id") id :Int
    ):Response<UserData>
}