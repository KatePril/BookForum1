package com.example.bookforum

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bookforum.data.ForumDatabase
import com.example.bookforum.data.daos.CommentDao
import com.example.bookforum.data.entities.Book
import com.example.bookforum.data.entities.Comment
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
class CommentDaoTest {
    private lateinit var commentDao: CommentDao
    private lateinit var forumDatabase: ForumDatabase

    private var comment1 = Comment(1, "18.04.2024", "something", 1, 2)
    private var comment2 = Comment(2, "19.04.2024", "something", 2, 1)
    private var comment3 = Comment(3, "20.04.2024", "something", 1, 1)

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

    private suspend fun addOneCommentToDB() {
        forumDatabase.userDao().insert(user1)
        forumDatabase.bookDao().insert(book2)
        commentDao.insert(comment1)
    }

    private suspend fun addThreeCommentsToDb() {
        forumDatabase.userDao().insert(user1)
        forumDatabase.bookDao().insert(book2)
        commentDao.insert(comment1)

        forumDatabase.userDao().insert(user2)
        forumDatabase.bookDao().insert(book1)
        commentDao.insert(comment2)
        commentDao.insert(comment3)
    }

    @Before
    fun createDB() {
        val context: Context = ApplicationProvider.getApplicationContext()
        forumDatabase = Room.inMemoryDatabaseBuilder(context, ForumDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        commentDao = forumDatabase.commentDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        forumDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsCommentIntoDB() = runBlocking {
        addOneCommentToDB()
        val bookComment = commentDao.getBookComments(2).first()
        assertEquals(bookComment[0], comment1)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetBookComments_returnsCommentsByBookIdFromDB() = runBlocking {
        addThreeCommentsToDb()
        val bookComments = commentDao.getBookComments(1).first()
        assertEquals(bookComments[0], comment2)
        assertEquals(bookComments[1], comment3)
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdate_updatesCommentInDB() = runBlocking {
        addOneCommentToDB()
        val updatedComment = comment1.copy(text = "something new")
        commentDao.update(updatedComment)
        val bookComment = commentDao.getBookComments(2).first()
        assertEquals(bookComment[0], updatedComment)
    }

    @Test
    @Throws(Exception::class)
    fun daoDelete_deletesCommentFromBD() = runBlocking {
        addThreeCommentsToDb()
        commentDao.delete(comment2)
        val bookComments = commentDao.getBookComments(1).first()
        assertEquals(bookComments[0], comment3)
    }
}