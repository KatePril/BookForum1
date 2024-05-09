package com.example.bookforum.data.repositories

import com.example.bookforum.data.daos.GroupMemberDao
import com.example.bookforum.data.entities.GroupMember

class GroupMembersRepository(private val groupMemberDao: GroupMemberDao) {
    suspend fun insertGroupMember(groupMember: GroupMember) = groupMemberDao.insert(groupMember)
    suspend fun updateGroupMember(groupMember: GroupMember) = groupMemberDao.update(groupMember)
    suspend fun deleteGroupMember(groupMember: GroupMember) = groupMemberDao.delete(groupMember)
    fun getGroupMembersByGroupId(id: Int) = groupMemberDao.getGroupMembersByGroupId(id)
}