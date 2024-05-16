package com.example.bookforum.daoTests

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bookforum.data.ForumDatabase
import com.example.bookforum.data.daos.GroupDao
import com.example.bookforum.data.entities.Group
import com.example.bookforum.data.entities.GroupMember
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
class GroupDaoTest {
    private lateinit var groupDao: GroupDao
    private lateinit var forumDatabase: ForumDatabase

    private var group1 = Group(
        id = 1,
        title = "BFFs"
    )
    private var group2 = Group(
        id = 2,
        title = "Trio"
    )

    private var user1 = User(1, "ron", "1111", "ronaldwisley@gmail.com")
    private var user2 = User(2, "luna", "0000", "luuuuna@gmail.com")

    private var groupMember1 = GroupMember(
        id = 1,
        userId = 1,
        groupId = 1,
        isAdmin = 0
    )
    private var groupMember2 = GroupMember(
        id = 2,
        userId = 1,
        groupId = 2,
        isAdmin = 0
    )
    private var groupMember3 = GroupMember(
        id = 3,
        userId = 2,
        groupId = 2,
        isAdmin = 0
    )

    private suspend fun addOneGroupToDB() {
        forumDatabase.userDao().insert(user1)
        groupDao.insert(group1)

        forumDatabase.groupMemberDao().insert(groupMember1)
    }

    private suspend fun addTwoGroupsToDB() {
        forumDatabase.userDao().insert(user1)
        forumDatabase.userDao().insert(user2)

        groupDao.insert(group1)
        groupDao.insert(group2)

        forumDatabase.groupMemberDao().insert(groupMember1)
        forumDatabase.groupMemberDao().insert(groupMember2)
        forumDatabase.groupMemberDao().insert(groupMember3)
    }

    @Before
    fun createDB() {
        val context: Context = ApplicationProvider.getApplicationContext()
        forumDatabase = Room.inMemoryDatabaseBuilder(context, ForumDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        groupDao = forumDatabase.groupDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        forumDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsGroupToDB() = runBlocking {
        addOneGroupToDB()
        val groupList = groupDao.getGroupsByUser(1).first()
        assertEquals(group1, groupList[0])
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdate_updatesGroupInDB() = runBlocking {
        addOneGroupToDB()
        val updatedGroup = group1.copy(title = "Best")
        groupDao.update(updatedGroup)
        val groupList = groupDao.getGroupsByUser(1).first()
        assertEquals(updatedGroup, groupList[0])
    }

    @Test
    @Throws(Exception::class)
    fun daoDelete_deletesGroupFromDB() = runBlocking {
        addTwoGroupsToDB()
        groupDao.delete(group1)
        val groupList = groupDao.getGroupsByUser(1).first()
        assertEquals(group2, groupList[0])
    }

    @Test
    @Throws(Exception::class)
    fun daoGetGroupsByUser_returnsGroupsFromDBByUser() = runBlocking {
        addTwoGroupsToDB()
        val groupList = groupDao.getGroupsByUser(1).first()
        assertEquals(group1, groupList[0])
        assertEquals(group2, groupList[1])
        assertEquals(2, groupList.size)
    }
}