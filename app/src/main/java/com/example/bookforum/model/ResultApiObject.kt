package com.example.bookforum.model

import kotlinx.serialization.Serializable

@Serializable
data class ResultApiObject(
    val results: List<BookApiObject>
)