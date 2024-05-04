package com.example.bookforum.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.bookforum.ui.databaseUi.postsUI.states.PostDetails

@Entity(
    tableName = "posts",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Post(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val author: String,
    val published: String,
    val review: String,
    @ColumnInfo(name = "user_id")
    val userId: Int,
)

fun Post.toDetails(): PostDetails = PostDetails(
    id = id,
    title = title,
    author = author,
    published = published,
    review = review,
    userId = userId
)