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
import kotlin.Throws

@RunWith(AndroidJUnit4::class)
class LikedPostDaoTest {
    private lateinit var likedPostDao: LikedPostDao
    private lateinit var forumDatabase: ForumDatabase

    private var likedPost1 = LikedPost(1, 1, 1)
    private var likedPost2 = LikedPost(2, 2, 1)
    private var likedPost3 = LikedPost(3, 2, 2)

    private var post1 = Post(
        id = 1,
        title = "Game of thrones",
        author = "George R R Martin",
        published = "1997",
        review = "An amazing book",
        userId = 1
    )
    private var post2 = Post(
        id = 2,
        title = "Harry Potter and the Philosopher stone",
        author = "J K Rowling",
        published = "1996",
        review = "The best book ever",
        userId = 2
    )

    private var user1 = User(1, "ron", "1111", "ronaldwisley@gmail.com", "")
    private var user2 = User(2, "luna", "0000", "luuuuna@gmail.com", "")

    private suspend fun addOneLikedPostToDb() {
        forumDatabase.userDao().insert(user1)
        forumDatabase.postDao().insert(post1)
        likedPostDao.insert(likedPost1)
    }

    private suspend fun addThreeLikedPostsToDb() {
        forumDatabase.userDao().insert(user1)
        forumDatabase.userDao().insert(user2)
        forumDatabase.postDao().insert(post1)
        forumDatabase.postDao().insert(post2)

        likedPostDao.insert(likedPost1)
        likedPostDao.insert(likedPost2)
        likedPostDao.insert(likedPost3)
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
        addOneLikedPostToDb()
        val likedPosts = likedPostDao.getLikedPosts(1).first()
        assertEquals(post1, likedPosts[0])
    }

    @Test
    @Throws(Exception::class)
    fun daoGetLikedBooks_returnsBooksIdsByUserIdFromDB() = runBlocking {
        addThreeLikedPostsToDb()
        val likedPosts = likedPostDao.getLikedPosts(2).first()
        assertEquals(post1, likedPosts[0])
        assertEquals(post2, likedPosts[1])
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdate_updatesLikedBookInDB() = runBlocking {
        addThreeLikedPostsToDb()
        val updatedLikedPost = likedPost1.copy(postId = 2)
        likedPostDao.update(updatedLikedPost)
        val likedPosts = likedPostDao.getLikedPosts(1).first()
        assertEquals(post2, likedPosts[0])
    }

    @Test
    @Throws(Exception::class)
    fun daoDelete_deletesLikedBookFromDB() = runBlocking {
        addThreeLikedPostsToDb()
        likedPostDao.delete(likedPost2)
        val likedBooks = likedPostDao.getLikedPosts(2).first()
        assertEquals(post2, likedBooks[0])
    }

    @Test
    @Throws(Exception::class)
    fun daoGetLikedPostByIds_returnsLikedPostIdFromDB() = runBlocking {
        addOneLikedPostToDb()
        val likedPost = likedPostDao.getLikedPostByIds(1, 1).first()
        assertEquals(1, likedPost)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetLikedPostByIds_returnsNull() = runBlocking {
        addOneLikedPostToDb()
        val likedPost = likedPostDao.getLikedPostByIds(1, 2).first()
        assertEquals(null, likedPost)
    }

}