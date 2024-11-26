package com.example.frontcapstone.api.data

import java.time.LocalDateTime

data class GetQuoteQuestion(
    val id: Int = -1,
    val groupID: Int = -1,
    val vocabularyID: Int = -1,
    val question: String = "",
    val date: LocalDateTime = LocalDateTime.now(),
)
