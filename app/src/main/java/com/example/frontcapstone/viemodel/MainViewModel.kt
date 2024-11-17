package com.example.frontcapstone.viemodel

import android.provider.ContactsContract.CommonDataKinds.Nickname
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.frontcapstone.AuthManager
import com.example.frontcapstone.api.ApiService
import com.example.frontcapstone.api.data.UserInput
import com.example.frontcapstone.data.UserState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainViewModel : ViewModel() {

    private val _userState = MutableStateFlow(UserState())
    val userState : StateFlow<UserState> = _userState.asStateFlow()



    fun updateUserState(id : Int, nickname: String){
        _userState.update{
            it.copy(
                id = id,
                nickname = nickname
            )
        }
    }

    fun updateUserState(nickname: String){
        _userState.update{
            it.copy(
                nickname = nickname
            )
        }
    }

    fun updateUserState(nickname:String?,email:String?,uid:String){
        //create여부 확인
        // create문 호출
        //state 업데이트


    }














    //(private val query_start: String)

//    // retrofit 객체 생성
//    val retrofit = Retrofit.Builder()
//        .baseUrl("basse url put ")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//    val apiService = retrofit.create(ApiService::class.java)
//
//    // 로그인한 유저 정보
//    private var _userData = MutableStateFlow(LoginUser())
//    val userData: StateFlow<LoginUser> = _userData.asStateFlow()
//
//
//    fun appInit(userId: Int) {
//        getInitialUserInfo(userId)
//    }
//
//    fun generateGetUserQuery(id: Int): String {
//        val userQuery = "SELECT * FROM userTable WHERE ID=?"
//        return userQuery.replace("?", id.toString())
////    }
////
////    fun getInitialUserInfo(id: Int) {
////        val getUserQuery = generateGetUserQuery(id)
////        val call = apiService.executeQuery(getUserQuery)
////
////        call.enqueue(object : Callback<DataSet> {
////            override fun onResponse(
////                call: Call<DataSet>,
////                response: Response<DataSet>
//            ) {
//                if (response.isSuccessful) {
//                    val userResponse = response.body()
//                    if (userResponse != null) {
//                        Log.d("UserInfo", userResponse.toString())
//                    }
//                    userResponse?.result?.forEach { user ->
//                        if (user.size == 4) { // 필요한 데이터가 모두 있는지 확인
//                            _userData.update {
//                                it.copy(
//                                    ID = (user[0] as Double).toInt(),
//                                    nickname = user[1] as String,
//                                    email = user[2] as String,
//                                    uid = user[3] as String
//                                )
//                            }
//                        } else {
//                            Log.d("UserInfo", "Unexpected data format")
//                        }
//                    }
//                } else {
//                    Log.d("UserInfo", "Failed to retrieve data: ${response.errorBody()?.string()}")
//                }
//            }
//
//            override fun onFailure(call: Call<DataSet>, t: Throwable) {
//                Log.d("UserInfo", "Error: ${t.message}")
//            }
//        })
//
//    }

}
