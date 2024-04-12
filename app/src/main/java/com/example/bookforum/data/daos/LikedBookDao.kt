package com.example.bookforum.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bookforum.data.entities.LikedBook
import kotlinx.coroutines.flow.Flow

@Dao
interface LikedBookDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(likedBook: LikedBook)

    @Update
    suspend fun update(likedBook: LikedBook)

    @Delete
    suspend fun delete(likedBook: LikedBook)

    @Query("SELECT book_id from liked_books WHERE user_id = :id")
    fun getLikedBooks(id: Int): Flow<List<Int>>
}