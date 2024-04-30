package com.example.bookforum.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.bookforum.ui.databaseUi.commentUi.states.CommentDetails

@Entity(
    tableName = "comments",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Post::class,
            parentColumns = ["id"],
            childColumns = ["post_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Comment(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String,
    val text: String,
    @ColumnInfo(name = "user_id")
    val userId: Int,
    @ColumnInfo(name = "post_id")
    val postId: Int
)


fun Comment.toCommentDetails(): CommentDetails = CommentDetails(
    id = id,
    date = date,
    text = text,
    userId = userId,
    postId = postId
)