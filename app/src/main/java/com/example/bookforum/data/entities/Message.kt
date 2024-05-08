package com.example.bookforum.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.bookforum.ui.databaseUi.messageUi.states.MessageDetails

@Entity(
    tableName = "messages",
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
        )
    ]
)
data class Message(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val text: String,
    val date: String,
    @ColumnInfo(name = "sender_id")
    val senderId: Int,
    @ColumnInfo(name = "receiver_id")
    val receiverId: Int,
    val edited: Int
)
/*TODO add isEdited*/

fun Message.toDetails(): MessageDetails = MessageDetails(
    id = id,
    text = text,
    date = date,
    senderId =  senderId,
    receiverId = receiverId,
    edited = edited
)