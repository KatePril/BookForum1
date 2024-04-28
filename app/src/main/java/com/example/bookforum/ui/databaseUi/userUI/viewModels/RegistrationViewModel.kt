package com.example.bookforum.ui.databaseUi.userUI.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.bookforum.utils.TIMEOUT_MILLS
import com.example.bookforum.data.entities.User
import com.example.bookforum.data.repositories.UsersRepository
import com.example.bookforum.ui.databaseUi.userUI.states.AllUsersUIState
import com.example.bookforum.ui.databaseUi.userUI.states.UserDetails
import com.example.bookforum.ui.databaseUi.userUI.states.UserRegistrationUIState
import com.example.bookforum.ui.databaseUi.userUI.states.UserValidationDetails
import com.example.bookforum.ui.databaseUi.userUI.states.toUser
import com.example.bookforum.ui.databaseUi.userUI.viewModels.utils.UserDetailsValidator
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RegistrationViewModel(usersRepository: UsersRepository) : UserByUsernameViewModel(usersRepository) {

    var userRegistrationUIState by mutableStateOf(UserRegistrationUIState())
    private val userDetailsValidator = UserDetailsValidator()

    var usersUIState by mutableStateOf(AllUsersUIState())

    init {
        viewModelScope.launch {
            usersUIState = usersRepository.getAllUsernames()
                .map { AllUsersUIState(it) }
                .stateIn(
                    scope = viewModelScope
                ).value
        }
    }

    fun updateUiState(userDetails: UserDetails) {
        userRegistrationUIState =
            UserRegistrationUIState(
                userDetails = userDetails,
                userValidationDetails = UserValidationDetails(
                    isUsernameValid = userDetailsValidator.isUsernameUnique(userDetails, usersUIState.usernameList),
                    isPasswordValid = userDetailsValidator.isPasswordValid(userDetails),
                    isEmailValid = userDetailsValidator.isEmailValid(userDetails)
                ),
                usersList = usersUIState.usernameList
            )
    }

    suspend fun saveUser() {
        if (userRegistrationUIState.userValidationDetails.areInputsValid) {
            if (userDetailsValidator.isUsernameUnique(userRegistrationUIState.userDetails, userRegistrationUIState.usersList)) {
                usersRepository.insertUser(userRegistrationUIState.userDetails.toUser())
            }
        }
    }

    suspend fun deleteUserById(id: Int) {
        usersRepository.deleteUserById(id)
    }
}