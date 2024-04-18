package com.example.bookforum.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bookforum.ui.databaseUi.booksUI.states.BookDetails

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val author: String,
    val published: String,
    val review: String
)

fun Book.toDetails(): BookDetails = BookDetails(
    id = id,
    title = title,
    author = author,
    published = published,
    review = review
)