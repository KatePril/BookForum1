package com.example.bookforum.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bookforum.data.entities.GroupMember
import com.example.bookforum.data.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupMemberDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(groupMember: GroupMember)

    @Update
    suspend fun update(groupMember: GroupMember)

    @Delete
    suspend fun delete(groupMember: GroupMember)

    @Query("SELECT * FROM group_members WHERE group_id = :id")
    fun getGroupMembersByGroupId(id: Int): Flow<List<GroupMember>>

    @Query("SELECT users.id, users.username, users.password, users.email FROM users INNER JOIN group_members WHERE (group_members.user_id <> :userId) AND (group_members.group_id = :groupId) AND (group_members.user_id = users.id)")
    fun getUsersByGroupId(groupId: Int, userId: Int): Flow<List<User>>

    @Query("SELECT * FROM group_members WHERE user_id = :id")
    fun getGroupMemberByUserId(id: Int): Flow<GroupMember>
}