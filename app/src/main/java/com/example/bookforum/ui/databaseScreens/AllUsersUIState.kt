package com.example.bookforum.ui.databaseScreens

import com.example.bookforum.data.entities.User

data class AllUsersUIState(
    val usernameList: List<User> = listOf()
)