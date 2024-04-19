package com.example.bookforum.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bookforum.data.entities.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(book: Book) // tested

    @Update
    suspend fun update(book: Book) // tested

    @Delete
    suspend fun delete(book: Book) // tested

    @Query("SELECT * from books WHERE id = :id")
    fun getBookById(id: Int): Flow<Book> // tested

    @Query("SELECT * from books ORDER BY id ASC")
    fun getAllBooks(): Flow<List<Book>> // tested
}