package com.example.bookforum.ui.databaseUi.userUI.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.data.repositories.UsersRepository
import com.example.bookforum.ui.databaseUi.userUI.states.AllUsersUIState
import com.example.bookforum.ui.databaseUi.userUI.states.UserDetails
import com.example.bookforum.ui.databaseUi.userUI.states.UserRegistrationUIState
import com.example.bookforum.ui.databaseUi.userUI.states.UserUiState
import com.example.bookforum.ui.databaseUi.userUI.states.UserValidationDetails
import com.example.bookforum.ui.databaseUi.userUI.states.toUser
import com.example.bookforum.ui.databaseUi.userUI.viewModels.utils.UserDetailsValidator
import com.example.bookforum.ui.databaseUi.userUI.viewModels.utils.getUserUiStateByUsername
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RegistrationViewModel(private val usersRepository: UsersRepository) : ViewModel() {
    var registrationUIState by mutableStateOf(UserRegistrationUIState())

    private val userDetailsValidator = UserDetailsValidator()

    private var usersListState by mutableStateOf(AllUsersUIState())

    val getUserUiStateByUsername: (String) -> StateFlow<UserUiState?> = {
        getUserUiStateByUsername(
            username = it,
            usersRepository = usersRepository,
            coroutineScope = viewModelScope
        )
    }

    init {
        viewModelScope.launch {
            usersListState = usersRepository.getAllUsernames()
                .map { AllUsersUIState(it) }
                .stateIn(
                    scope = viewModelScope
                ).value
        }
    }

    fun updateUiState(userDetails: UserDetails) {
        registrationUIState =
            UserRegistrationUIState(
                userDetails = userDetails,
                userValidationDetails = UserValidationDetails(
                    isUsernameValid = userDetailsValidator.isUsernameUnique(userDetails, usersListState.usersList),
                    isPasswordValid = userDetailsValidator.isPasswordValid(userDetails),
                    isEmailValid = userDetailsValidator.isEmailValid(userDetails)
                )
            )
    }

    suspend fun saveUser() {
        if (registrationUIState.userValidationDetails.areInputsValid) {
            if (userDetailsValidator.isUsernameUnique(registrationUIState.userDetails, usersListState.usersList)) {
                usersRepository.insertUser(registrationUIState.userDetails.toUser())
            }
        }
    }

    suspend fun deleteUserById(id: Int) {
        usersRepository.deleteUserById(id)
    }
}