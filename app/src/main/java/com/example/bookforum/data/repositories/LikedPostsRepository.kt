package com.example.bookforum.data.repositories

import com.example.bookforum.data.daos.LikedPostDao
import com.example.bookforum.data.entities.LikedPost
import com.example.bookforum.data.entities.Post
import kotlinx.coroutines.flow.Flow

class LikedPostsRepository(private val likedPostDao: LikedPostDao) {
    fun getLikedPosts(id: Int): Flow<List<Post>?> = likedPostDao.getLikedPosts(id)

    fun getLikedPostByIds(userId: Int, postId: Int): Flow<Int?> =
            likedPostDao.getLikedPostByIds(userId = userId, postId = postId)

    suspend fun insertLikedPost(likedPost: LikedPost) = likedPostDao.insert(likedPost)

    suspend fun deleteLikedPost(likedPost: LikedPost) = likedPostDao.delete(likedPost)
}