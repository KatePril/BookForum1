package com.example.bookforum.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bookforum.data.entities.GroupMessage
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

    /* TODO get users by GroupId*/
}