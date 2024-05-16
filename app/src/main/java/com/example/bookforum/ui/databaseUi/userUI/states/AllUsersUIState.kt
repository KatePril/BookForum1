package com.example.bookforum.ui.databaseUi.userUI.states

import com.example.bookforum.data.entities.User

data class AllUsersUIState(
    val usersList: List<User> = listOf()
)

/*TODO replace AllUsersUIState with list*/