package com.example.bookforum

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bookforum.data.ForumDatabase
import com.example.bookforum.data.daos.LikedBookDao
import com.example.bookforum.data.entities.Book
import com.example.bookforum.data.entities.LikedBook
import com.example.bookforum.data.entities.User
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
class LikedBookDaoTest {
    private lateinit var likedBookDao: LikedBookDao
    private lateinit var forumDatabase: ForumDatabase

    private var likedBook1 = LikedBook(1, 1, 1)
    private var likedBook2 = LikedBook(2, 2, 1)
    private var likedBook3 = LikedBook(3, 2, 2)

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

    private var user1 = User(1, "ron", "1111", "ronaldwisley@gmail.com")
    private var user2 = User(2, "luna", "0000", "luuuuna@gmail.com")

    suspend fun addOneLikedBookToDb() {
        forumDatabase.userDao().insert(user1)
        forumDatabase.bookDao().insert(book1)
        likedBookDao.insert(likedBook1)
    }

    suspend fun addThreeLikedBooksToDb() {
        forumDatabase.userDao().insert(user1)
        forumDatabase.userDao().insert(user2)
        forumDatabase.bookDao().insert(book1)
        forumDatabase.bookDao().insert(book2)

        likedBookDao.insert(likedBook1)
        likedBookDao.insert(likedBook2)
        likedBookDao.insert(likedBook3)
    }

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        forumDatabase = Room.inMemoryDatabaseBuilder(context, ForumDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        likedBookDao = forumDatabase.likedBookDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        forumDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertLikedBookIntoDB() = runBlocking {
        addOneLikedBookToDb()
        val likedBooks = likedBookDao.getLikedBooks(1).first()
        assertEquals(likedBooks[0], likedBook1.bookId)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetLikedBooks_returnsBooksIdsByUserIdFromDB() = runBlocking {
        addThreeLikedBooksToDb()
        val likedBooks = likedBookDao.getLikedBooks(2).first()
        assertEquals(likedBooks[0], likedBook2.bookId)
        assertEquals(likedBooks[1], likedBook3.bookId)
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdate_updatesLikedBookInDB() = runBlocking {
        addThreeLikedBooksToDb()
        val updatedLikedBook = likedBook1.copy(bookId = 2)
        likedBookDao.update(updatedLikedBook)
        val likedBooks = likedBookDao.getLikedBooks(1).first()
        assertEquals(likedBooks[0], updatedLikedBook.bookId)
    }

    @Test
    @Throws(Exception::class)
    fun daoDelete_deletesLikedBookFromDB() = runBlocking {
        addThreeLikedBooksToDb()
        likedBookDao.delete(likedBook2)
        val likedBooks = likedBookDao.getLikedBooks(2).first()
        assertEquals(likedBooks[0], likedBook3.bookId)
    }

}