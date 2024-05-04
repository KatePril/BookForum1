package com.example.bookforum.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bookforum.data.entities.Message
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(message: Message)

    @Update
    suspend fun update(message: Message)

    @Delete
    suspend fun delete(message: Message)

    @Query("SELECT * FROM messages WHERE (sender_id = :currentUserId AND receiver_id = :receiverId) " +
            "OR (sender_id = :receiverId AND receiver_id = :currentUserId) ORDER BY id ASC")
    fun getChatMessages(currentUserId: Int, receiverId: Int): Flow<List<Message>>
}