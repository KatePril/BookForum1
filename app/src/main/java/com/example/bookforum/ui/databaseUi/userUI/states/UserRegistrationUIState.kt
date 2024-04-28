package com.example.bookforum.ui.databaseUi.userUI.states

data class UserRegistrationUIState(
    val userDetails: UserDetails = UserDetails(),
    val userValidationDetails: UserValidationDetails = UserValidationDetails()
)



data class UserValidationDetails(
    val isUsernameValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isEmailValid: Boolean = false,
    val areInputsValid: Boolean = isPasswordValid && isPasswordValid && isEmailValid
)