package com.example.bookforum.data.repositories

import com.example.bookforum.data.daos.GroupDao
import com.example.bookforum.data.entities.Group

class GroupsRepository(private val groupDao: GroupDao) {
    suspend fun insertGroup(group: Group) = groupDao.insert(group)
    suspend fun updateGroup(group: Group) = groupDao.update(group)
    suspend fun deleteGroup(group: Group) = groupDao.delete(group)
    fun getGroupsByUser(id: Int) = groupDao.getGroupsByUser(id)
}