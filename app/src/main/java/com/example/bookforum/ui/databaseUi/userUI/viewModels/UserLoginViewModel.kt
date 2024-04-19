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
import com.example.bookforum.ui.databaseUi.userUI.states.toDetails
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class UserLoginViewModel(private val usersRepository: UsersRepository) : ViewModel() {

    var userLogInUiState by mutableStateOf(UserLogInUiState())

//    var userUiState: StateFlow<UserUiState?> =
//        usersRepository.getUser("Kate")
//            .map { it?.toDetails()?.let { details -> UserUiState(details) } }
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(),
//                initialValue = UserUiState()
//            )

    fun getUserUiState(username: String) = usersRepository.getUserByUsername(username)
        .map { it?.toDetails()?.let { details -> UserUiState(details) } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = UserUiState()
        )

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