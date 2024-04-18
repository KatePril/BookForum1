package com.example.bookforum.network.apiObjects

import kotlinx.serialization.Serializable

@Serializable
data class ResultApiObject(
    val results: List<BookApiObject>
)