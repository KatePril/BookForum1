package com.example.bookforum.data.repositories

import com.example.bookforum.data.daos.CommentDao
import com.example.bookforum.data.entities.Comment
import kotlinx.coroutines.flow.Flow

class CommentsRepository(private val commentDao: CommentDao) {
    fun getCommentsByPost(id: Int): Flow<List<Comment>?> = commentDao.getCommentsByPost(id)
    suspend fun insertComment(comment: Comment) = commentDao.insert(comment)
    suspend fun updateComment(comment: Comment) = commentDao.update(comment)
    suspend fun deleteComment(comment: Comment) = commentDao.delete(comment)
}