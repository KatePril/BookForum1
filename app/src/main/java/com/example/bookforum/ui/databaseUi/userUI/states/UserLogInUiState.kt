package com.example.bookforum.ui.databaseUi.userUI.states

import com.example.bookforum.data.entities.User

data class UserLogInUiState(
    val userDetails: UserLogInDetails = UserLogInDetails(),
    val areInputsValid: Boolean = false,
)

data class UserLogInDetails(
    val username: String = "",
    val password: String = ""
)