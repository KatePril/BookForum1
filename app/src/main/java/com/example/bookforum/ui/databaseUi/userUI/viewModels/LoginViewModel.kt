package com.example.bookforum.ui.databaseUi.userUI.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.data.repositories.UsersRepository
import com.example.bookforum.ui.databaseUi.userUI.states.UserLogInDetails
import com.example.bookforum.ui.databaseUi.userUI.states.UserLogInUiState
import com.example.bookforum.ui.databaseUi.userUI.states.UserUiState
import com.example.bookforum.ui.databaseUi.userUI.viewModels.utils.getUserUiStateByUsername
import com.example.bookforum.utils.hashPassword
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel(usersRepository: UsersRepository) : ViewModel() {

    var userLogInUiState by mutableStateOf(UserLogInUiState())

    val getUserUiStateByUsername: (String) -> StateFlow<UserUiState?> = {
        getUserUiStateByUsername(
            username = it,
            usersRepository = usersRepository,
            coroutineScope = viewModelScope
        )
    }
    fun updateUiState(userLogInDetails: UserLogInDetails) {
        userLogInUiState = UserLogInUiState(
            userDetails = userLogInDetails,
            areInputsValid = validateInput(userLogInDetails)
        )
    }

    fun checkPassword(correctPassword: String): Boolean {
        Log.i("LOG_IN", correctPassword)
        return correctPassword == hashPassword(userLogInUiState.userDetails.password)
    }

    private fun validateInput(userLogInDetails: UserLogInDetails = userLogInUiState.userDetails): Boolean {
        return with(userLogInDetails) {
            username.isNotBlank() && password.isNotBlank()
        }
    }

}