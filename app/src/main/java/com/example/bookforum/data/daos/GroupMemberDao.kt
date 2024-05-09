package com.example.bookforum.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bookforum.data.entities.GroupMember

@Dao
interface GroupMemberDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(groupMember: GroupMember)

    @Update
    suspend fun update(groupMember: GroupMember)

    @Delete
    suspend fun delete(groupMember: GroupMember)

    @Query("SELECT * FROM group_members WHERE group_id = :id")
    fun getGroupMembersByGroupId(id: Int)
}