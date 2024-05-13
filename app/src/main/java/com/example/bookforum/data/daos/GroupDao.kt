package com.example.bookforum.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bookforum.data.entities.Group
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(group: Group): Long

    @Update
    suspend fun update(group: Group)

    @Delete
    suspend fun delete(group: Group)

    @Query("SELECT groups.id, groups.title FROM groups INNER JOIN group_members ON (group_members.user_id = :id) AND (group_members.group_id = groups.id)")
    fun getGroupsByUser(id: Int): Flow<List<Group>>

}