package com.example.bookforum.ui.databaseUi.booksUI.states

import com.example.bookforum.data.entities.Book

data class BookDetails(
    val id: Int = 0,
    val title: String = "",
    val author: String = "",
    val published: String = "",
    val review: String = ""
)

fun BookDetails.toBook(): Book = Book(
    id = id,
    title = title,
    author = author,
    published = published,
    review = review
)