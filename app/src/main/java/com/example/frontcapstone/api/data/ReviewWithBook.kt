package com.example.frontcapstone.api.data

import java.time.LocalDateTime

data class ReviewWithBook(
    val id: Int,
    val userID: Int,
    val bookID: Int,
    val rating: Float,
    val review: String,
    val quote: String,
    val reviewDate: LocalDateTime,
    val name: String = "",
    val author: String = "",
    val year: String = "",
    val desc: String = "",
    val image: String = "",
)
