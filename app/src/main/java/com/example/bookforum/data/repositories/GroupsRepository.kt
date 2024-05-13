package com.example.bookforum.data.repositories

import com.example.bookforum.data.daos.GroupDao
import com.example.bookforum.data.entities.Group
import kotlinx.coroutines.flow.Flow

class GroupsRepository(private val groupDao: GroupDao) {
    suspend fun insertGroup(group: Group): Long = groupDao.insert(group)
    suspend fun updateGroup(group: Group) = groupDao.update(group)
    suspend fun deleteGroup(group: Group) = groupDao.delete(group)
    fun getGroupsByUser(id: Int): Flow<List<Group>?> = groupDao.getGroupsByUser(id)
}