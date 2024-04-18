package com.example.bookforum.ui.databaseUi.userUI.states

data class UserLogInUiState(
    val userDetails: UserLogInDetails = UserLogInDetails(),
    val areInputsValid: Boolean = false,
)

data class UserLogInDetails(
    val username: String = "",
    val password: String = ""
)