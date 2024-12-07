package com.example.frontcapstone.api.data

import java.time.LocalDateTime

data class Comment(
    val commentID: Int,
    val reviewID: Int,
    val userID: Int,
    val comment: String,
    val commentDate: LocalDateTime,
)
