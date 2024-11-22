package com.example.frontcapstone.api

import android.util.Log
import com.example.frontcapstone.api.data.UserInput
import com.example.frontcapstone.api.data.UserUIState
import com.example.frontcapstone.data.BookData
import com.example.frontcapstone.data.GroupData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitManager {
    companion object {
        val instance: RetrofitManager by lazy { RetrofitManager() }
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://172.30.1.47:8000")
        .addConverterFactory(GsonConverterFactory.create())
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
                ) // TODO 여기서  Error: okhttp3.ResponseBody$Companion$asResponseBody$1@564d011
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
        onSuccess: (List<BookData>) -> Unit,
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
        userID: Int,
        groupName: String,
        groupDescription: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        try {
            val response = apiService.createGroup(
                userID = userID,
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

}