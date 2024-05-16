package com.example.bookforum.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bookforum.data.entities.LikedPost
import com.example.bookforum.data.entities.Post
import kotlinx.coroutines.flow.Flow

@Dao
interface LikedPostDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(likedPost: LikedPost)

    @Update
    suspend fun update(likedPost: LikedPost)

    @Delete
    suspend fun delete(likedPost: LikedPost)

    @Query("SELECT posts.id, posts.title, posts.author, posts.published, posts.review, posts.user_id from posts INNER JOIN liked_posts WHERE (liked_posts.user_id = :id) AND (posts.id = liked_posts.post_id)")
    fun getLikedPosts(id: Int): Flow<List<Post>>

    @Query("SELECT id from liked_posts WHERE user_id = :userId AND post_id = :postId")
    fun getLikedPostByIds(userId: Int, postId: Int): Flow<Int?>
}