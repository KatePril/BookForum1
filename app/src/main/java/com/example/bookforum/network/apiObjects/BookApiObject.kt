package com.example.bookforum.network.apiObjects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookApiObject(
    val ISBN: String,
    val author: String,
    val description: String,
    @SerialName(value = "img_link")
    val imgLink: String,
    @SerialName(value = "pdf_link")
    val pdfLink: String,
    val publisher: String,
    val title: String,
    val year: String
)
