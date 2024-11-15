package com.example.frontcapstone.api

import android.util.Log
import com.example.frontcapstone.api.data.UserInput
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitManager {
    companion object {
        val instance: RetrofitManager by lazy { RetrofitManager() }
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://localhost:8000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val apiService = retrofit.create(ApiService::class.java)

    suspend fun createUser(nickname:String, email:String, uid:String, onSuccess:()->Unit, onFailure:()->Unit){
        try{
            val response = apiService.createUser(userInput = UserInput(nickname,email,uid))
            if(response.isSuccessful){
                val userID  = response.body()?.ID
                Log.d("API-Request", "createUser : User created with ID: $userID")


            }else{
                Log.e("API-Request", "Error: ${response.errorBody()}")
            }
        }
        catch (e:Exception){
            Log.e("API-Request", e.toString())
        }
    }

    suspend fun getUser(id:Int, onSuccess:()->Unit,onFailure:()->Unit) {
        try{
            val response = apiService.getUser(id)
            if(response.isSuccessful)
            {
                val userData = response.body()

            }else{
                Log.e("API-Request", "Error: ${response.errorBody()}")
            }
        } catch (e:Exception){
            Log.e("API-Request", e.toString())
        }
    }
}