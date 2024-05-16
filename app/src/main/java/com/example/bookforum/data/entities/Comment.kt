package com.example.bookforum.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

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