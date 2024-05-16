package com.example.bookforum.data.repositories

import com.example.bookforum.data.daos.GroupDao
import com.example.bookforum.data.entities.Group
import kotlinx.coroutines.flow.Flow

class GroupsRepository(private val groupDao: GroupDao) {
    suspend fun insertGroup(group: Group): Long = groupDao.insert(group)

    fun getGroupsByUser(id: Int): Flow<List<Group>?> = groupDao.getGroupsByUser(id)
}