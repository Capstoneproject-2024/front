package com.example.frontcapstone.data

data class UserState(
    val id: Int = 0,
    val nickname: String = "",


){
    fun isValid():Boolean{
        return id != 0
    }
}
