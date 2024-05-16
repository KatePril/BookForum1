package com.example.bookforum.daoTests

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.bookforum.data.ForumDatabase
import com.example.bookforum.data.daos.GroupMemberDao
import com.example.bookforum.data.entities.Group
import com.example.bookforum.data.entities.GroupMember
import com.example.bookforum.data.entities.User
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException
import kotlin.jvm.Throws

class GroupMemberDaoTest {
    private lateinit var groupMemberDao: GroupMemberDao
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

    private suspend fun addOneGroupMemberToDB() {
        forumDatabase.userDao().insert(user1)
        forumDatabase.groupDao().insert(group1)

        groupMemberDao.insert(groupMember1)
    }

    private suspend fun addThreeGroupMembersToDB() {
        forumDatabase.userDao().insert(user1)
        forumDatabase.userDao().insert(user2)

        forumDatabase.groupDao().insert(group1)
        forumDatabase.groupDao().insert(group2)

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
        groupMemberDao = forumDatabase.groupMemberDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        forumDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsGroupMemberToDB() = runBlocking {
        addOneGroupMemberToDB()
        val groupMemberList = groupMemberDao.getGroupMembersByGroupId(1, 22).first()
        assertEquals(groupMemberList[0], groupMember1)

    }

    @Test
    @Throws(Exception::class)
    fun daoUpdate_updatesGroupMemberInDB() = runBlocking {
        addOneGroupMemberToDB()
        val updatedGroupMember = groupMember1.copy(isAdmin = 1)
        groupMemberDao.update(updatedGroupMember)
        val groupMemberList = groupMemberDao.getGroupMembersByGroupId(1, 2).first()
        assertEquals(groupMemberList[0], updatedGroupMember)
    }

    @Test
    @Throws(Exception::class)
    fun daoDelete_deletesGroupMemberFromDB() = runBlocking {
        addThreeGroupMembersToDB()
        groupMemberDao.delete(groupMember2)
        val groupMemberList = groupMemberDao.getGroupMembersByGroupId(2, 4).first()
        assertEquals(groupMemberList[0], groupMember3)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetGroupMembersByGroupId_returnsGroupMembersByGroupIdFromDB() = runBlocking {
        addThreeGroupMembersToDB()
        val groupMemberList = groupMemberDao.getGroupMembersByGroupId(2, 1).first()
        assertEquals(groupMemberList[0], groupMember3)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetUsersByGroupId_returnsUsersByGroupIdFromDB() = runBlocking {
        addThreeGroupMembersToDB()
        val groupMemberList = groupMemberDao.getUsersByGroupId(2).first()
        assertEquals(groupMemberList[0], user1)
        assertEquals(groupMemberList[1], user2)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetGroupMemberByUserId_returnsGroupMemberByUserIdFromDB() = runBlocking {
        addOneGroupMemberToDB()
        val groupMember = groupMemberDao.getGroupMemberByUserId(1).first()
        assertEquals(groupMember, groupMember1)
    }

}