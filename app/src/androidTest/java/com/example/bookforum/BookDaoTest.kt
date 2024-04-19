package com.example.bookforum

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bookforum.data.ForumDatabase
import com.example.bookforum.data.daos.BookDao
import com.example.bookforum.data.entities.Book
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class BookDaoTest {
    private lateinit var bookDao: BookDao
    private lateinit var forumDatabase: ForumDatabase

    private var book1 = Book(
        id = 1,
        title = "Game of thrones",
        author = "Jorge R R Martin",
        published = "1997",
        review = "An amazing book"
    )

    private var book2 = Book(
        id = 2,
        title = "Harry Potter and the Philosopher stone",
        author = "J K Rowling",
        published = "1996",
        review = "The best book ever"
    )

    private suspend fun addOneBookToDB() {
        bookDao.insert(book1)
    }

    private suspend fun addTwoBooksToDB() {
        bookDao.insert(book1)
        bookDao.insert(book2)
    }

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        forumDatabase = Room.inMemoryDatabaseBuilder(context, ForumDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        bookDao = forumDatabase.bookDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        forumDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsBookIntoDB() = runBlocking {
        addOneBookToDB()
        val allBooks = bookDao.getAllBooks().first()
        assertEquals(allBooks[0], book1)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllBooks_returnsAllBooksFromDB() = runBlocking {
        addTwoBooksToDB()
        val allBooks = bookDao.getAllBooks().first()
        assertEquals(allBooks[0], book1)
        assertEquals(allBooks[1], book2)
    }

    @Test
    @Throws(Exception::class)
    fun doaGetBookById_returnsBookByIdFromDb() = runBlocking {
        addOneBookToDB()
        val book = bookDao.getBookById(1).first()
        assertEquals(book, book1)
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdate_updatesBookInDB() = runBlocking {
        addOneBookToDB()
        val updatedBook = book1.copy(published = "1996")
        bookDao.update(updatedBook)
        val allBooks = bookDao.getAllBooks().first()
        assertEquals(allBooks[0], updatedBook)
    }

    @Test
    @Throws(Exception::class)
    fun daoDelete_deletesBookFromDB() = runBlocking {
        addTwoBooksToDB()
        bookDao.delete(book1)
        val allBooks = bookDao.getAllBooks().first()
        assertEquals(allBooks[0], book2)
    }
}