package com.example.bookforum.data.repositories

import com.example.bookforum.data.daos.UserDao
import com.example.bookforum.data.entities.User
import kotlinx.coroutines.flow.Flow

class UsersRepository(private val userDao: UserDao) {
    fun getUser(username: String): Flow<User?> = userDao.getUser(username)
    fun getAllUsernames(): Flow<List<User>> = userDao.getAllUsers()
    suspend fun insertUser(user: User) = userDao.insert(user)
    suspend fun updateUser(user: User) = userDao.update(user)
    suspend fun deleteUser(user: User) = userDao.delete(user)

    fun getUserById(id: Int) = userDao.getUserById(id)
    suspend fun deleteUserById(id: Int) = userDao.deleteUserById(id)
}