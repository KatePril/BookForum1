package com.example.bookforum.network.apiObjects

import kotlinx.serialization.Serializable

@Serializable
data class ResultObject(
    val results: List<Book>
)