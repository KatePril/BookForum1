package com.example.bookforum.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.bookforum.ui.databaseUi.groupUi.states.group.GroupMessageDetails

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
    @ColumnInfo(name = "group_id")
    val groupId: Int,
    val edited: Int,
    val reply: Int
)

fun GroupMessage.toDetails(): GroupMessageDetails = GroupMessageDetails(
    id = id,
    text = text,
    date = date,
    senderId = senderId,
    groupId = groupId,
    edited = edited,
    reply = reply
)