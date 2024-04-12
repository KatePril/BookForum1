package com.example.bookforum.data

import android.content.Context
import com.example.bookforum.data.repositories.BooksRepository
import com.example.bookforum.data.repositories.CommentsRepository
import com.example.bookforum.data.repositories.LikedBooksRepository
import com.example.bookforum.data.repositories.UsersRepository

class ForumDataContainer(private val context: Context): ForumContainer {
    override val usersRepository: UsersRepository by lazy {
        UsersRepository(ForumDatabase.getDatabase(context).userDao())
    }
    override val booksRepository: BooksRepository by lazy {
        BooksRepository(ForumDatabase.getDatabase(context).bookDao())
    }
    override val commentsRepository: CommentsRepository by lazy {
        CommentsRepository(ForumDatabase.getDatabase(context).commentDao())
    }
    override val likedBooksRepository: LikedBooksRepository by lazy {
        LikedBooksRepository(ForumDatabase.getDatabase(context).likedBookDao())
    }
}