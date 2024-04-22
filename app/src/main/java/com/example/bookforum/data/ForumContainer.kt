package com.example.bookforum.data

import com.example.bookforum.data.repositories.PostsRepository
import com.example.bookforum.data.repositories.CommentsRepository
import com.example.bookforum.data.repositories.LikedPostsRepository
import com.example.bookforum.data.repositories.UsersRepository

interface ForumContainer {
    val usersRepository: UsersRepository
    val postsRepository: PostsRepository
    val commentsRepository: CommentsRepository
    val likedPostsRepository: LikedPostsRepository
}