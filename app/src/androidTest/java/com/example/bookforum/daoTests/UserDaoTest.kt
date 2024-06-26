package com.example.bookforum.daoTests

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

    private var user1 = User(1, "ron", "1111", "ronaldwisley@gmail.com", "")
    private var user2 = User(2, "luna", "0000", "luuuuna@gmail.com", "")

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
        assertEquals(user1, allUsers[0])
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllUsers_returnsAllUsersFromDB() = runBlocking {
        addTwoUsersToDb()
        val allUsers = userDao.getAllUsers().first()
        assertEquals(user1, allUsers[0])
        assertEquals(user2, allUsers[1])
    }

    @Test
    @Throws(Exception::class)
    fun daoGetUserById_returnsUserByIdFromDB() = runBlocking {
        addOneUserToDb()
        val user = userDao.getUserById(1).first()
        assertEquals(user1, user)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetUserByNotId_returnsUsersByNotIdFromDB() = runBlocking {
        addTwoUsersToDb()
        val users = userDao.getUserByNotId(1).first()
        assertEquals(user2, users[0])
    }

    @Test
    @Throws(Exception::class)
    fun daoGetUserByUsername_returnsUserByUsernameFromDB() = runBlocking {
        addOneUserToDb()
        val user = userDao.getUserByUsername("ron").first()
        assertEquals(user1, user)
    }

    @Test
    @Throws(Exception::class)
    fun daoDelete_deletesUserFromDB() = runBlocking {
        addTwoUsersToDb()
        userDao.delete(user1)
        val allUsers = userDao.getAllUsers().first()
        assertEquals(user2, allUsers[0])
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdate_updatesUserInDB() = runBlocking {
        addOneUserToDb()
        val userUpdated = user1.copy(username = "harry")
        userDao.update(userUpdated)
        val allUsers = userDao.getAllUsers().first()
        assertEquals(userUpdated, allUsers[0])
    }

}