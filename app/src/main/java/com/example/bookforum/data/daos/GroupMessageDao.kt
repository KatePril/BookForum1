package com.example.bookforum.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bookforum.data.entities.GroupMessage
import com.example.bookforum.data.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupMessageDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(groupMessage: GroupMessage)

    @Update
    suspend fun update(groupMessage: GroupMessage)

    @Delete
    suspend fun delete(groupMessage: GroupMessage)

    @Query("SELECT * FROM group_messages WHERE group_id = :id")
    fun getGroupMessagesByGroupId(id: Int): Flow<List<GroupMessage>>

    @Query("SELECT users.id, users.username, users.password, users.email, users.salt FROM users INNER JOIN group_messages ON (users.id = group_messages.sender_id) AND (group_messages.group_id = :id)")
    fun getGroupUsersByGroupId(id: Int): Flow<List<User>>
}