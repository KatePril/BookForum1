package com.example.bookforum.daoTests

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bookforum.data.ForumDatabase
import com.example.bookforum.data.daos.GroupMessageDao
import com.example.bookforum.data.entities.Group
import com.example.bookforum.data.entities.GroupMessage
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
class GroupMessageDaoTest {
    private lateinit var groupMessageDao: GroupMessageDao
    private lateinit var forumDatabase: ForumDatabase

    private var message1 = GroupMessage(
        id = 1,
        text = "Hi",
        date = "12:00",
        senderId = 1,
        groupId = 1,
        edited = 0,
        reply = 0
    )
    private var message2 = GroupMessage(
        id = 2,
        text = "Hello",
        date = "13:44",
        senderId = 1,
        groupId = 2,
        edited = 0,
        reply = 0
    )
    private var message3 = GroupMessage(
        id = 3,
        text = "How are you?",
        date = "13:44",
        senderId = 2,
        groupId = 1,
        edited = 0,
        reply = 0
    )

    private var user1 = User(1, "ron", "1111", "ronaldwisley@gmail.com")
    private var user2 = User(2, "luna", "0000", "luuuuna@gmail.com")

    private var group1 = Group(1, "BFFs")
    private var group2 = Group(2, "Project for EXPO")

    private suspend fun addOneMessageToDB() {
        forumDatabase.userDao().insert(user1)
        forumDatabase.userDao().insert(user2)
        forumDatabase.groupDao().insert(group1)
        groupMessageDao.insert(message1)
    }
    private suspend fun addThreeMessagesToDB() {
        forumDatabase.userDao().insert(user1)
        forumDatabase.userDao().insert(user2)
        forumDatabase.groupDao().insert(group1)
        forumDatabase.groupDao().insert(group2)

        groupMessageDao.insert(message1)
        groupMessageDao.insert(message2)
        groupMessageDao.insert(message3)
    }

    @Before
    fun createDB() {
        val  context: Context = ApplicationProvider.getApplicationContext()
        forumDatabase = Room.inMemoryDatabaseBuilder(context, ForumDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        groupMessageDao = forumDatabase.groupMessageDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        forumDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsGroupMessageIntoDB() = runBlocking {
        addOneMessageToDB()
        val groupMessage = groupMessageDao.getGroupMessagesByGroupId(1).first()
        assertEquals(groupMessage[0], message1)
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdate_updatesGroupMessageInDB() = runBlocking {
        addOneMessageToDB()
        val updatedGroupMessage = message1.copy(text = "Hi ;)")
        groupMessageDao.update(updatedGroupMessage)
        val groupMessage = groupMessageDao.getGroupMessagesByGroupId(1).first()
        assertEquals(groupMessage[0], updatedGroupMessage)
    }

    @Test
    @Throws(Exception::class)
    fun daoDelete_deletedGroupMessageFromDB() = runBlocking {
        addThreeMessagesToDB()
        groupMessageDao.delete(message1)
        val groupMessage = groupMessageDao.getGroupMessagesByGroupId(1).first()
        assertEquals(groupMessage[0], message3)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetGroupMessagesByGroupId_returnsGroupMessagesByGroupIdFromDB() = runBlocking {
        addThreeMessagesToDB()
        val groupMessages = groupMessageDao.getGroupMessagesByGroupId(1).first()
        assertEquals(groupMessages[0], message1)
        assertEquals(groupMessages[1], message3)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetGroupUsersByGroupId_returnsGroupUsersByGroupIdFromDB() = runBlocking {
        addThreeMessagesToDB()
        val users = groupMessageDao.getGroupUsersByGroupId(1).first()
        assertEquals(users[0], user1)
        assertEquals(users[1], user2)
    }

}