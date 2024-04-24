package com.example.bookforum.ui.databaseUi.userUI.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.bookforum.data.repositories.UsersRepository
import com.example.bookforum.ui.databaseUi.userUI.states.UserLogInDetails
import com.example.bookforum.ui.databaseUi.userUI.states.UserLogInUiState

class LoginViewModel(usersRepository: UsersRepository) : UserByUsernameViewModel(usersRepository) {

    var userLogInUiState by mutableStateOf(UserLogInUiState())

    fun updateUiState(userLogInDetails: UserLogInDetails) {
        userLogInUiState = UserLogInUiState(
            userDetails = userLogInDetails,
            areInputsValid = validateInput(userLogInDetails)
        )
    }

    fun checkPassword(correctPassword: String): Boolean {
        Log.i("LOG_IN", correctPassword)
        return correctPassword == userLogInUiState.userDetails.password
    }

    private fun validateInput(userLogInDetails: UserLogInDetails = userLogInUiState.userDetails): Boolean {
        return with(userLogInDetails) {
            username.isNotBlank() && password.isNotBlank()
        }
    }

}