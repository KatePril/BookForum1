package com.example.bookforum.data.repositories

import com.example.bookforum.data.daos.MessageDao
import com.example.bookforum.data.entities.Message
import kotlinx.coroutines.flow.Flow

class MessagesRepository(private val messageDao: MessageDao) {
    fun getChatMessages(currentUserId: Int, receiverId: Int): Flow<List<Message>?> =
        messageDao.getChatMessages(currentUserId, receiverId)

    suspend fun insertMessage(message: Message) = messageDao.insert(message)

    suspend fun updateMessage(message: Message) = messageDao.update(message)

    suspend fun deleteMessage(message: Message) = messageDao.delete(message)

    fun getMessageById(id: Int): Flow<Message> = messageDao.getMessageById(id)
}
