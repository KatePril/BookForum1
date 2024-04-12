package com.example.bookforum.data.repositories

import com.example.bookforum.data.daos.LikedBookDao
import com.example.bookforum.data.entities.LikedBook
import kotlinx.coroutines.flow.Flow

class LikedBooksRepository(private val likedBookDao: LikedBookDao) {
    fun getLikedBooks(id: Int): Flow<List<Int>?> = likedBookDao.getLikedBooks(id)
    suspend fun insertLikedBook(likedBook: LikedBook) = likedBookDao.insert(likedBook)
    suspend fun updateLikedBook(likedBook: LikedBook) = likedBookDao.update(likedBook)
    suspend fun deleteLikedBook(likedBook: LikedBook) = likedBookDao.delete(likedBook)
}