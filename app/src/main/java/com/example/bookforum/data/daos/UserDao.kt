package com.example.bookforum.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bookforum.data.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User): Long

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * from users WHERE username = :username")
    fun getUserByUsername(username: String): Flow<User>

    @Query("SELECT * from users WHERE id = :id")
    fun getUserById(id: Int): Flow<User>

    @Query("SELECT * from users WHERE id <> :id")
    fun getUserByNotId(id: Int): Flow<List<User>>

    @Query("SELECT * from users")
    fun getAllUsers(): Flow<List<User>>
}