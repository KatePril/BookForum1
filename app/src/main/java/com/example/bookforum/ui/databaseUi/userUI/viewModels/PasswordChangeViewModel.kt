package com.example.bookforum.ui.databaseUi.userUI.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.data.entities.User
import com.example.bookforum.data.entities.toDetails
import com.example.bookforum.data.repositories.UsersRepository
import com.example.bookforum.ui.databaseUi.userUI.screens.profile.ProfileDestination
import com.example.bookforum.ui.databaseUi.userUI.states.PasswordDetails
import com.example.bookforum.ui.databaseUi.userUI.states.PasswordUiState
import com.example.bookforum.ui.databaseUi.userUI.states.UserRegistrationUIState
import com.example.bookforum.ui.databaseUi.userUI.states.UserUiState
import com.example.bookforum.ui.databaseUi.userUI.states.UserValidationDetails
import com.example.bookforum.ui.databaseUi.userUI.viewModels.utils.getUserUiStateByUsername
import com.example.bookforum.utils.hashPassword
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PasswordChangeViewModel(
    savedStateHandle: SavedStateHandle,
    private val usersRepository: UsersRepository
) : ViewModel() {
    var passwordUiState by mutableStateOf(PasswordUiState())

    val userId: Int = checkNotNull(savedStateHandle[ProfileDestination.userIdArg])

    private var userUiState by mutableStateOf(User(0, "", "", ""))
    init {
        viewModelScope.launch {
            userUiState = usersRepository
                .getUserById(userId)
                .stateIn(
                    scope = viewModelScope
                ).value
        }
    }

    fun updateUiState(passwordDetails: PasswordDetails) {
        passwordUiState = PasswordUiState(
            passwordDetails = passwordDetails,
            areInputsValid = validateInput(passwordDetails)
        )
    }

    private fun validateInput(passwordDetails: PasswordDetails = passwordUiState.passwordDetails): Boolean {
        return with(passwordDetails) {
            oldPassword.isNotBlank() && newPassword.isNotBlank()
        }
    }

    fun checkPassword(): Boolean {
//        Log.i("LOG_IN", correctPassword)
        return userUiState.password == hashPassword(passwordUiState.passwordDetails.oldPassword)
    }

    suspend fun updateUser() {
        usersRepository.updateUser(
            userUiState.copy(
                password = hashPassword(passwordUiState.passwordDetails.newPassword)
            )
        )
    }
}