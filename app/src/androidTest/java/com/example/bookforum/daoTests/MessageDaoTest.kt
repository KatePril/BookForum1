package com.example.bookforum.daoTests

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bookforum.data.ForumDatabase
import com.example.bookforum.data.daos.MessageDao
import com.example.bookforum.data.entities.Message
import com.example.bookforum.data.entities.User
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okio.IOException
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class MessageDaoTest {
    private lateinit var messageDao: MessageDao
    private lateinit var forumDatabase: ForumDatabase

    private var message1 = Message(
        id = 1,
        text = "Hi",
        date = "12:00",
        senderId = 1,
        receiverId = 2,
        edited = 0,
        reply = 0
    )
    private var message2 = Message(
        id = 2,
        text = "Hello",
        date = "13:44",
        senderId = 3,
        receiverId = 2,
        edited = 0,
        reply = 0
    )
    private var message3 = Message(
        id = 3,
        text = "How are you?",
        date = "13:44",
        senderId = 2,
        receiverId = 1,
        edited = 0,
        reply = 0
    )

    private var user1 = User(1, "ron", "1111", "ronaldwisley@gmail.com", "")
    private var user2 = User(2, "luna", "0000", "luuuuna@gmail.com", "")
    private var user3 = User(3, "draco", "2222", "dracomalfoy@gmail.com", "")

    private suspend fun addOneMessageToDB() {
        forumDatabase.userDao().insert(user1)
        forumDatabase.userDao().insert(user2)
        messageDao.insert(message1)
    }

    private suspend fun addThreeMessagesToDB() {
        forumDatabase.userDao().insert(user1)
        forumDatabase.userDao().insert(user2)
        forumDatabase.userDao().insert(user3)
        messageDao.insert(message1)
        messageDao.insert(message2)
        messageDao.insert(message3)
    }

    @Before
    fun createDB() {
        val  context: Context = ApplicationProvider.getApplicationContext()
        forumDatabase = Room.inMemoryDatabaseBuilder(context, ForumDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        messageDao = forumDatabase.messageDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        forumDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsMessageIntoDB() = runBlocking {
        addOneMessageToDB()
        val messages = messageDao.getChatMessages(1, 2).first()
        assertEquals(message1, messages[0])
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdate_updatesMessageInTheDB() = runBlocking {
        addOneMessageToDB()
        val updatedMessage = message1.copy(text = "Hi :)")
        messageDao.update(updatedMessage)
        val messages = messageDao.getChatMessages(1, 2).first()
        assertEquals(updatedMessage, messages[0])
    }

    @Test
    @Throws(Exception::class)
    fun daoDelete_deletesMessageFromDB() = runBlocking {
        addThreeMessagesToDB()
        messageDao.delete(message1)
        val messages = messageDao.getChatMessages(1, 2).first()
        assertEquals(message3, messages[0])
    }

    @Test
    @Throws(Exception::class)
    fun daoGetChatMessages_getsMessagesByChatFromDB() = runBlocking {
        addThreeMessagesToDB()
        val messages = messageDao.getChatMessages(2, 1).first()
        assertEquals(message1, messages[0])
        assertEquals(message3, messages[1])
    }

    @Test
    @Throws(Exception::class)
    fun daoDeleteMessageById_deletesMessageFromDBById() = runBlocking {
        addThreeMessagesToDB()
        messageDao.deleteMessageById(message1.id)
        val messages = messageDao.getChatMessages(2, 1).first()
        assertEquals(message3, messages[0])
    }

    @Test
    @Throws(Exception::class)
    fun daoGetMessageById_returnsMessageFromDBById() = runBlocking {
        addOneMessageToDB()
        val message = messageDao.getMessageById(1).first()
        assertEquals(message1, message)
    }
}