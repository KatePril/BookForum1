package com.example.bookforum.ui.databaseUi.userUI.states

import com.example.bookforum.data.entities.User

data class UserDetails(
    val id: Int = 0,
    val username: String = "",
    val password: String = "",
    val email: String = ""
)

fun UserDetails.toUser(): User = User(
    id = id,
    username = username,
    password = password,
    email = email
)

