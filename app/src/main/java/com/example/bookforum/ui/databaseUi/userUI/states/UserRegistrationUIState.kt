package com.example.bookforum.ui.databaseUi.userUI.states

import com.example.bookforum.data.entities.User

data class UserRegistrationUIState(
    val userDetails: UserDetails = UserDetails(),
    val userValidationDetails: UserValidationDetails = UserValidationDetails(),
    val usersList: List<User> = listOf()
)

data class UserDetails(
    val id: Int = 0,
    val username: String = "",
    val password: String = "",
    val email: String = ""
)

data class UserValidationDetails(
    val isUsernameValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isEmailValid: Boolean = false,
    val areInputsValid: Boolean = isPasswordValid && isPasswordValid && isEmailValid
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