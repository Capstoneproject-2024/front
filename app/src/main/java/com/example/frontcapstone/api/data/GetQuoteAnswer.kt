package com.example.frontcapstone.api.data

import java.time.LocalDateTime

data class GetQuoteAnswer(
    val questionID: Int = -1,
    val userID: Int = -1,
    val bookID: Int = -1,
    val quotation: String = "",
    val date: LocalDateTime = LocalDateTime.now(),
    val name: String = "",
    val author: String = "",
    val year: String = "",
    val image: String = ""
)
