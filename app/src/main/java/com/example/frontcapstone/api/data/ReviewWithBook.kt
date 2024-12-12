package com.example.frontcapstone.api.data

import java.time.LocalDateTime

data class ReviewWithBook(
    val id: Int = -1,
    val userID: Int = -1,
    val nickname: String = "",
    val bookID: Int = -1,
    val rating: Float = 0f,
    val review: String = "",
    val quote: String = "",
    val reviewDate: LocalDateTime = LocalDateTime.now(),
    val name: String = "",
    val author: String = "",
    val year: String = "",
    val desc: String = "",
    val image: String = "",
)
