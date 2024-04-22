package com.example.bookforum.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bookforum.data.entities.LikedPost
import kotlinx.coroutines.flow.Flow

@Dao
interface LikedPostDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(likedPost: LikedPost)

    @Update
    suspend fun update(likedPost: LikedPost)

    @Delete
    suspend fun delete(likedPost: LikedPost)

    @Query("SELECT post_id from liked_posts WHERE user_id = :id")
    fun getLikedPosts(id: Int): Flow<List<Int>>
}