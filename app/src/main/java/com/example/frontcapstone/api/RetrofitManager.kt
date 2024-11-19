package com.example.frontcapstone.api

import android.util.Log
import com.example.frontcapstone.api.data.UserInput
import com.example.frontcapstone.api.data.UserUIState
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitManager {
    companion object {
        val instance: RetrofitManager by lazy { RetrofitManager() }
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.219.101:8000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val apiService = retrofit.create(ApiService::class.java)

    suspend fun createUser(nickname:String, email:String, uid:String, onSuccess: (UserUIState) -> Unit, onFailure:()->Unit){
        try{
            val response = apiService.createUser(userInput = UserInput(nickname,email,uid))
            if(response.isSuccessful){
                val userUIState  = response.body()
                Log.d("API-Request", "createUser : User created with ID: ${userUIState?.uid}")
                if (userUIState != null) {
                    onSuccess(userUIState)
                }

            }else{
                Log.e("API-Request", "Error: ${response.errorBody()}")
                onFailure()
            }
        }
        catch (e:Exception){
            Log.e("API-Request", e.toString())
            onFailure()
        }
    }

    suspend fun getUserByEmail(email:String, onSuccess: (UserUIState) -> Unit, onFailure: () -> Unit){
        try{
            val response = apiService.getUserEmail(email)
            if(response.isSuccessful){
                val userUIState = response.body()
                if (userUIState != null) {
                    onSuccess(userUIState)
                }
            } else{
                onFailure()
            }

        } catch(e:Exception){
            Log.e("API-Request", e.toString())
            onFailure()
        }
    }

    suspend fun getUser(id:Int, onSuccess:(UserUIState)->Unit,onFailure:()->Unit) {
        try{
            val response = apiService.getUser(id)
            if(response.isSuccessful)
            {
                val userUIState = response.body()
                if (userUIState != null) {
                    onSuccess(userUIState)
                }

            }else{
                Log.e("API-Request", "Error: ${response.errorBody()}")
                onFailure()
            }
        } catch (e:Exception){
            Log.e("API-Request", e.toString())
            onFailure()
        }
    }


}