package com.example.bookforum.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class Message(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val text: String,
    @ColumnInfo(name = "sender_id")
    val senderId: Int,
    @ColumnInfo(name = "receiver_id")
    val receiverId: Int
)
