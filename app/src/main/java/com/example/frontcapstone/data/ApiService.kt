package com.example.frontcapstone.data

import com.example.frontcapstone.viemodel.DataSet
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("execute_query")
    fun executeQuery(@Query("query") query: String): Call<DataSet>
}