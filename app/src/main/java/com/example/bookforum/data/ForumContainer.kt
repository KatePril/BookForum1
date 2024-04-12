package com.example.bookforum.data

import com.example.bookforum.data.repositories.BooksRepository
import com.example.bookforum.data.repositories.CommentsRepository
import com.example.bookforum.data.repositories.LikedBooksRepository
import com.example.bookforum.data.repositories.UsersRepository

interface ForumContainer {
    val usersRepository: UsersRepository
    val booksRepository: BooksRepository
    val commentsRepository: CommentsRepository
    val likedBooksRepository: LikedBooksRepository
}