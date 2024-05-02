package com.example.bookforum.ui.databaseUi.userUI.states

data class PasswordUiState(
    val passwordDetails: PasswordDetails = PasswordDetails(),
    val areInputsValid: Boolean = false
)

data class PasswordDetails(
    val oldPassword: String = "",
    val newPassword: String = "",
)