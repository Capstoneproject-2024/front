package com.example.frontcapstone.api.data

data class PostReview(
    val userID: Int,
    val bookID: Int,
    val rating: Float,
    val review: String,
    val quote: String
)
