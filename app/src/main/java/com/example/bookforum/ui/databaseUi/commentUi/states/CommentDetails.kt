package com.example.bookforum.ui.databaseUi.commentUi.states

import com.example.bookforum.data.entities.Comment

data class CommentDetails(
    val id: Int = 0,
    val date: String = "",
    val text: String = "",
    val userId: Int = 0,
    val postId: Int = 0
)

fun CommentDetails.toComment(): Comment = Comment(
    id = id,
    date = date,
    text = text,
    userId =  userId,
    postId = postId
)