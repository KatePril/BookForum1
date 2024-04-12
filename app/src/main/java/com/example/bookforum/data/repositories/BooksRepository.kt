package com.example.bookforum.data.repositories

import com.example.bookforum.data.daos.BookDao
import com.example.bookforum.data.entities.Book
import kotlinx.coroutines.flow.Flow

class BooksRepository(private val bookDao: BookDao) {
    fun getAllBooks(): Flow<List<Book>> = bookDao.getAllBooks()
    fun getBook(id: Int): Flow<Book?> = bookDao.getBook(id)
    suspend fun insertBook(book: Book) = bookDao.insert(book)
    suspend fun updateBook(book: Book) = bookDao.update(book)
    suspend fun deleteBook(book: Book) = bookDao.delete(book)
}