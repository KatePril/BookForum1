package com.example.bookforum.ui.databaseUi.userUI.viewModels

import android.util.Log
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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class RegistrationViewModel(usersRepository: UsersRepository) : UserByUsernameViewModel(usersRepository) {

    var userRegistrationUIState by mutableStateOf(UserRegistrationUIState())
    private val userDetailsValidator = UserDetailsValidator()

    var usersUIState: StateFlow<AllUsersUIState> =
        usersRepository.getAllUsernames()
            .map { AllUsersUIState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLS),
                initialValue = AllUsersUIState()
            )

    fun updateUiState(userDetails: UserDetails, usersList: List<User>) {
        userRegistrationUIState =
            UserRegistrationUIState(
                userDetails = userDetails,
                userValidationDetails = UserValidationDetails(
                    isUsernameValid = userDetailsValidator.isUsernameUnique(userDetails, usersList),
                    isPasswordValid = userDetailsValidator.isPasswordValid(userDetails),
                    isEmailValid = userDetailsValidator.isEmailValid(userDetails)
                ),
                usersList = usersList
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