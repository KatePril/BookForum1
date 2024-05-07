package com.example.bookforum.daoTests

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bookforum.data.ForumDatabase
import com.example.bookforum.data.daos.PostDao
import com.example.bookforum.data.entities.Post
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
class PostDaoTest {
    private lateinit var postDao: PostDao
    private lateinit var forumDatabase: ForumDatabase

    private var book1 = Post(
        id = 1,
        title = "Game of thrones",
        author = "Jorge R R Martin",
        published = "1997",
        review = "An amazing book",
        userId = 1
    )

    private var book2 = Post(
        id = 2,
        title = "Harry Potter and the Philosopher stone",
        author = "J K Rowling",
        published = "1996",
        review = "The best book ever",
        userId = 2
    )

    private var user1 = User(1, "ron", "1111", "ronaldwisley@gmail.com")
    private var user2 = User(2, "luna", "0000", "luuuuna@gmail.com")

    private suspend fun addOneBookToDB() {
        forumDatabase.userDao().insert(user1)
        postDao.insert(book1)
    }

    private suspend fun addTwoBooksToDB() {
        forumDatabase.userDao().insert(user1)
        forumDatabase.userDao().insert(user2)
        postDao.insert(book1)
        postDao.insert(book2)
    }

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        forumDatabase = Room.inMemoryDatabaseBuilder(context, ForumDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        postDao = forumDatabase.postDao()
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
        val allBooks = postDao.getAllPosts().first()
        assertEquals(allBooks[0], book1)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllBooks_returnsAllBooksFromDB() = runBlocking {
        addTwoBooksToDB()
        val allBooks = postDao.getAllPosts().first()
        assertEquals(allBooks[0], book1)
        assertEquals(allBooks[1], book2)
    }

    @Test
    @Throws(Exception::class)
    fun doaGetBookById_returnsBookByIdFromDB() = runBlocking {
        addOneBookToDB()
        val book = postDao.getPostById(1).first()
        assertEquals(book, book1)
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdate_updatesBookInDB() = runBlocking {
        addOneBookToDB()
        val updatedBook = book1.copy(published = "1996")
        postDao.update(updatedBook)
        val allBooks = postDao.getAllPosts().first()
        assertEquals(allBooks[0], updatedBook)
    }

    @Test
    @Throws(Exception::class)
    fun daoDelete_deletesBookFromDB() = runBlocking {
        addTwoBooksToDB()
        postDao.delete(book1)
        val allBooks = postDao.getAllPosts().first()
        assertEquals(allBooks[0], book2)
    }
}