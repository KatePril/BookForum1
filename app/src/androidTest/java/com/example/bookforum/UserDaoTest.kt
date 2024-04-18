package com.example.bookforum

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bookforum.data.ForumDatabase
import com.example.bookforum.data.daos.UserDao
import com.example.bookforum.data.entities.User
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okio.IOException
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Exception
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class UserDaoTest {
    private lateinit var userDao: UserDao
    private lateinit var forumDatabase: ForumDatabase

    private var user1 = User(1, "ron", "1111", "ronaldwisley@gmail.com")
    private var user2 = User(2, "luna", "0000", "luuuuna@gmail.com")

    private suspend fun addOneUserToDb() {
        userDao.insert(user1)
    }

    private suspend fun addTwoUsersToDb() {
        userDao.insert(user1)
        userDao.insert(user2)
    }

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        forumDatabase = Room.inMemoryDatabaseBuilder(context, ForumDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        userDao = forumDatabase.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        forumDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsUserIntoDB() = runBlocking {
        addOneUserToDb()
        val allUsers = userDao.getAllUsers().first()
        assertEquals(allUsers[0], user1)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllUsers_returnsAllUsersFromDB() = runBlocking {
        addTwoUsersToDb()
        val allUsers = userDao.getAllUsers().first()
        assertEquals(allUsers[0], user1)
        assertEquals(allUsers[1], user2)
    }


}