package com.example.frontcapstone.api.data

import java.time.LocalDateTime

data class Review(
    val id: Int,
    val userID: Int,
    val bookID: Int,
    val rating: Float,
    val review: String,
    val quote: String,
    val reviewDate: LocalDateTime

)
