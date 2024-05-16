package com.example.bookforum.ui.databaseUi.userUI.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.data.entities.toDetails
import com.example.bookforum.data.repositories.UsersRepository
import com.example.bookforum.ui.databaseUi.userUI.states.UserDetails
import com.example.bookforum.ui.databaseUi.userUI.states.UserLogInDetails
import com.example.bookforum.ui.databaseUi.userUI.states.UserLogInUiState
import com.example.bookforum.utils.hashPassword
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class LoginViewModel(
    private  val usersRepository: UsersRepository
) : ViewModel() {
    var userLogInUiState by mutableStateOf(UserLogInUiState())

    fun getUserUiStateByUsername(): StateFlow<UserDetails> = usersRepository
        .getUserByUsername(userLogInUiState.userDetails.username)
        .filterNotNull()
        .map { it.toDetails() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = UserDetails()
        )

    fun updateUiState(userLogInDetails: UserLogInDetails) {
        userLogInUiState = UserLogInUiState(
            userDetails = userLogInDetails,
            areInputsValid = validateInput(userLogInDetails)
        )
    }

    fun checkPassword(correctPassword: String): Boolean =
        correctPassword == hashPassword(userLogInUiState.userDetails.password)

    private fun validateInput(userLogInDetails: UserLogInDetails = userLogInUiState.userDetails): Boolean {
        return with(userLogInDetails) {
            username.isNotBlank() && password.isNotBlank()
        }
    }

}