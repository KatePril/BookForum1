package com.example.bookforum.ui.databaseUi.messageUi.states

import com.example.bookforum.data.entities.Message

data class MessageDetails(
    val id: Int = 0,
    val text: String = "",
    val date: String = "",
    val senderId: Int = 0,
    val receiverId: Int = 0
)

fun MessageDetails.toMessage(): Message = Message(
    id = id,
    text = text,
    date = date,
    senderId =  senderId,
    receiverId = receiverId
)