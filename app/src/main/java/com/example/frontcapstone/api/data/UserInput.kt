package com.example.frontcapstone.api.data

data class UserInput(
    val nickname: String,
    val email: String,
    val uid: String
)


data class UserUIState(
    val id: Int =0,
    val nickname: String="",
    val email: String="",
    val uid: String=""


){
    fun isValid():Boolean{
        return id != 0
    }
}