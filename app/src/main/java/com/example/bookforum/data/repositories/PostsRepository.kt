package com.example.bookforum.data.repositories

import com.example.bookforum.data.daos.PostDao
import com.example.bookforum.data.entities.Post
import kotlinx.coroutines.flow.Flow

class PostsRepository(private val postDao: PostDao) {
    fun getAllPosts(): Flow<List<Post>> = postDao.getAllPosts()
    fun getPost(id: Int): Flow<Post?> = postDao.getPostById(id)
    suspend fun insertPost(post: Post) = postDao.insert(post)
    suspend fun updatePost(post: Post) = postDao.update(post)
    suspend fun deletePost(post: Post) = postDao.delete(post)
}