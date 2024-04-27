package com.example.bookforum.ui.databaseUi.postsUI.states

import com.example.bookforum.data.entities.Post

data class PostDetails(
    val id: Int = 0,
    val title: String = "",
    val author: String = "",
    val published: String = "",
    val review: String = ""
)

fun PostDetails.toPost(): Post = Post(
    id = id,
    title = title,
    author = author,
    published = published,
    review = review
)