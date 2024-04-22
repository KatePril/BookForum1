package com.example.bookforum.data.repositories

import com.example.bookforum.data.daos.LikedPostDao
import com.example.bookforum.data.entities.LikedPost
import kotlinx.coroutines.flow.Flow

class LikedPostsRepository(private val likedPostDao: LikedPostDao) {
    fun getLikedPosts(id: Int): Flow<List<Int>?> = likedPostDao.getLikedPosts(id)
    suspend fun insertLikedPost(likedPost: LikedPost) = likedPostDao.insert(likedPost)
    suspend fun updateLikedPost(likedPost: LikedPost) = likedPostDao.update(likedPost)
    suspend fun deleteLikedPost(likedPost: LikedPost) = likedPostDao.delete(likedPost)
}