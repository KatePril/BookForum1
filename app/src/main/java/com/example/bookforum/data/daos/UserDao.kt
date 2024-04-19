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
    suspend fun insert(user: User) // tested

    @Update
    suspend fun update(user: User) // tested

    @Delete
    suspend fun delete(user: User) // tested

    @Query("SELECT * from users WHERE username = :username")
    fun getUserByUsername(username: String): Flow<User> // tested

    @Query("SELECT * from users WHERE id = :id")
    fun getUserById(id: Int): Flow<User> // tested

    @Query("SELECT * from users")
    fun getAllUsers(): Flow<List<User>> // tested

    @Query("DELETE FROM users WHERE id = :id")
    suspend fun deleteUserById(id: Int) // tested
}