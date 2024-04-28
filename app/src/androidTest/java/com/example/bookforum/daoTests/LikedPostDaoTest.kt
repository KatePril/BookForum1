package com.example.bookforum.daoTests

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bookforum.data.ForumDatabase
import com.example.bookforum.data.daos.LikedPostDao
import com.example.bookforum.data.entities.Post
import com.example.bookforum.data.entities.LikedPost
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
class LikedPostDaoTest {
    private lateinit var likedPostDao: LikedPostDao
    private lateinit var forumDatabase: ForumDatabase

    private var likedBook1 = LikedPost(1, 1, 1)
    private var likedBook2 = LikedPost(2, 2, 1)
    private var likedBook3 = LikedPost(3, 2, 2)

    private var book1 = Post(
        id = 1,
        title = "Game of thrones",
        author = "George R R Martin",
        published = "1997",
        review = "An amazing book"
    )
    private var book2 = Post(
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
        likedPostDao.insert(likedBook1)
    }

    suspend fun addThreeLikedBooksToDb() {
        forumDatabase.userDao().insert(user1)
        forumDatabase.userDao().insert(user2)
        forumDatabase.bookDao().insert(book1)
        forumDatabase.bookDao().insert(book2)

        likedPostDao.insert(likedBook1)
        likedPostDao.insert(likedBook2)
        likedPostDao.insert(likedBook3)
    }

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        forumDatabase = Room.inMemoryDatabaseBuilder(context, ForumDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        likedPostDao = forumDatabase.likedPostDao()
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
        val likedBooks = likedPostDao.getLikedPosts(1).first()
        assertEquals(likedBooks[0], likedBook1.bookId)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetLikedBooks_returnsBooksIdsByUserIdFromDB() = runBlocking {
        addThreeLikedBooksToDb()
        val likedBooks = likedPostDao.getLikedPosts(2).first()
        assertEquals(likedBooks[0], likedBook2.bookId)
        assertEquals(likedBooks[1], likedBook3.bookId)
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdate_updatesLikedBookInDB() = runBlocking {
        addThreeLikedBooksToDb()
        val updatedLikedBook = likedBook1.copy(bookId = 2)
        likedPostDao.update(updatedLikedBook)
        val likedBooks = likedPostDao.getLikedPosts(1).first()
        assertEquals(likedBooks[0], updatedLikedBook.bookId)
    }

    @Test
    @Throws(Exception::class)
    fun daoDelete_deletesLikedBookFromDB() = runBlocking {
        addThreeLikedBooksToDb()
        likedPostDao.delete(likedBook2)
        val likedBooks = likedPostDao.getLikedPosts(2).first()
        assertEquals(likedBooks[0], likedBook3.bookId)
    }

}