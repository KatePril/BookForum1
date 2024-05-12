package com.example.bookforum.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "group_messages",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["sender_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["receiver_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Group::class,
            parentColumns = ["id"],
            childColumns = ["group_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class GroupMessage(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val text: String,
    val date: String,
    @ColumnInfo(name = "sender_id")
    val senderId: Int,
    @ColumnInfo(name = "receiver_id")
    val receiverId: Int,
    @ColumnInfo(name = "group_id")
    val groupId: Int,
    val edited: Int,
    val reply: Int
)
