package com.example.bookforum.ui.databaseUi.userUI.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.bookforum.data.entities.toDetails
import com.example.bookforum.data.repositories.UsersRepository
import com.example.bookforum.ui.databaseUi.userUI.screens.profile.ProfileDestination
import com.example.bookforum.ui.databaseUi.userUI.states.UserRegistrationUIState
import com.example.bookforum.ui.databaseUi.userUI.states.UserValidationDetails
import com.example.bookforum.ui.databaseUi.userUI.states.toUser
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProfileViewModel(
    savedStateHandle: SavedStateHandle,
    private val usersRepository: UsersRepository
) : ViewModelWithUsernameValidation(usersRepository) {


    private val userId: Int = checkNotNull(savedStateHandle[ProfileDestination.userIdArg])

    init {
        viewModelScope.launch {
            registrationUIState = usersRepository
                .getUserById(userId)
                .filterNotNull()
                .map {
                    UserRegistrationUIState(
                        userDetails = it.toDetails(),
                        userValidationDetails = UserValidationDetails(
                            isUsernameValid = true,
                            isPasswordValid = true,
                            isEmailValid = true
                        )
                    )
                }
                .stateIn(
                    scope = viewModelScope
                ).value
        }
    }

    suspend fun updateUser() {
        if (registrationUIState.userValidationDetails.areInputsValid) {
            if (userDetailsValidator.isUsernameUnique(registrationUIState.userDetails, usersListState.usersList)) {
                usersRepository.updateUser(registrationUIState.userDetails.toUser())
            }
        }
    }

    suspend fun deleteUser() {
        usersRepository.deleteUser(registrationUIState.userDetails.toUser())
    }

}