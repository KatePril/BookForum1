package com.example.bookforum.ui.databaseScreens

import com.example.bookforum.data.entities.User

data class UserUIState(
    val userDetails: UserDetails = UserDetails(),
    val isInputValid: Boolean = false
)

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

fun User.toDetails(): UserDetails = UserDetails(
    id = id,
    username = username,
    password = password,
    email = email
)
