package com.example.bookforum.data.repositories

import com.example.bookforum.data.daos.GroupMessageDao
import com.example.bookforum.data.entities.GroupMessage
import com.example.bookforum.data.entities.User
import kotlinx.coroutines.flow.Flow

class GroupMessageRepository(private val groupMessageDao: GroupMessageDao) {
    fun getGroupMessagesByGroupId(id: Int): Flow<List<GroupMessage>?> = groupMessageDao.getGroupMessagesByGroupId(id)
    fun getGroupUsersByGroupId(id: Int): Flow<List<User>?> = groupMessageDao.getGroupUsersByGroupId(id)

    suspend fun insertGroupMessage(groupMessage: GroupMessage) = groupMessageDao.insert(groupMessage)

    suspend fun updateGroupMessage(groupMessage: GroupMessage) = groupMessageDao.update(groupMessage)

    suspend fun deleteGroupMessage(groupMessage: GroupMessage) = groupMessageDao.delete(groupMessage)
}