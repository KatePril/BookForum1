package com.example.bookforum.ui.databaseUi.groupUi.states.group

import com.example.bookforum.data.entities.GroupMessage

data class GroupMessageDetails(
    val id: Int = 0,
    val text: String = "",
    val date: String = "",
    val senderId: Int = 0,
    val groupId: Int = 0,
    val edited: Int = 0,
    val reply: Int = 0
)

fun GroupMessageDetails.toGroupMessage(): GroupMessage = GroupMessage(
    id = id,
    text = text,
    date = date,
    senderId = senderId,
    groupId = groupId,
    edited = edited,
    reply = reply
)
