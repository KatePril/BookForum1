package com.example.bookforum.data.repositories

import com.example.bookforum.data.daos.GroupMemberDao
import com.example.bookforum.data.entities.GroupMember
import com.example.bookforum.data.entities.User
import kotlinx.coroutines.flow.Flow

class GroupMembersRepository(private val groupMemberDao: GroupMemberDao) {
    suspend fun insertGroupMember(groupMember: GroupMember) = groupMemberDao.insert(groupMember)

    suspend fun updateGroupMember(groupMember: GroupMember) = groupMemberDao.update(groupMember)

    suspend fun deleteGroupMember(groupMember: GroupMember) = groupMemberDao.delete(groupMember)

    fun getGroupMembersByGroupId(groupId: Int, userId: Int): Flow<List<GroupMember>?> =
        groupMemberDao.getGroupMembersByGroupId(groupId, userId)

    fun getUsersByGroupId(id: Int): Flow<List<User>?> =
        groupMemberDao.getUsersByGroupId(id)

    fun getGroupMemberByUserId(id: Int): Flow<GroupMember?> = groupMemberDao.getGroupMemberByUserId(id)
}